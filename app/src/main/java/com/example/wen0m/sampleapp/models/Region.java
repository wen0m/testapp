package com.example.wen0m.sampleapp.models;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Region {

    @Id
    private long id;

    @Property
    private String name;

    @Generated(hash = 1568887301)
    public Region(long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Generated(hash = 600106640)
    public Region() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
