package com.badran.currencyconverter.dashboard.fragmentrates;

import androidx.databinding.ViewDataBinding;

import io.reactivex.Observable;

public interface ContractRates {
    interface View{
    }
    interface Presenter{
        void fetchData();
        void onRateClick(int pos);
    }
    interface ViewHolderRates{
        ViewDataBinding getBinding();
        void setPresenter(ContractRates.Presenter presenter);
        Observable<Double> observeValue();
    }
}
