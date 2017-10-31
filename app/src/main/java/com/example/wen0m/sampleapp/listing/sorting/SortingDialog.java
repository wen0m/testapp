package com.example.wen0m.sampleapp.listing.sorting;

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
import com.example.wen0m.sampleapp.listing.ListingPresenter;
import com.example.wen0m.sampleapp.R;
import com.example.wen0m.sampleapp.shared.MyApplication;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class SortingDialog extends DialogFragment implements SortDialogMvpView, RadioGroup.OnCheckedChangeListener {

    @Inject
    SortingPresenter sortingPresenter;

    @BindView(R.id.sort_by_apartments) RadioButton apartOption;
    @BindView(R.id.sort_by_date) RadioButton dateOption;
    @BindView(R.id.sort_by_price) RadioButton priceOption;
    @BindView(R.id.sorting_group) RadioGroup sortingOptionsGroup;

    private Unbinder unbinder;
    private static ListingPresenter housePresenter;

    public static SortingDialog newInstance(ListingPresenter presenter)
    {
        SortingDialog.housePresenter = presenter;
        return new SortingDialog();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        ((MyApplication) getActivity().getApplication()).getListingComponent().inject(this);
        sortingPresenter.attachView(this);
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
                housePresenter.displayHouseList();
                break;

            case R.id.sort_by_date:
                sortingPresenter.onReleaseDateSelected();
                housePresenter.displayHouseList();
                break;

            case R.id.sort_by_apartments:
                sortingPresenter.onApartsCountSelected();
                housePresenter.displayHouseList();
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
}
