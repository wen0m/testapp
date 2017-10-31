package com.example.wen0m.sampleapp.listing.sorting;

import com.example.wen0m.sampleapp.utils.SharedPrefsUtil;


public class SortingPresenter implements SortDialogMvpPresenter {
    private SortDialogMvpView view;
    private SharedPrefsUtil sortingOptionsStore;

    public SortingPresenter(SharedPrefsUtil sharedPrefs) {
        sortingOptionsStore = sharedPrefs;
    }

    private boolean isViewAttached()
    {
        return view != null;
    }


    @Override
    public void attachView(SortDialogMvpView view) {
        this.view = view;
    }

    @Override
    public void onPriceOptionSelected() {
        if(isViewAttached()) {
            sortingOptionsStore.setSortingOption(SortOption.BY_PRICE);
            view.dismissDialog();
        }

    }

    @Override
    public void onApartsCountSelected() {
        if(isViewAttached()) {
            sortingOptionsStore.setSortingOption(SortOption.BY_APARTS);
            view.dismissDialog();
        }
    }

    @Override
    public void onReleaseDateSelected() {
        if(isViewAttached()) {
            sortingOptionsStore.setSortingOption(SortOption.BY_NAME);
            view.dismissDialog();
        }
    }

    @Override
    public void setLastSavedOption() {
        if (isViewAttached())
        {
            int selectedOption = sortingOptionsStore.getOption();

            if (selectedOption == SortOption.BY_PRICE.getOption())
            {
                view.setPriceChecked();
            } else if (selectedOption ==  SortOption.BY_APARTS.getOption())
            {
                view.setApartCountChecked();
            } else
            {
                view.setReleaseDateChecked();
            }
        }
    }

    @Override
    public void stop() {
        this.view = null;
    }
}
