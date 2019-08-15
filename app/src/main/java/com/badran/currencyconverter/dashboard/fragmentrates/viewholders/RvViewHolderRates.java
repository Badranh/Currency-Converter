package com.badran.currencyconverter.dashboard.fragmentrates.viewholders;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.badran.currencyconverter.base.BaseViewHolderKnife;
import com.badran.currencyconverter.dashboard.fragmentrates.ContractRates;
import com.badran.currencyconverter.databinding.ItemCurrencyFirstBinding;
import com.jakewharton.rxbinding3.widget.RxTextView;

import io.reactivex.Observable;


public class RvViewHolderRates extends BaseViewHolderKnife implements ContractRates.ViewHolderRates {
    private ViewDataBinding itemCurrencyBinding;
    private ContractRates.Presenter presenter;


    public RvViewHolderRates(@NonNull View itemView) {
        super(itemView);
        itemCurrencyBinding = DataBindingUtil.bind(itemView);
        itemView.setOnClickListener(this::onClick);

    }

    @Override
    public ViewDataBinding getBinding() {
        return itemCurrencyBinding;
    }

    @Override
    public void setPresenter(ContractRates.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public Observable<Double> observeValue() {
        if(getItemViewType() == 1)
            return RxTextView.afterTextChangeEvents(((ItemCurrencyFirstBinding)itemCurrencyBinding).edtMain)
                    .distinctUntilChanged()
                    .map(s ->{
                        if(s.getEditable().toString().isEmpty())
                            return 0.0;
                        return Double.valueOf(s.getEditable().toString());
                    });
        else
            return null;
    }

    private void onClick(View v) {
        presenter.onRateClick(getAdapterPosition());
    }
}
