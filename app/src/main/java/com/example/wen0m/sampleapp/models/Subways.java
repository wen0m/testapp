package com.example.wen0m.sampleapp.models;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Subways {

    @Id
    private Long subId;

    @Property
    private String name;

    @Property
    private int distance_timing;

    @Property
    private String distance_type;

    @Generated(hash = 1329976504)
    public Subways(Long subId, String name, int distance_timing,
            String distance_type) {
        this.subId = subId;
        this.name = name;
        this.distance_timing = distance_timing;
        this.distance_type = distance_type;
    }

    @Generated(hash = 1343446021)
    public Subways() {
    }

    public Long getSubId() {
        return this.subId;
    }

    public void setSubId(Long subId) {
        this.subId = subId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDistance_timing() {
        return this.distance_timing;
    }

    public void setDistance_timing(int distance_timing) {
        this.distance_timing = distance_timing;
    }

    public String getDistance_type() {
        return this.distance_type;
    }

    public void setDistance_type(String distance_type) {
        this.distance_type = distance_type;
    }

}
