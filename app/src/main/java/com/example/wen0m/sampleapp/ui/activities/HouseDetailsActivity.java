package com.example.wen0m.sampleapp.ui.activities;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
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
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;
import com.example.wen0m.sampleapp.R;
import com.example.wen0m.sampleapp.mvp.models.Fighter;
import com.example.wen0m.sampleapp.mvp.presenters.DetailPresenter;
import com.example.wen0m.sampleapp.mvp.views.DetailsMvpView;
import com.example.wen0m.sampleapp.base.BaseActivity;
import com.example.wen0m.sampleapp.ui.views.FloatingButtonListener;
import com.example.wen0m.sampleapp.ui.views.CustomMapFragment;
import com.example.wen0m.sampleapp.shared.CommonUtils;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HouseDetailsActivity extends BaseActivity implements DetailsMvpView {
    float sAnimatorScale = 1;
    private static final int ANIM_DURATION = 1000;
    @InjectPresenter(type = PresenterType.GLOBAL, tag = "DetailedPresenter")
    DetailPresenter presenter;

    @BindView(R.id.thumbnail) ImageView hotelImg;
    @BindView(R.id.fighter_name) TextView fighterName;
    @BindView(R.id.draws_count) TextView draws;
    @BindView(R.id.wins_count) TextView wins;
    @BindView(R.id.lose_cnt) TextView looses;
    @BindView(R.id.phone_fab) FloatingActionButton fab;
    @BindView(R.id.app_bar) AppBarLayout appbar;
    @BindView(R.id.collapse_toolbar) CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.coordinator) CoordinatorLayout coordinator;
    @BindView(R.id.nickname) TextView nickname;
    @BindView(R.id.age) TextView age;
    @BindView(R.id.height) TextView height;
    @BindView(R.id.weight) TextView weight;
    @BindView(R.id.weight_class) TextView weightClass;

    private Menu collapsedMenu;
    private Animation shrinkAnimation, growAnimation;
    private boolean appBarExpanded = true;
    int mLeftDelta;
    int mTopDelta;
    float mWidthScale;
    float mHeightScale;
    private int mOriginalOrientation;
    ColorDrawable mBackground;
    private static final TimeInterpolator sDecelerator = new DecelerateInterpolator();
    private static final TimeInterpolator sAccelerator = new AccelerateInterpolator();


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
        setContentView(R.layout.activity_hotel_detail);
        setUnBinder(ButterKnife.bind(this));
        setUpViews();

        presenter.displayHouseDetails(getIntent().getLongExtra("resourceId", 0));
        presenter.changeFavoriteButton(getIntent().getLongExtra("resourceId", 0));

        mBackground = new ColorDrawable(0);

        String description = getIntent().getStringExtra("description");
        final int thumbnailTop = getIntent().getIntExtra("top", 0);
        final int thumbnailLeft = getIntent().getIntExtra("left", 0);
        final int thumbnailWidth = getIntent().getIntExtra("width", 0);
        final int thumbnailHeight = getIntent().getIntExtra("height", 0);
        mOriginalOrientation = getIntent().getIntExtra("orientation", 0);

        if (savedInstanceState == null) {
            ViewTreeObserver observer = hotelImg.getViewTreeObserver();
            observer.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {

                @Override
                public boolean onPreDraw() {
                    hotelImg.getViewTreeObserver().removeOnPreDrawListener(this);

                    // Figure out where the thumbnail and full size versions are, relative
                    // to the screen and each other
                    int[] screenLocation = new int[2];
                    hotelImg.getLocationOnScreen(screenLocation);
                    mLeftDelta = thumbnailLeft - screenLocation[0];
                    mTopDelta = thumbnailTop - screenLocation[1];

                    // Scale factors to make the large version the same size as the thumbnail
                    mWidthScale = (float) thumbnailWidth / hotelImg.getWidth();
                    mHeightScale = (float) thumbnailHeight / hotelImg.getHeight();

                    runEnterAnimation();

                    return true;
                }
            });
        }
        overridePendingTransition(0, 0);
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
        this.collapsedMenu = menu;
        return true;
    }


    @OnClick(R.id.phone_fab)
    public void onClick() {
        phoneCallIntent(getApplicationContext());
    }

    @Override
    public void showDetails(Fighter building) {
        if(building != null) {
            collapsingToolbar.setTitle(building.getFirst_name() + " " + building.getLast_name());

            Picasso.with(this).load(building.getProfile_image())
                    .transform(CommonUtils.getTransform())
                    .error(R.drawable.common_full_open_on_phone)
                    .into(hotelImg);

            if (building.getNickname() != null) {
                fighterName.setText(building.getNickname());
            }

            nickname.setText(building.getStrengths());

            //java.lang.NullPointerException: string arg == null

//            country.setText(building.getCountry_residing().concat(building.getState_residing()).concat(building.getCity_residing()));
            wins.setText(String.valueOf(building.getWins()));
            draws.setText(String.valueOf(building.getDraws()));
            looses.setText(String.valueOf(building.getLosses()));

            Date date = null;
            try {
                date = new SimpleDateFormat("yyyy-MM-dd").parse(building.getDob());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            age.setText(String.valueOf(date.getYear()));
            height.setText(String.valueOf(building.getHeight()));
            weight.setText(String.valueOf(building.getWeight()));
            weightClass.setText(building.getWeight_class());
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

    public void runEnterAnimation() {
        final long duration = (long) (ANIM_DURATION * sAnimatorScale);

        // Set starting values for properties we're going to animate. These
        // values scale and position the full size version down to the thumbnail
        // size/location, from which we'll animate it back up
        hotelImg.setPivotX(0);
        hotelImg.setPivotY(0);
        hotelImg.setScaleX(mWidthScale);
        hotelImg.setScaleY(mHeightScale);
        hotelImg.setTranslationX(mLeftDelta);
        hotelImg.setTranslationY(mTopDelta);

        // We'll fade the text in later
//        mTextView.setAlpha(0);

        // Animate scale and translation to go from thumbnail to full size
        hotelImg.animate().setDuration(duration).
                scaleX(1).scaleY(1).
                translationX(0).translationY(0).
                setInterpolator(sDecelerator).
                withEndAction(new Runnable() {
                    public void run() {
                        // Animate the description in after the image animation
                        // is done. Slide and fade the text in from underneath
                        // the picture.
//                        mTextView.setTranslationY(-mTextView.getHeight());
//                        mTextView.animate().setDuration(duration/2).
//                                translationY(0).alpha(1).
//                                setInterpolator(sDecelerator);
                    }
                });

        // Fade in the black background
        ObjectAnimator bgAnim = ObjectAnimator.ofInt(mBackground, "alpha", 0, 255);
        bgAnim.setDuration(duration);
        bgAnim.start();

        // Animate a color filter to take the image from grayscale to full color.
        // This happens in parallel with the image scaling and moving into place.
//        ObjectAnimator colorizer = ObjectAnimator.ofFloat(HouseDetailsActivity.this,
//                "saturation", 0, 1);
//        colorizer.setDuration(duration);
//        colorizer.start();

        // Animate a drop-shadow of the image
//        ObjectAnimator shadowAnim = ObjectAnimator.ofFloat(mShadowLayout, "shadowDepth", 0, 1);
//        shadowAnim.setDuration(duration);
//        shadowAnim.start();
    }

}
