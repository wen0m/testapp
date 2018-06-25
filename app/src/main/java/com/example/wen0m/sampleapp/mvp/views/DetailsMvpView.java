package com.example.wen0m.sampleapp.mvp.views;


import com.example.wen0m.sampleapp.base.BaseMvpView;
import com.example.wen0m.sampleapp.mvp.models.Fighter;
import com.google.android.gms.maps.model.LatLng;

public interface DetailsMvpView extends BaseMvpView{
    void showDetails(Fighter building);
    void showMap(LatLng position);
    void showFavoritedButton();
    void showUnFavoritedButton();
//    void changeFavBtnState();
}
