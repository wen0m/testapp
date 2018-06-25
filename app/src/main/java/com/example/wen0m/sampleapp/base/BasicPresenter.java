package com.example.wen0m.sampleapp.base;


import com.arellomobile.mvp.MvpPresenter;
import com.example.wen0m.sampleapp.shared.SharedPrefsUtil;
import org.greenrobot.greendao.annotation.NotNull;
import javax.inject.Inject;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class BasicPresenter<View extends BaseMvpView> extends MvpPresenter<View> {
    @Inject
    SharedPrefsUtil sharedPrefsUtil;

    private CompositeSubscription subscription = new CompositeSubscription();

//    private final SharedPrefsUtil sharedPrefsUtil;
//    private final BuildingsApiService buildingsApiService;
//
//    @Inject
//    public BasicPresenter(SharedPrefsUtil prefs, BuildingsApiService nu, CompositeSubscription cs) {
//        this.sharedPrefsUtil = prefs;
//        this.buildingsApiService = nu;
//        this.subscription = cs;
//    }


    public void addSubscription(@NotNull Subscription subscription) {
        this.subscription.add(subscription);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        subscription.clear();
    }

//    public BuildingsApiService getBuildingsApiService() {
//        return this.buildingsApiService;
//    }
//
    public SharedPrefsUtil getSharedPrefsUtil() {
        return this.sharedPrefsUtil;
    }
}
