package com.example.wen0m.sampleapp.mvp.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;
import com.example.wen0m.sampleapp.MyApplication;
import com.example.wen0m.sampleapp.base.BasicPresenter;
import com.example.wen0m.sampleapp.mvp.models.Fighter;
import com.example.wen0m.sampleapp.mvp.views.DetailsMvpView;
import com.example.wen0m.sampleapp.shared.UfcApiService;
import com.google.android.gms.maps.model.LatLng;
import javax.inject.Inject;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;


@InjectViewState
public class DetailPresenter extends BasicPresenter<DetailsMvpView> {

    @Inject
    public UfcApiService webService;

//    @Inject
//    public FavouritesPresenter  favouritesPresenter;


    public DetailPresenter() {
        MyApplication.getInstance().getAppComponent().inject(this);
    }

    public void displayHouseDetails(Long buildingId) {
        Subscription subscription = webService.getFighterDetailed(buildingId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Fighter>() {
                    @Override
                    public void onCompleted() {
                    }
                    @Override
                    public void onError(Throwable e) {
                        getViewState().showErrorMessage(e.getLocalizedMessage());
                    }
                    @Override
                    public void onNext(Fighter hotel) {
                        getViewState().showDetails(hotel);
                        getViewState().showMap(new LatLng(0.0, 0));
                    }
                });

        addSubscription(subscription);
    }


    public void changeFavoriteButton(Long buildingId)
    {
//        boolean isFavorite = favouritesPresenter.isFavorite(buildingId);
//        if (isFavorite)
//        {
//            getViewState().showFavoritedButton();
//        } else
//        {
//            getViewState().showUnFavoritedButton();
//        }
    }

    public void favoriteBtnClicked(Long buildingId)
    {
//        boolean isFavorite = favouritesPresenter.isFavorite(buildingId);
//        if (isFavorite)
//        {
//            favouritesPresenter.setFavorite(buildingId);
//            getViewState().showUnFavoritedButton();
//        } else
//        {
//            favouritesPresenter.setFavorite(buildingId);
//            getViewState().showFavoritedButton();
//        }
    }
}
