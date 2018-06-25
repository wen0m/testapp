package com.example.wen0m.sampleapp.mvp.models;

import android.support.annotation.Nullable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

import java.util.Date;

/**
 * Created by moisha on 01.02.2018.
 */

@Entity
public class Fighter {
    @Id
    private Long id;

//    @Index(unique = true)
//    private String guid;

    @Property
    private String first_name;

    @Property
    private String last_name;

    @Property
    @Nullable
    private String nickname;

    @Property
    private String dob;

    @Property
    private String country_residing;

    @Property
    private String state_residing;

    @Property
    private String city_residing;

    @Property
    private String profile_image;

    @Property
    private String strengths;

    @Property
    private int height;

    @Property
    private int weight;

    @Property
    private String weight_class;

    @Property
    private int wins;

    @Property
    private int losses;

    @Property
    private int draws;

    @Generated(hash = 554488065)
    public Fighter(Long id, String first_name, String last_name, String nickname,
            String dob, String country_residing, String state_residing,
            String city_residing, String profile_image, String strengths,
            int height, int weight, String weight_class, int wins, int losses,
            int draws) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.nickname = nickname;
        this.dob = dob;
        this.country_residing = country_residing;
        this.state_residing = state_residing;
        this.city_residing = city_residing;
        this.profile_image = profile_image;
        this.strengths = strengths;
        this.height = height;
        this.weight = weight;
        this.weight_class = weight_class;
        this.wins = wins;
        this.losses = losses;
        this.draws = draws;
    }

    @Generated(hash = 427134932)
    public Fighter() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return this.first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return this.last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getDob() {
        return this.dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getCountry_residing() {
        return this.country_residing;
    }

    public void setCountry_residing(String country_residing) {
        this.country_residing = country_residing;
    }

    public String getState_residing() {
        return this.state_residing;
    }

    public void setState_residing(String state_residing) {
        this.state_residing = state_residing;
    }

    public String getCity_residing() {
        return this.city_residing;
    }

    public void setCity_residing(String city_residing) {
        this.city_residing = city_residing;
    }

    public String getProfile_image() {
        return this.profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public String getStrengths() {
        return this.strengths;
    }

    public void setStrengths(String strengths) {
        this.strengths = strengths;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return this.weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getWeight_class() {
        return this.weight_class;
    }

    public void setWeight_class(String weight_class) {
        this.weight_class = weight_class;
    }

    public int getWins() {
        return this.wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return this.losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public int getDraws() {
        return this.draws;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }
}
