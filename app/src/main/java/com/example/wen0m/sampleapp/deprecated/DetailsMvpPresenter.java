package com.example.wen0m.sampleapp.deprecated;


@Deprecated
public interface DetailsMvpPresenter {
    void displayHouseDetails(Long buildingId);

    void favoriteBtnClicked(Long buildingId);
    void changeFavoriteButton(Long buildingId);
}
