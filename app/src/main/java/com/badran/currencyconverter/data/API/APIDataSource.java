package com.badran.currencyconverter.data.API;

import com.badran.currencyconverter.data.model.Rates;
import com.badran.currencyconverter.data.model.Requests.RatesRequest;

public interface APIDataSource {
    interface onFinishedListener<T>{
        void onSuccess(T data);
        void onFailure(Throwable t);
    }
    void getRate(String baseCurrency,onFinishedListener<RatesRequest> onFinishedListener);
    void clear();
    void dispose();
}
