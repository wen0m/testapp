package com.example.wen0m.sampleapp.listing;


import com.example.wen0m.sampleapp.shared.base.BaseMvpPresenter;

public interface ListingMvpPresenter<V extends ListingMvpView> extends BaseMvpPresenter<V> {
    void displayHouseList();
}
