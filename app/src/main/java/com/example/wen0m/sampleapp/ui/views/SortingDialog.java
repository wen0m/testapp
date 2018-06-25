package com.example.wen0m.sampleapp.ui.views;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.arellomobile.mvp.MvpAppCompatDialogFragment;
import com.arellomobile.mvp.MvpDialogFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;
import com.example.wen0m.sampleapp.deprecated.ListingPresenter;
import com.example.wen0m.sampleapp.R;
import com.example.wen0m.sampleapp.mvp.presenters.ListPresenter;
import com.example.wen0m.sampleapp.mvp.presenters.SortingPresenter;
import com.example.wen0m.sampleapp.mvp.views.SortDialogMvpView;

import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class SortingDialog extends MvpAppCompatDialogFragment implements SortDialogMvpView, RadioGroup.OnCheckedChangeListener {

//    @InjectPresenter(type = PresenterType.GLOBAL, tag = "SortingPresenter")
    SortingPresenter sortingPresenter;

//    @InjectPresenter(type = PresenterType.GLOBAL, tag = "ListingPresenter")
    ListPresenter housePresenter;

    @BindView(R.id.sort_by_apartments) RadioButton apartOption;
    @BindView(R.id.sort_by_date) RadioButton dateOption;
    @BindView(R.id.sort_by_price) RadioButton priceOption;
    @BindView(R.id.sorting_group) RadioGroup sortingOptionsGroup;

    private Unbinder unbinder;
//    private static ListingPresenter housePresenter;

    public static SortingDialog newInstance()
    {
//        SortingDialog.housePresenter = presenter;
        return new SortingDialog();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
//        sortingPresenter.attachView(this);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.sorting_dialog, null);
        unbinder = ButterKnife.bind(this, dialogView);
        sortingPresenter.setLastSavedOption();
        sortingOptionsGroup.setOnCheckedChangeListener(this);
        Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(dialogView);
        dialog.setTitle(R.string.sort_menu_title);
        dialog.show();
        return dialog;
    }

    @Override
    public void setPriceChecked() {
        priceOption.setChecked(true);
    }

    @Override
    public void setApartCountChecked() {
        apartOption.setChecked(true);
    }

    @Override
    public void setReleaseDateChecked() {
        dateOption.setChecked(true);
    }

    @Override
    public void dismissDialog() {
        dismiss();
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
        switch (i)
        {
            case R.id.sort_by_price:
                sortingPresenter.onPriceOptionSelected();
                housePresenter.showHouseList();
                break;

            case R.id.sort_by_date:
                sortingPresenter.onReleaseDateSelected();
                housePresenter.showHouseList();
                break;

            case R.id.sort_by_apartments:
                sortingPresenter.onApartsCountSelected();
                housePresenter.showHouseList();
                break;
        }
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        unbinder.unbind();
        unbinder = null;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showErrorMessage(String msg) {

    }
}
