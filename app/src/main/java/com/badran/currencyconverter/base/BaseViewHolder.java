package com.badran.currencyconverter.base;



public interface BaseViewHolder {

    interface Binder<T>{
        void bindViewHolders(T viewHolder, int pos);
        int getViewHoldersCount();
    }

}
