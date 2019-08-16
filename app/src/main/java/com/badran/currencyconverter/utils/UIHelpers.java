package com.badran.currencyconverter.utils;

import android.content.Context;

import androidx.annotation.LayoutRes;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.badran.currencyconverter.base.BaseRecyclerViewAdapter;
import com.badran.currencyconverter.base.BaseViewHolder;

public class UIHelpers {

    public void setupRecycler(RecyclerView recyclerView ,BaseRecyclerViewAdapter baseRecyclerViewAdapter, BaseViewHolder.Binder presenter, Class clazz, @LayoutRes int layout){
        baseRecyclerViewAdapter.addBinder(presenter)
                .setLayout(layout)
                .setViewHolder(clazz);
        recyclerView.setAdapter(baseRecyclerViewAdapter);
    }

    public LinearLayoutManager makeItVertical(Context context, RecyclerView recyclerView) {
        LinearLayoutManager llm = new LinearLayoutManager(context);
        llm.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(llm);
        return llm;
    }
}
