package com.example.wen0m.sampleapp.mvp.presenters;


import com.example.wen0m.sampleapp.base.BasicPresenter;
import com.example.wen0m.sampleapp.mvp.views.SortDialogMvpView;

public interface SortDialogMvpPresenter {
    void attachView(SortDialogMvpView view);
    void onPriceOptionSelected();
    void onApartsCountSelected();
    void onReleaseDateSelected();
    void setLastSavedOption();
    void stop();
}
