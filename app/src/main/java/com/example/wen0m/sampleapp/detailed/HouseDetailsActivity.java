package com.example.wen0m.sampleapp.detailed;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.wen0m.sampleapp.models.Building;
import com.example.wen0m.sampleapp.R;
import com.example.wen0m.sampleapp.shared.base.BaseActivity;
import com.example.wen0m.sampleapp.shared.FloatingButtonListener;
import com.example.wen0m.sampleapp.shared.MyApplication;
import com.example.wen0m.sampleapp.shared.base.CustomMapFragment;
import com.example.wen0m.sampleapp.utils.CommonUtils;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.squareup.picasso.Picasso;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HouseDetailsActivity extends BaseActivity implements DetailsMvpView {

    @Inject DetailsPresenter presenter;

    @BindView(R.id.thumbnail) ImageView hotelImg;
    @BindView(R.id.name) TextView houseName;
    @BindView(R.id.finishing) TextView finishingOpts;
    @BindView(R.id.deadline_date) TextView deadlineDay;
    @BindView(R.id.aparts_count) TextView aparts;
    @BindView(R.id.phone_fab) FloatingActionButton fab;
    @BindView(R.id.app_bar) AppBarLayout appbar;
    @BindView(R.id.collapse_toolbar) CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.coordinator) CoordinatorLayout coordinator;

    private Menu collapsedMenu;
    private Animation shrinkAnimation, growAnimation;
    private boolean appBarExpanded = true;


    public static void start(Context context, Long val1) {
        Intent starter = new Intent(context, HouseDetailsActivity.class);
        starter.putExtra("buildingId", val1);
        starter.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(starter);
    }

    private void phoneCallIntent(Context context) {
        Intent caller = new Intent(Intent.ACTION_DIAL, Uri.parse("tel: +66666666"));
        caller.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(caller);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MyApplication) getApplication()).createDetailsComponent().inject(this);
        setContentView(R.layout.activity_hotel_detail);
        setUnBinder(ButterKnife.bind(this));
        setUpViews();
        presenter.attachView(this);
        presenter.displayHouseDetails(getIntent().getLongExtra("buildingId", 0));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
            case R.id.fav_action:
                presenter.favoriteBtnClicked(getIntent().getLongExtra("buildingId", 0));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (collapsedMenu != null && (!appBarExpanded || collapsedMenu.size() != 1)) {
            //collapsed
            collapsedMenu.add("Phone")
                    .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            phoneCallIntent(getApplicationContext());
                            return true;
                        }
                    })
                    .setIcon(R.drawable.common_full_open_on_phone)
                    .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        }
        return super.onPrepareOptionsMenu(collapsedMenu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        collapsedMenu = menu;
        presenter.changeFavoriteButton(getIntent().getLongExtra("buildingId", 0));
        return true;
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
        ((MyApplication) getApplication()).releaseDetailsComponent();
    }


    @OnClick(R.id.phone_fab)
    public void onClick() {
        phoneCallIntent(getApplicationContext());
    }

    @Override
    public void showDetails(Building building) {
        if(building != null) {
            Picasso.with(this).load(building.getImage())
                    .transform(CommonUtils.getTransform())
                    .error(R.drawable.common_full_open_on_phone)
                    .into(hotelImg);
            houseName.setText(building.getAddress() + " ближ. метро - " + building.getSubways().get(0).getName());
            finishingOpts.setText("Отделка: " + building.getFinishing());
            deadlineDay.setText(building.getDeadline());
            aparts.setText(String.valueOf(building.getApart_count()));
            collapsingToolbar.setTitle(building.getName());
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void showMap(LatLng position) {
        Slide enterFade = new Slide();
        enterFade.setStartDelay(400);
        enterFade.setDuration(200);

        MapFragment fragment = CustomMapFragment.newInstance(position);
        fragment.setEnterTransition(enterFade);

        android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.map, fragment);
        transaction.commit();
    }

    @Override
    public void showFavoritedButton() {
        collapsedMenu.getItem(0).setIcon(R.drawable.ic_favorite_white_24dp);
    }

    @Override
    public void showUnFavoritedButton() {
        collapsedMenu.getItem(0).setIcon(R.drawable.ic_favorite_border_white_24dp);
    }

    private void setUpViews() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        growAnimation = AnimationUtils.loadAnimation(this, R.anim.fab_grow);
        shrinkAnimation = AnimationUtils.loadAnimation(this, R.anim.fab_shrink);

        appbar.addOnOffsetChangedListener(new FloatingButtonListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                switch (state) {
                    case COLLAPSED:
                        fab.hide();
                        fab.startAnimation(shrinkAnimation);
                        appBarExpanded = false;
                        invalidateOptionsMenu();
                        break;
                    case EXPANDED:
                        fab.show();
                        appBarExpanded = true;
                        fab.startAnimation(growAnimation);
                        invalidateOptionsMenu();
                        break;
                    case IDLE:
                        appBarExpanded = false;
                        fab.show();
                        break;
                }
            }
        });
    }

}
