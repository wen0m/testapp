package com.example.wen0m.sampleapp.listing.sorting;



public interface SortDialogMvpPresenter {
    void attachView(SortDialogMvpView view);
    void onPriceOptionSelected();
    void onApartsCountSelected();
    void onReleaseDateSelected();
    void setLastSavedOption();
    void stop();
}
