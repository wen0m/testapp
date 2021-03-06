package com.example.wen0m.sampleapp.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;
import com.arellomobile.mvp.MvpActivity;
import com.arellomobile.mvp.MvpAppCompatActivity;
import com.example.wen0m.sampleapp.R;
import com.example.wen0m.sampleapp.shared.CommonUtils;
import butterknife.Unbinder;


public class BaseActivity extends MvpAppCompatActivity implements BaseMvpView {
    private Unbinder mUnBinder;
    private ProgressDialog mProgressDialog;


    public void setUnBinder(Unbinder unBinder) {
        mUnBinder = unBinder;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_out_back_to_left, R.anim.slide_in_back_from_right);
    }

    @Override
    public void showLoading() {
        hideLoading();
        mProgressDialog = CommonUtils.showLoadingDialog(this);
    }

    @Override
    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }

    @Override
    public void showErrorMessage(String msg) {
        Log.e("TAG666", msg);
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public boolean isNetworkConnected() {
        return CommonUtils.isNetworkConnected(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right);
    }


}
