package com.example.wen0m.sampleapp.deprecated;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Deprecated
//@Entity
public class Subways {

    @Id
    private Long subId;

    @Property
    private String name;

    @Property
    private int distance_timing;

    @Property
    private String distance_type;

}
