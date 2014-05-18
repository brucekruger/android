
package com.example.entity;

public class Employer {

    private String url;
    private String alternate_url;
    private Logo_urls logo_urls;
    private String name;
    private String id;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAlternate_url() {
        return alternate_url;
    }

    public void setAlternate_url(String alternate_url) {
        this.alternate_url = alternate_url;
    }

    public Logo_urls getLogo_urls() {
        return logo_urls;
    }

    public void setLogo_urls(Logo_urls logo_urls) {
        this.logo_urls = logo_urls;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
