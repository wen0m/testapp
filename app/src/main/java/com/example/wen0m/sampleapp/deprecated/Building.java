package com.example.wen0m.sampleapp.deprecated;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToMany;
import java.util.List;
import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.ToOne;

@Deprecated
//@Entity
public class Building {

    @Id
    private Long id;
    
    @Index(unique = true)
    private String guid;

    @Property
    private String name;

    @Property
    private String finishing;

    @Property
    private String deadline;

    @Property
    private String address;

    @Property
    private String image;

    @Property
    private Double latitude;

    @Property
    private Double longitude;

    @Property
    private int apart_count;

    @ToOne
    private Region region;

    @ToMany(referencedJoinProperty = "subId")
    private List<Subways> subways;

    @ToMany(referencedJoinProperty = "priceId")
    private List<Min_Prices> min_prices;

}

