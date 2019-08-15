package com.badran.currencyconverter.fragmentrates;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.badran.currencyconverter.base.BaseNetworkViewModel;
import com.badran.currencyconverter.di.annotations.ActivityScoped;

import javax.inject.Inject;

@ActivityScoped
public class ViewModelRates extends BaseNetworkViewModel {
    private final MutableLiveData<Integer> notifyItemMoved = new MutableLiveData<>();

    @Inject
    public ViewModelRates() {
    }

    public void setNotifyItemMoved(Integer val){
        notifyItemMoved.setValue(val);
    }


    public LiveData<Integer> getItemMoved() {
        return notifyItemMoved;
    }
}
