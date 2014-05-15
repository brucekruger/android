
package com.example.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Vacancy {

    private String description;
    private Schedule schedule;
    private Boolean accept_handicapped;
    private Experience experience;
    private Object address;
    private String alternate_url;
    private Employment employment;
    private String id;
    private Salary salary;
    private Boolean archived;
    private String name;
    private Area area;
    private String published_at;
    private List<Object> relations = new ArrayList<Object>();
    private String negotiations_url;
    private Employer employer;
    private Boolean response_letter_required;
    private Object response_url;
    private Type type;
    private Test test;
    private List<Specialization> specializations = new ArrayList<Specialization>();
    private Contacts contacts;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public Boolean getAccept_handicapped() {
        return accept_handicapped;
    }

    public void setAccept_handicapped(Boolean accept_handicapped) {
        this.accept_handicapped = accept_handicapped;
    }

    public Experience getExperience() {
        return experience;
    }

    public void setExperience(Experience experience) {
        this.experience = experience;
    }

    public Object getAddress() {
        return address;
    }

    public void setAddress(Object address) {
        this.address = address;
    }

    public String getAlternate_url() {
        return alternate_url;
    }

    public void setAlternate_url(String alternate_url) {
        this.alternate_url = alternate_url;
    }

    public Employment getEmployment() {
        return employment;
    }

    public void setEmployment(Employment employment) {
        this.employment = employment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Salary getSalary() {
        return salary;
    }

    public void setSalary(Salary salary) {
        this.salary = salary;
    }

    public Boolean getArchived() {
        return archived;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public String getPublished_at() {
        return published_at;
    }

    public void setPublished_at(String published_at) {
        this.published_at = published_at;
    }

    public List<Object> getRelations() {
        return relations;
    }

    public void setRelations(List<Object> relations) {
        this.relations = relations;
    }

    public String getNegotiations_url() {
        return negotiations_url;
    }

    public void setNegotiations_url(String negotiations_url) {
        this.negotiations_url = negotiations_url;
    }

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    public Boolean getResponse_letter_required() {
        return response_letter_required;
    }

    public void setResponse_letter_required(Boolean response_letter_required) {
        this.response_letter_required = response_letter_required;
    }

    public Object getResponse_url() {
        return response_url;
    }

    public void setResponse_url(Object response_url) {
        this.response_url = response_url;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public List<Specialization> getSpecializations() {
        return specializations;
    }

    public void setSpecializations(List<Specialization> specializations) {
        this.specializations = specializations;
    }

    public Contacts getContacts() {
        return contacts;
    }

    public void setContacts(Contacts contacts) {
        this.contacts = contacts;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
