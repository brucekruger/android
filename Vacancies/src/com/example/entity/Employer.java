
package com.example.entity;

import java.util.HashMap;
import java.util.Map;

public class Employer {

    private Logo_urls logo_urls;
    private String name;
    private String url;
    private String alternate_url;
    private String id;
    private Boolean trusted;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getTrusted() {
        return trusted;
    }

    public void setTrusted(Boolean trusted) {
        this.trusted = trusted;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
