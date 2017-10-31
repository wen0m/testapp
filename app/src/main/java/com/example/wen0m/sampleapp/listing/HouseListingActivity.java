package com.example.wen0m.sampleapp.listing;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.example.wen0m.sampleapp.detailed.HouseDetailsActivity;
import com.example.wen0m.sampleapp.listing.sorting.SortingDialog;
import com.example.wen0m.sampleapp.models.Building;
import com.example.wen0m.sampleapp.R;
import com.example.wen0m.sampleapp.shared.base.BaseActivity;
import com.example.wen0m.sampleapp.shared.MyApplication;
import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;


public class HouseListingActivity extends BaseActivity implements ListingMvpView, ListingAdapter.Callback {

    @Inject
    ListingPresenter presenter;
    @Inject
    ListingAdapter adapter;

    @BindView(R.id.hotel_lst) RecyclerView recyclerView;
    @BindView(R.id.toolbar) Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MyApplication) getApplication()).createListingComponent().inject(this);
        setContentView(R.layout.activity_hotels);
        setUnBinder(ButterKnife.bind(this));
        setUpViews();
        adapter.setCallback(this);
        presenter.attachView(this);
        presenter.displayHouseList();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_hotels, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.search_action).getActionView();
        searchView.setQueryHint(getResources().getText(R.string.coming_soon));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.sort_action:
                showSortingDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
        ((MyApplication) getApplication()).releaseListingComponent();
    }

    @Override
    public void showHousesList(List<Building> buildings) {
        adapter.addItems(buildings);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onHouseClicked(Long buildId) {
        HouseDetailsActivity.start(this, buildId);
    }


    @Override
    public void onEmptyListRetryClick() {

    }

    @Override
    public void onItemClicked(long Id) {
        this.onHouseClicked(Id);
    }


    private void setUpViews() {
        setSupportActionBar(toolbar);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
    }

    private void showSortingDialog() {
        DialogFragment sortDialog = SortingDialog.newInstance(presenter);
        sortDialog.show(getSupportFragmentManager(), "Something");
    }

}
