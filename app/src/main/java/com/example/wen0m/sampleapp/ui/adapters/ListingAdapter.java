package com.example.wen0m.sampleapp.ui.adapters;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wen0m.sampleapp.base.BaseViewHolder;
import com.example.wen0m.sampleapp.mvp.models.Fighter;
import com.example.wen0m.sampleapp.shared.Constans;
import com.example.wen0m.sampleapp.R;
import com.example.wen0m.sampleapp.ui.activities.HouseDetailsActivity;
import com.example.wen0m.sampleapp.ui.activities.HouseListingActivity;
import com.squareup.picasso.Picasso;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ListingAdapter extends RecyclerView.Adapter<BaseViewHolder>{

    private static final int EMPTY = 0;
    private static final int NORMAL = 1;

    private List<Fighter> hotels;
    private Callback mCallback;


    public ListingAdapter(List<Fighter> hotels) {
        super();
        this.hotels = hotels;
    }

    public void setCallback(Callback callback) {
        mCallback = callback;
    }

    @Override
    public int getItemViewType(int position) {
        if (hotels != null && hotels.size() > 0) {
            return NORMAL;
        } else {
            return EMPTY;
        }
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case NORMAL:
                return new HouseViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.content_hotels, parent, false));
            case EMPTY:
            default:
                return new EmptyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.empty_hotels, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(final BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return Constans.ELEMENTS_CNT;
    }

    public void addItems(List<Fighter> buildings) {
        this.hotels.clear();
        hotels.addAll(buildings);
    }

    public interface Callback {
        void onEmptyListRetryClick();
        void onItemClicked(long Id);
        void fighterClicked(PictureData da);
    }


    public class HouseViewHolder extends BaseViewHolder {
        @BindView(R.id.hotel_card) CardView card;
        @BindView(R.id.Specialty) TextView title;
        @BindView(R.id.address) TextView address;
//        @BindView(R.id.startPrice) TextView fromPrice;
        @BindView(R.id.houseImg) ImageView image;

        HouseViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        protected void refresh() {
            title.setText("");
            address.setText("");
            image.setImageDrawable(null);
        }

        public void onBind(int position) {
            super.onBind(position);
            final Fighter current = hotels.get(position);

            try {
                title.setText(current.getFirst_name());
                address.setText(current.getLast_name());
//                fromPrice.setText(String.valueOf(current.getMin_prices().get(0).getPrice()));
            } catch (NullPointerException ex) {
                Log.e("TAG666", ex.getLocalizedMessage());
            }

            if(current.getProfile_image() != null) {
                Picasso.with(itemView.getContext()).load(current.getProfile_image())
                        .error(R.drawable.common_full_open_on_phone)
                        .into(image);
//                Glide.with(itemView.getContext()).load(current.getImage()).into(image);
            }

            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(current.getId() != null && mCallback != null) {
                        mCallback.onItemClicked(current.getId());

                        int[] screenLocation = new int[2];
                        view.getLocationOnScreen(screenLocation);
                        PictureData data = new PictureData(current.getId(), "", null,
                                view.getWidth(), view.getHeight(), screenLocation[0], screenLocation[1]);

                        mCallback.fighterClicked(data);
                    }
                }
            });

        }
    }

    public class EmptyViewHolder extends BaseViewHolder {
        @BindView(R.id.failure_text) TextView failureMsg;
        @BindView(R.id.retry_btn) Button retryBtn;

        public EmptyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        protected void refresh() {

        }

        @OnClick(R.id.retry_btn)
        void RetryBtnClick() {
            if (mCallback != null)
                mCallback.onEmptyListRetryClick();
        }
    }

    public class PictureData {
        int width, height, left, top;
        String description;
        Bitmap thumbnail;
        Long resourceId;

        public PictureData(Long resourceId, String description, Bitmap thumbnail, int width, int height, int left, int top) {
            this.resourceId = resourceId;
            this.description = description;
            this.thumbnail = thumbnail;
            this.width = width;
            this.height = height;
            this.left = left;
            this.top = top;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }

        public int getLeft() {
            return left;
        }

        public int getTop() {
            return top;
        }

        public String getDescription() {
            return description;
        }

        public Bitmap getThumbnail() {
            return thumbnail;
        }

        public Long getResourceId() {
            return resourceId;
        }
    }



}
