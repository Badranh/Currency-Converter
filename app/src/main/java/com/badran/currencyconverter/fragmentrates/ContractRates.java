package com.badran.currencyconverter.fragmentrates;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.DiffUtil;
import com.badran.currencyconverter.databinding.ItemCurrencyBinding;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

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
