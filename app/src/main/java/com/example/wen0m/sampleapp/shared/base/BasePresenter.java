package com.example.wen0m.sampleapp.shared.base;


import com.example.wen0m.sampleapp.utils.NetworkUtils;
import com.example.wen0m.sampleapp.utils.SharedPrefsUtil;
import java.lang.ref.WeakReference;
import javax.inject.Inject;
import rx.subscriptions.CompositeSubscription;


public class BasePresenter<V extends BaseMvpView> implements BaseMvpPresenter<V> {

    private WeakReference<V> view;

    private final CompositeSubscription subscription;
    private final SharedPrefsUtil sharedPrefsUtil;
    private final NetworkUtils networkUtils;

    @Inject
    public BasePresenter(SharedPrefsUtil prefs, NetworkUtils nu, CompositeSubscription cs) {
        this.sharedPrefsUtil = prefs;
        this.networkUtils = nu;
        this.subscription = cs;
    }

    @Override
    public void attachView(V view) {
        this.view = new WeakReference<>(view);
    }

    @Override
    public void detachView() {
        subscription.unsubscribe();
        view = null;
    }

    @Override
    public boolean isViewAttached() {
        return view != null;
    }

    public V getMvpView() {
        return view.get();
    }

    public CompositeSubscription getSubscription() {
        return this.subscription;
    }

    public NetworkUtils getNetworkUtils() {
        return this.networkUtils;
    }

    public SharedPrefsUtil getSharedPrefsUtil() {
        return this.sharedPrefsUtil;
    }
}
