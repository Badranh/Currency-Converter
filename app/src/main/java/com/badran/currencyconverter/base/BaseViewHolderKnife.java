package com.badran.currencyconverter.base;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseViewHolderKnife  extends RecyclerView.ViewHolder {

    private  Unbinder bind;

    public BaseViewHolderKnife(@NonNull View itemView) {
        super(itemView);
       bind = ButterKnife.bind(this, itemView);
    }

}
