package com.yhl.entity;

import org.apache.solr.client.solrj.beans.Field;

public class Inf {
    @Field
    private String id;
    @Field
    private String name;
    @Field
    private Integer age;

    public Inf(String id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
