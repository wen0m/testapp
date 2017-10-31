package com.example.wen0m.sampleapp.detailed;

import com.example.wen0m.sampleapp.models.Building;
import com.example.wen0m.sampleapp.shared.base.BasePresenter;
import com.example.wen0m.sampleapp.utils.NetworkUtils;
import com.example.wen0m.sampleapp.utils.SharedPrefsUtil;
import com.google.android.gms.maps.model.LatLng;
import javax.inject.Inject;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;


public class DetailsPresenter<V extends DetailsMvpView> extends BasePresenter<V> implements DetailsMvpPresenter {

    @Inject
    public DetailsPresenter(SharedPrefsUtil prefs, NetworkUtils nu, CompositeSubscription cs) {
        super(prefs, nu, cs);
    }


    @Override
    public void displayHouseDetails(Long buildingId) {
        getSubscription().add(
                getNetworkUtils().getHotelDetailed(buildingId)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<Building>() {
                            @Override
                            public void onCompleted() {
                            }

                            @Override
                            public void onError(Throwable e) {
                                getMvpView().showErrorMessage(e.getLocalizedMessage());
                            }

                            @Override
                            public void onNext(Building hotel) {
                                getMvpView().showDetails(hotel);
                                getMvpView().showMap(new LatLng(hotel.getLatitude(), hotel.getLongitude()));
                            }
                        })
        );
    }


    @Override
    public void changeFavoriteButton(Long buildingId)
    {
        if (isViewAttached())
        {
            if (getSharedPrefsUtil().isFavourite(buildingId))
            {
                getMvpView().showFavoritedButton();
            } else
            {
                getMvpView().showUnFavoritedButton();
            }
        }
    }

    @Override
    public void favoriteBtnClicked(Long buildingId)
    {
        if (isViewAttached())
        {
            if (getSharedPrefsUtil().isFavourite(buildingId))
            {
                getSharedPrefsUtil().setUnFavourite(buildingId);
                getMvpView().showUnFavoritedButton();
            } else
            {
                getSharedPrefsUtil().setFavourite(buildingId);
                getMvpView().showFavoritedButton();
            }
        }
    }

}
