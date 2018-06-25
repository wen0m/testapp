package com.example.wen0m.sampleapp.shared;

import com.example.wen0m.sampleapp.MyApplication;
import com.example.wen0m.sampleapp.mvp.models.Fighter;
import java.util.List;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


public class UfcApiService {
    private ApiUfcEndpoint webService;

    public UfcApiService(ApiUfcEndpoint service){
        webService = service;
    }

    public Observable<List<Fighter>> getFightersList() {
        return  webService.getFightersList()
                .doOnNext(new Action1<List<Fighter>>() {
                    @Override
                    public void call(List<Fighter> buildings) {
                        saveData(buildings);
                    }
                }).subscribeOn(Schedulers.io());
    }

    public Observable<Fighter> getFighterDetailed(final long id) {

        return webService.getFighterDetailed(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
//        return Observable.create(new Observable.OnSubscribe<Fighter>() {
//            @Override
//            public void call(Subscriber<? super Fighter> subscriber) {
//                subscriber.onNext(MyApplication.getInstance().getSession().getFighterDao().load(id));
//            }
//        });
    }

    private static void saveData(List<Fighter> res) {
        MyApplication.getInstance().getSession().getFighterDao().insertOrReplaceInTx(res);
    }
}
