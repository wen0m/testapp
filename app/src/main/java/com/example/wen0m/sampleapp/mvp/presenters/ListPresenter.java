package com.example.wen0m.sampleapp.mvp.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.example.wen0m.sampleapp.MyApplication;
import com.example.wen0m.sampleapp.base.BasicPresenter;
import com.example.wen0m.sampleapp.mvp.models.Fighter;
import com.example.wen0m.sampleapp.mvp.views.ListingMvpView;
import com.example.wen0m.sampleapp.shared.SortType;
import com.example.wen0m.sampleapp.shared.UfcApiService;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;


@InjectViewState
public class ListPresenter extends BasicPresenter<ListingMvpView> {
    @Inject
    public UfcApiService webService;

    public ListPresenter() {
        MyApplication.getInstance().getAppComponent().inject(this);
    }

    public void showHouseList() {
        getViewState().showLoading();

        Subscription subscription = webService.getFightersList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Fighter>>() {
                    @Override
                    public void onCompleted() {
                        getViewState().hideLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        getViewState().hideLoading();
                        getViewState().showErrorMessage(e.getLocalizedMessage());
                        // желательно проверить какая именно ошибка, в моем случае имелось в виду, что при внезапном отсутствии инета,
                        // данные загрузились бы из БД, но для тестового задания подойдет
                    }

                    @Override
                    public void onNext(List<Fighter> hotels) {
                        getViewState().hideLoading();
                        Collections.sort(hotels, SortType.getComparator(getSharedPrefsUtil().getOption()));
                        getViewState().showHousesList(hotels);
                    }
                });

        addSubscription(subscription);
    }


}
