package com.badran.currencyconverter.dashboard.fragmentrates;

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

    void setNotifyItemMoved(Integer val) {
        notifyItemMoved.setValue(val);
    }


    LiveData<Integer> getItemMoved() {
        return notifyItemMoved;
    }
}
