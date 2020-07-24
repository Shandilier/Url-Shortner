package com.Sachin.Service;

import com.Sachin.Dto.UrlRequest;
import com.Sachin.Model.Url;
import com.Sachin.Repository.UrlRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.Null;
import java.util.Date;
import java.util.Optional;

@Service
public class UrlService {
    private final UrlRepository urlRepository;
    private final BaseConversion baseConversion;

    public UrlService(UrlRepository urlRepository, BaseConversion baseConversion) {
        this.urlRepository = urlRepository;
        this.baseConversion = baseConversion;
    }
    public String shortenUrl(UrlRequest urlRequest)
    {
        Url url = new Url();
        url.setUrl(urlRequest.getUrl());
        url.setCreatedDate(new Date());
        url.setExpiresDate(urlRequest.getExpiresDate());
        Url obj = urlRepository.save(url);
//        System.out.println(baseConversion.encode(obj.getId()));
        return baseConversion.encode(obj.getId());
    }
    public String getOriginalUrl(String shortUrl)
    {
        int input = (int) baseConversion.decode(shortUrl);
        System.out.println("The id value is: "+input);
        Url url = urlRepository.findById(input).orElseThrow(() -> new EntityNotFoundException("No object with Url as "+shortUrl));
        if(url.getExpiresDate()!= null && url.getExpiresDate().before(new Date()))
            urlRepository.delete(url);
        System.out.println("Value is "+url.getUrl());
        return url.getUrl();
    }
    public int getUrlId(String shortUrl){
        int input = (int) baseConversion.decode(shortUrl);
        return input;
    }
}
