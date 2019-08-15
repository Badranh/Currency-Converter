package com.badran.currencyconverter.dashboard.fragmentrates.viewholders;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.badran.currencyconverter.R;
import com.badran.currencyconverter.base.BaseRecyclerViewAdapter;

public class RvRatesAdapter extends BaseRecyclerViewAdapter<RvViewHolderRates> {

    @NonNull
    @Override
    public RvViewHolderRates onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == 1) {
            return new RvViewHolderRates(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_currency_first,parent,false));
        } else
            return super.onCreateViewHolder(parent, viewType);
    }

    //we have 2 view types, first cell and the rest
    //we can do it with 1 type by converting edit text into textView, but in this approach it  will be highly customizable for future development
    @Override
    public int getItemViewType(int position) {
        if (position == 0) return 1;
        else return 2;
    }
}
