package com.example.wen0m.sampleapp.mvp.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.example.wen0m.sampleapp.MyApplication;
import com.example.wen0m.sampleapp.base.BasicPresenter;
import com.example.wen0m.sampleapp.mvp.views.SortDialogMvpView;
import com.example.wen0m.sampleapp.shared.SortOption;
import com.example.wen0m.sampleapp.shared.SharedPrefsUtil;
import javax.inject.Inject;

@InjectViewState
public class SortingPresenter extends BasicPresenter<SortDialogMvpView> {
    @Inject
    public SharedPrefsUtil sortingOptionsStore;

    public SortingPresenter() {
        MyApplication.getInstance().getAppComponent().inject(this);
    }


    public void onPriceOptionSelected() {
        if(true) {
            sortingOptionsStore.setSortingOption(SortOption.BY_PRICE);
            getViewState().dismissDialog();
        }

    }

    public void onApartsCountSelected() {
        if(true) {
            sortingOptionsStore.setSortingOption(SortOption.BY_APARTS);
            getViewState().dismissDialog();
        }
    }

    public void onReleaseDateSelected() {
        if(true) {
            sortingOptionsStore.setSortingOption(SortOption.BY_NAME);
            getViewState().dismissDialog();
        }
    }

    public void setLastSavedOption() {
        if (true)
        {
            int selectedOption = sortingOptionsStore.getOption();

            if (selectedOption == SortOption.BY_PRICE.getOption())
            {
                getViewState().setPriceChecked();
            } else if (selectedOption ==  SortOption.BY_APARTS.getOption())
            {
                getViewState().setApartCountChecked();
            } else
            {
                getViewState().setReleaseDateChecked();
            }
        }
    }
}
