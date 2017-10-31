package com.example.wen0m.sampleapp.detailed;


import com.example.wen0m.sampleapp.models.Building;
import com.example.wen0m.sampleapp.shared.base.BaseMvpView;
import com.google.android.gms.maps.model.LatLng;

public interface DetailsMvpView extends BaseMvpView{
    void showDetails(Building building);
    void showMap(LatLng position);
    void showFavoritedButton();
    void showUnFavoritedButton();
}
