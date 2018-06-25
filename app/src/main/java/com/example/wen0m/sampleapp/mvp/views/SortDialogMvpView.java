package com.example.wen0m.sampleapp.mvp.views;


import com.arellomobile.mvp.MvpView;
import com.example.wen0m.sampleapp.base.BaseMvpView;

public interface SortDialogMvpView extends BaseMvpView{
    void setPriceChecked();
    void setApartCountChecked();
    void setReleaseDateChecked();
    void dismissDialog();
}
