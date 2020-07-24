package com.Sachin.Dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel(description = "Required object for Post method")
public class UrlRequest {

    @ApiModelProperty(required=true,notes="Url to shorten")
    private String url;
    @ApiModelProperty(notes = "Expiration date for url")
    private Date expiresDate;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getExpiresDate() {
        return expiresDate;
    }

    public void setExpiresDate(Date expiresDate) {
        this.expiresDate = expiresDate;
    }
}
