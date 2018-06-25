package com.example.wen0m.sampleapp.base;


import com.arellomobile.mvp.MvpView;

public interface BaseMvpView extends MvpView {
    void showLoading();
    void hideLoading();
    void showErrorMessage(String msg);
}
