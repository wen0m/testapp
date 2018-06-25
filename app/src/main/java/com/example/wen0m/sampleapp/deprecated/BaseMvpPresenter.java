package com.example.wen0m.sampleapp.deprecated;


import com.example.wen0m.sampleapp.base.BaseMvpView;

@Deprecated
public interface BaseMvpPresenter<V extends BaseMvpView> {

    void attachView(V view);

    void detachView();

    boolean isViewAttached();
}
