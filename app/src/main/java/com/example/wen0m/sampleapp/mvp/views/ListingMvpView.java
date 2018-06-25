package com.example.wen0m.sampleapp.mvp.views;


import com.example.wen0m.sampleapp.base.BaseMvpView;
import com.example.wen0m.sampleapp.mvp.models.Fighter;
import java.util.List;


public interface ListingMvpView extends BaseMvpView {
    void showHousesList(List<Fighter> buildings);
    void onHouseClicked(Long buildId);
}
