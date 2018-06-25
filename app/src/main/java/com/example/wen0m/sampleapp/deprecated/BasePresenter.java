package com.example.wen0m.sampleapp.deprecated;


import com.example.wen0m.sampleapp.base.BaseMvpView;
import com.example.wen0m.sampleapp.shared.BuildingsApiService;
import com.example.wen0m.sampleapp.shared.SharedPrefsUtil;
import java.lang.ref.WeakReference;
import javax.inject.Inject;
import rx.subscriptions.CompositeSubscription;

@Deprecated
public class BasePresenter<V extends BaseMvpView> implements BaseMvpPresenter<V> {

    private WeakReference<V> view;

    private final CompositeSubscription subscription;
    private final SharedPrefsUtil sharedPrefsUtil;
    private final BuildingsApiService buildingsApiService;

    @Inject
    public BasePresenter(SharedPrefsUtil prefs, BuildingsApiService nu, CompositeSubscription cs) {
        this.sharedPrefsUtil = prefs;
        this.buildingsApiService = nu;
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

    public BuildingsApiService getBuildingsApiService() {
        return this.buildingsApiService;
    }

    public SharedPrefsUtil getSharedPrefsUtil() {
        return this.sharedPrefsUtil;
    }
}
