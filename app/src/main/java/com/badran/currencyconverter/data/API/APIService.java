package com.badran.currencyconverter.data.API;

import com.badran.currencyconverter.data.model.Rates;
import com.badran.currencyconverter.data.model.Requests.RatesRequest;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {

    @GET("/latest")
    Single<RatesRequest> getRates(@Query("base") String base);
}
