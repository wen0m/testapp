package com.example.wen0m.sampleapp.mvp.presenters;


import com.example.wen0m.sampleapp.MyApplication;
import com.example.wen0m.sampleapp.base.BasicPresenter;
import com.example.wen0m.sampleapp.shared.SharedPrefsUtil;
import javax.inject.Inject;


public class FavouritesPresenter {
    @Inject
    SharedPrefsUtil sharedPrefsUtil;

    public FavouritesPresenter() {
        MyApplication.getInstance().getAppComponent().inject(this);
    }

    public void setFavorite(long id) {
        sharedPrefsUtil.setFavourite(id);
    }

    public void unFavorite(long id) {
        sharedPrefsUtil.setUnFavourite(id);
    }

    public boolean isFavorite(long id) {
        return sharedPrefsUtil.isFavourite(id);
    }
}
