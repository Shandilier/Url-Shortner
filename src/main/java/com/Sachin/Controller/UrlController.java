package com.Sachin.Controller;

import com.Sachin.Dto.UrlRequest;
import com.Sachin.Model.Url;
import com.Sachin.Repository.UrlRepository;
import com.Sachin.Service.UrlService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("api/new")
public class UrlController {
    @Autowired
    public UrlService urlService;
    @Autowired
    public UrlRepository urlRepository;
    public UrlController(UrlService urlService,UrlRepository urlRepository) {
        this.urlService = urlService;
        this.urlRepository = urlRepository;
    }

    @ApiOperation(value = "Shorten the Url", notes = "Converts the given Url to shrter format")
    @PostMapping("shortUrl")
    public String convertToShortUrl(@RequestBody UrlRequest urlRequest)
    {
//        System.out.println(urlService.shortenUrl(urlRequest)+" Hello");
        return urlService.shortenUrl(urlRequest);
    }

    @ApiOperation(value = "Fetch url", notes = "Fetches the actual url")
    @GetMapping("{shortUrl}")
    @Cacheable(value = "url",key = "#shortUrl",sync = true)
    public String getUrl(@PathVariable String shortUrl)
    {
        System.out.println("Value of shortUrl is: "+ shortUrl);
        String url = urlService.getOriginalUrl(shortUrl);
//        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(url)).build();
        return url;
    }
    @ApiOperation(value = "Url to delete",notes = "url which deletes the mentioned url")
    @DeleteMapping("{shortUrl}")
    @Cacheable(value = "url1",key="#shortUrl1",sync = true)
    public void deleteUrlRecord(@PathVariable("shortUrl") String url)
    {
        int input = urlService.getUrlId(url);
        urlRepository.deleteById(input);
    }

    @ApiOperation(value = "Url to delete",notes = "url which deletes the mentioned url")
    @PutMapping("{shortUrl}")
    @Cacheable(value = "url2",key="#shortUrl2",sync = true)
    public void updateUrlRecord(@PathVariable("shortUrl") String url,@RequestBody UrlRequest urlRequest)
    {
        int input = urlService.getUrlId(url);
//        Optional<Url> obj = urlRepository.findById(input);
        Url obj = urlRepository.getOne(input);
        obj.setExpiresDate(urlRequest.getExpiresDate());
        obj.setUrl(urlRequest.getUrl());
        urlRepository.save(obj);
    }
}
