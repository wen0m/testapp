package com.example.wen0m.sampleapp.listing;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.wen0m.sampleapp.models.Building;
import com.example.wen0m.sampleapp.shared.base.BaseViewHolder;
import com.example.wen0m.sampleapp.shared.Constans;
import com.example.wen0m.sampleapp.R;
import com.squareup.picasso.Picasso;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ListingAdapter extends RecyclerView.Adapter<BaseViewHolder>{

    private static final int EMPTY = 0;
    private static final int NORMAL = 1;

    private List<Building> hotels;
    private Callback mCallback;


    public ListingAdapter(List<Building> hotels) {
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

    public void addItems(List<Building> buildings) {
        this.hotels.clear();
        hotels.addAll(buildings);
    }

    public interface Callback {
        void onEmptyListRetryClick();
        void onItemClicked(long Id);
    }


    public class HouseViewHolder extends BaseViewHolder {
        @BindView(R.id.hotel_card) CardView card;
        @BindView(R.id.name) TextView title;
        @BindView(R.id.address) TextView address;
        @BindView(R.id.startPrice) TextView fromPrice;
        @BindView(R.id.houseImg) ImageView image;

        HouseViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        protected void refresh() {
            title.setText(""); address.setText(""); fromPrice.setText("");
            image.setImageDrawable(null);
        }

        public void onBind(int position) {
            super.onBind(position);
            final Building current = hotels.get(position);

            try {
                title.setText(current.getName());
                address.setText(current.getAddress());
                fromPrice.setText(String.valueOf(current.getMin_prices().get(0).getPrice()));
            } catch (NullPointerException ex) {
                Log.e("TAG666", ex.getLocalizedMessage());
            }

            if(current.getImage() != null) {
                Picasso.with(itemView.getContext()).load(current.getImage())
                        .error(R.drawable.common_full_open_on_phone)
                        .into(image);
//                Glide.with(itemView.getContext()).load(current.getImage()).into(image);
            }

            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(current.getId() != null && mCallback != null) {
                        mCallback.onItemClicked(current.getId());
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

}
