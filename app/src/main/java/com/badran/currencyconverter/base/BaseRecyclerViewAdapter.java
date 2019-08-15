package com.badran.currencyconverter.base;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static androidx.recyclerview.widget.RecyclerView.ViewHolder;

public class BaseRecyclerViewAdapter<T extends ViewHolder> extends RecyclerView.Adapter<T> {

    private BaseViewHolder.Binder<T> binder;
    @LayoutRes
    private int layoutRes;

    private Class<T> parameterClass;


    @NonNull
    @Override
    public T onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return this.newInstance(LayoutInflater.from(parent.getContext()).inflate(layoutRes,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull T holder, int position) {
        binder.bindViewHolders(holder,position);
    }

    @Override
    public int getItemCount() {
        return binder.getViewHoldersCount();
    }

    private T newInstance(View view) {
        try {
            return parameterClass.getDeclaredConstructor(View.class).newInstance(view);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public BaseRecyclerViewAdapter<T> addBinder(BaseViewHolder.Binder<T> binder){
        this.binder = binder;
        return this;
    }
    public BaseRecyclerViewAdapter<T> setLayout(int LayoutRes) {
        layoutRes = LayoutRes;
        return this;
    }
    public BaseRecyclerViewAdapter<T> setViewHolder(Class<T> typeParameterClass) {
        this.parameterClass = typeParameterClass;
        return this;
    }

}
