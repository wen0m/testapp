package com.example.wen0m.sampleapp.shared.base;


public interface BaseMvpPresenter<V extends BaseMvpView> {

    void attachView(V view);

    void detachView();

    boolean isViewAttached();
}
