package com.example.wen0m.sampleapp.shared.base;


public interface BaseMvpView {
    void showLoading();
    void hideLoading();
    void showErrorMessage(String msg);
    boolean isNetworkConnected();
}
