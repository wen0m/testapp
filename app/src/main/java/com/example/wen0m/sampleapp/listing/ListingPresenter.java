package com.example.wen0m.sampleapp.listing;

import com.example.wen0m.sampleapp.models.Building;
import com.example.wen0m.sampleapp.listing.sorting.SortType;
import com.example.wen0m.sampleapp.shared.base.BasePresenter;
import com.example.wen0m.sampleapp.utils.NetworkUtils;
import com.example.wen0m.sampleapp.utils.SharedPrefsUtil;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;


public class ListingPresenter<V extends ListingMvpView> extends BasePresenter<V> implements ListingMvpPresenter<V> {

    @Inject
    public ListingPresenter(SharedPrefsUtil prefs, NetworkUtils nu, CompositeSubscription cs) {
        super(prefs, nu, cs);
    }

    public void displayHouseList() {
        if (isViewAttached())
        {
            getMvpView().showLoading();
            getSubscription().add(
                    getNetworkUtils().getHotelList().observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Subscriber<List<Building>>() {
                                @Override
                                public void onCompleted() {
                                    getMvpView().hideLoading();
                                }

                                @Override
                                public void onError(Throwable e) {
                                    getMvpView().hideLoading();
                                    getMvpView().showErrorMessage(e.getLocalizedMessage());
                                    // желательно проверить какая именно ошибка, в моем случае имелось в виду, что при внезапном отсутствии инета,
                                    // данные загрузились бы из БД, но для тестового задания подойдет

                                }

                                @Override
                                public void onNext(List<Building> hotels) {
                                    getMvpView().hideLoading();
                                    Collections.sort(hotels, SortType.getComparator(getSharedPrefsUtil().getOption()));
                                    getMvpView().showHousesList(hotels);
                                }
                            })
            );
        }

    }
}
