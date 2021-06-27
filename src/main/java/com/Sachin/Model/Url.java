package com.Sachin.Model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "url")
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private String url;

    @Column(nullable = false)
    private Date createdDate;

    private Date ExpiresDate;
    public Url()
    {

    }
    public Url(int id, String url, Date createdDate, Date expiresDate) {
        super();
        this.id = id;
        this.url = url;
        this.createdDate = createdDate;
        ExpiresDate = expiresDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getExpiresDate() {
        return ExpiresDate;
    }

    public void setExpiresDate(Date expiresDate) {
        ExpiresDate = expiresDate;
    }
}
// test issue
