
package com.example.entity;

import java.util.HashMap;
import java.util.Map;

public class Specialization {

    private String profarea_id;
    private String profarea_name;
    private String id;
    private String name;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getProfarea_id() {
        return profarea_id;
    }

    public void setProfarea_id(String profarea_id) {
        this.profarea_id = profarea_id;
    }

    public String getProfarea_name() {
        return profarea_name;
    }

    public void setProfarea_name(String profarea_name) {
        this.profarea_name = profarea_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
