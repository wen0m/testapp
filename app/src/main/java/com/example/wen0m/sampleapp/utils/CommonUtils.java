package com.example.wen0m.sampleapp.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.example.wen0m.sampleapp.R;
import jp.wasabeef.picasso.transformations.CropTransformation;
import rx.Subscription;


public abstract class CommonUtils {

    public static CropTransformation getTransform() {
        return new CropTransformation((float) 16 / (float) 8,
                CropTransformation.GravityHorizontal.CENTER,
                CropTransformation.GravityVertical.CENTER);
    }

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    public static ProgressDialog showLoadingDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.show();
        if (progressDialog.getWindow() != null) {
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;
    }

//    public static Observable<List<Building>> getHotelList() {
//        return  ApiHotelClient.getClient().create(ApiHotelClient.ApiHotelEndpoints.class).getHotelsList()
//                .doOnNext(new Action1<List<Building>>() {
//                    @Override
//                    public void call(List<Building> buildings) {
//                        saveData(buildings);
//                    }
//        }).subscribeOn(Schedulers.io());
//    }
//
//    public static Observable<Building> getHotelDetailed(final long id) {
//        return Observable.create(new Observable.OnSubscribe<Building>() {
//            @Override
//            public void call(Subscriber<? super Building> subscriber) {
//                subscriber.onNext(MyApplication.getInstance().getSession().getBuildingDao().load(id));
//            }
//        });
//    }
//
//
//    private static Observable<List<Building>> databaseObservable() {
//        return Observable.create(new Observable.OnSubscribe<List<Building>>() {
//            @Override
//            public void call(Subscriber<? super List<Building>> subscriber) {
//                subscriber.onNext(MyApplication.getInstance().getSession().getBuildingDao().loadAll());
//                subscriber.onCompleted();
//            }
//        });
//    }
//
//
//    private static Observable<List<Building>> networkObservable() {
//        return Observable.create(new Observable.OnSubscribe<List<Building>>() {
//            @Override
//            public void call(Subscriber<? super List<Building>> subscriber) {
////                subscriber.onNext(ApiHotelClient.getClient().create(ApiHotelClient.ApiHotelEndpoints.class).getHotelsList());
//            }
//        });
//    }
//
//    private static void saveData(List<Building> res) {
//        MyApplication.getInstance().getSession().getBuildingDao().insertOrReplaceInTx(res);
//    }

    public static void unsubscribe(Subscription subscription)
    {
        if (subscription != null)
        {
            if (!subscription.isUnsubscribed())
            {
                subscription.unsubscribe();
            } else
            {
                // уже отписались
            }
        } else
        {
            // подписки не было совсем
        }
    }


}
