package com.example.wen0m.sampleapp.listing;


import com.example.wen0m.sampleapp.models.Building;
import com.example.wen0m.sampleapp.shared.base.BaseMvpView;

import java.util.List;


public interface ListingMvpView extends BaseMvpView{
    void showHousesList(List<Building> buildings);
    void onHouseClicked(Long buildId);
}
