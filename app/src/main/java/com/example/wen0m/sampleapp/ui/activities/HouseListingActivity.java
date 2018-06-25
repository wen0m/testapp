package com.example.wen0m.sampleapp.ui.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatDialogFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;
import com.example.wen0m.sampleapp.mvp.models.Fighter;
import com.example.wen0m.sampleapp.mvp.presenters.ListPresenter;
import com.example.wen0m.sampleapp.shared.Constans;
import com.example.wen0m.sampleapp.ui.adapters.ListingAdapter;
import com.example.wen0m.sampleapp.R;
import com.example.wen0m.sampleapp.mvp.views.ListingMvpView;
import com.example.wen0m.sampleapp.base.BaseActivity;
import com.example.wen0m.sampleapp.ui.views.SortingDialog;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;


public class HouseListingActivity extends BaseActivity implements ListingMvpView, ListingAdapter.Callback {
    private static final String PACKAGE = "com.example.wen0m.sampleapp";
    private static final String SAVED_LAYOUT_MANAGER = "layout-manager-state";
    private Parcelable rwPosition;

    @InjectPresenter(type = PresenterType.GLOBAL, tag = "ListingPresenter")
    ListPresenter presenter;

    @Inject
    ListingAdapter adapter;

    @BindView(R.id.hotel_lst) RecyclerView recyclerView;
    @BindView(R.id.toolbar) Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotels);
        setUnBinder(ButterKnife.bind(this));

        setUpViews();

        presenter.showHouseList();
        restorePosition();
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            rwPosition = savedInstanceState.getBundle(SAVED_LAYOUT_MANAGER);
        }
        super.onRestoreInstanceState(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
        recyclerView.getLayoutManager().onRestoreInstanceState(rwPosition);

    }

//    @Override
//    public void onPause() {
//        super.onPause();
//        rwPosition = recyclerView.getLayoutManager().onSaveInstanceState();
//        Toast.makeText(this, "Pause", Toast.LENGTH_SHORT).show();
//    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        rwPosition = recyclerView.getLayoutManager().onSaveInstanceState();
        outState.putParcelable(SAVED_LAYOUT_MANAGER, rwPosition);
        super.onSaveInstanceState(outState);
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
    public void showHousesList(List<Fighter> buildings) {
        adapter = new ListingAdapter(new ArrayList<Fighter>(Constans.ELEMENTS_CNT));
        adapter.setCallback(this);
        adapter.addItems(buildings);
//        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

        recyclerView.setVisibility(View.VISIBLE);

    }

    @Override
    public void onHouseClicked(Long buildId) {
//        HouseDetailsActivity.start(this, buildId);
    }


    @Override
    public void onEmptyListRetryClick() {
    }

    @Override
    public void onItemClicked(long Id) {
        this.onHouseClicked(Id);
    }

    @Override
    public void fighterClicked(ListingAdapter.PictureData data) {
        Intent subActivity = new Intent(HouseListingActivity.this, HouseDetailsActivity.class);
        int orientation = getResources().getConfiguration().orientation;
        subActivity.
                putExtra("orientation", orientation).
                putExtra("resourceId", data.getResourceId()).
                putExtra("left", data.getLeft()).
                putExtra("top", data.getTop()).
                putExtra("width", data.getWidth()).
                putExtra("height", data.getHeight()).
                putExtra("description", data.getDescription());
        startActivity(subActivity);
        // Override transitions: we don't want the normal window animation in addition
        // to our custom one
        overridePendingTransition(0, 0);
    }


    private void setUpViews() {
        setSupportActionBar(toolbar);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setHasFixedSize(true);
    }

    private void showSortingDialog() {
        MvpAppCompatDialogFragment sortDialog = SortingDialog.newInstance();
        sortDialog.show(getSupportFragmentManager(), "Something");
    }

    private void restorePosition() {
        if (rwPosition != null) {
            new Handler().postDelayed(new Runnable() {
                                          @Override
                                          public void run() {
                                              recyclerView.getLayoutManager().onRestoreInstanceState(rwPosition);

                                          }
                                      }, 200);
            rwPosition = null;
        }
    }

}
