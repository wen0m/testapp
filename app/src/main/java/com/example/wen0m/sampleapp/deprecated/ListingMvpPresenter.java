package com.example.wen0m.sampleapp.deprecated;


import com.example.wen0m.sampleapp.mvp.views.ListingMvpView;

@Deprecated
public interface ListingMvpPresenter<V extends ListingMvpView> extends BaseMvpPresenter<V> {
    void displayHouseList();
}
