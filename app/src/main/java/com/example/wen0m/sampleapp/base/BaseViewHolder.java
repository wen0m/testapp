package com.example.wen0m.sampleapp.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;


public abstract class BaseViewHolder extends RecyclerView.ViewHolder{

    private int mPosition;

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    protected abstract void refresh();

    public void onBind(int position) {
        mPosition = position;
        refresh();
    }
}
