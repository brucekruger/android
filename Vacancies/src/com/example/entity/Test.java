
package com.example.entity;

import java.util.HashMap;
import java.util.Map;

public class Test {

    private Boolean required;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
