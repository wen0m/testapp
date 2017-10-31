package com.example.wen0m.sampleapp.models;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;


@Entity
public class Min_Prices {

    @Id
    private Long priceId;

    @Property
    private String rooms;

    @Property
    private double totalArea;

    @Property
    private int price;

    @Generated(hash = 1660595683)
    public Min_Prices(Long priceId, String rooms, double totalArea, int price) {
        this.priceId = priceId;
        this.rooms = rooms;
        this.totalArea = totalArea;
        this.price = price;
    }

    @Generated(hash = 2102924061)
    public Min_Prices() {
    }

    public Long getPriceId() {
        return this.priceId;
    }

    public void setPriceId(Long priceId) {
        this.priceId = priceId;
    }

    public String getRooms() {
        return this.rooms;
    }

    public void setRooms(String rooms) {
        this.rooms = rooms;
    }

    public double getTotalArea() {
        return this.totalArea;
    }

    public void setTotalArea(double totalArea) {
        this.totalArea = totalArea;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


}
