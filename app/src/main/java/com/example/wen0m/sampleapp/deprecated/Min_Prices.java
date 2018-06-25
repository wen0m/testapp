package com.example.wen0m.sampleapp.deprecated;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Deprecated
//@Entity
public class Min_Prices {

    @Id
    private Long priceId;

    @Property
    private String rooms;

    @Property
    private double totalArea;

    @Property
    private int price;


}
