package com.example.wen0m.sampleapp.shared;

import com.example.wen0m.sampleapp.deprecated.Building;
import com.example.wen0m.sampleapp.MyApplication;
import java.util.List;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

@Deprecated
public class BuildingsApiService {

    private ApiHotelEndpoint webService;

    public BuildingsApiService(ApiHotelEndpoint service){
        webService = service;
    }

    public Observable<List<Building>> getHotelList() {
        return  webService.getHotelsList()
                .doOnNext(new Action1<List<Building>>() {
                    @Override
                    public void call(List<Building> buildings) {
                        saveData(buildings);
                    }
                }).subscribeOn(Schedulers.io());
    }

    public Observable<Building> getHotelDetailed(final long id) {
        return Observable.create(new Observable.OnSubscribe<Building>() {
            @Override
            public void call(Subscriber<? super Building> subscriber) {
//                subscriber.onNext(MyApplication.getInstance().getSession().getBuildingDao().load(id));
            }
        });
    }

    private static Observable<List<Building>> databaseObservable() {
        return Observable.create(new Observable.OnSubscribe<List<Building>>() {
            @Override
            public void call(Subscriber<? super List<Building>> subscriber) {
//                subscriber.onNext(MyApplication.getInstance().getSession().getBuildingDao().loadAll());
                subscriber.onCompleted();
            }
        });
    }


    private static Observable<List<Building>> networkObservable() {
        return Observable.create(new Observable.OnSubscribe<List<Building>>() {
            @Override
            public void call(Subscriber<? super List<Building>> subscriber) {
//                subscriber.onNext(ApiHotelClient.getClient().create(ApiHotelClient.ApiHotelEndpoints.class).getHotelsList());
            }
        });
    }

    private static void saveData(List<Building> res) {
//        MyApplication.getInstance().getSession().getBuildingDao().insertOrReplaceInTx(res);
    }

    //    public static Observable<List<Building>> getHotelList() {
//        //проверяем есть ли данные в бд, если нет, тогда идем за ними в сеть и затем сохраняем в бд,
//        //тут не учитывается актуальность загруженных данных, но в задании об этом ничего не сказано,
//        //поэтому оставлю в упрощенном виде, в реальной системе необходимо учесть этот момент и добавить соответствующую проверку
//        return Observable.mergeDelayError(
//                databaseObservable().subscribeOn(Schedulers.io()),
//                networkObservable().doOnNext(new Action1<List<Building>>() {
//                    @Override
//                    public void call(List<Building> hotels) {
//                        saveData(hotels);
//                    }
//                })
//        ).subscribeOn(Schedulers.io());
//    }
//    public Observable<Building> getHotelDetailed(final long id) {
//        return webService.getHotelDetailed(id).doOnNext(new Action1<Building>() {
//            @Override
//            public void call(Building building) {
//                MyApplication.getInstance().getSession().getBuildingDao().load(id);
//            }
//        }).subscribeOn(Schedulers.io());
//    }

}
