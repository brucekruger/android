
package com.example.entity;

import java.util.HashMap;
import java.util.Map;

public class Logo_urls {

    private String _90;
    private String _240;
    private String original;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String get90() {
        return _90;
    }

    public void set90(String _90) {
        this._90 = _90;
    }

    public String get240() {
        return _240;
    }

    public void set240(String _240) {
        this._240 = _240;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
