package com.badran.currencyconverter.data.API;

import android.util.Log;

import com.badran.currencyconverter.data.model.Requests.RatesRequest;

import org.reactivestreams.Publisher;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

import static com.badran.currencyconverter.AppConstants.API_REFRESH_RATE;

public class APIServiceImpl implements APIDataSource {

    private final APIService service;
    private final CompositeDisposable disposables = new CompositeDisposable();
    private final int MAX_ATTEMPTS = 5;

    public APIServiceImpl(APIService service) {
        this.service = service;
    }

    @Override
    public void getRate(String baseCurrency,onFinishedListener<RatesRequest> onFinishedListener) {
        Single<RatesRequest> request = service.getRates(baseCurrency);
        disposables.add(request.repeatWhen(objectFlowable -> objectFlowable.delay(API_REFRESH_RATE,TimeUnit.MILLISECONDS))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                //no one wants duplicate requests
                .distinctUntilChanged()
                //Exponential backoff retry
                .retryWhen(throwableFlowable -> throwableFlowable
                        .zipWith(Flowable.range(1, MAX_ATTEMPTS+1), (throwable, integer) -> {
                            Log.d("error","retry" + integer);
                            if(integer>=MAX_ATTEMPTS){ throw new Exception();}  return integer;})
                        .flatMap((Function<Integer, Publisher<?>>) integer -> Flowable.timer(
                    ((long)Math.pow(2, integer)),
                    TimeUnit.SECONDS
                 )))
                .share()
                .subscribeWith(new DisposableSubscriber<RatesRequest>() {
                    @Override
                    public void onNext(RatesRequest rates) {
                        if(rates.getError() != null)
                            onFinishedListener.onFailure(new Throwable("Unknown Base Currency"));
                        else
                            onFinishedListener.onSuccess(rates);
                    }

                    @Override
                    public void onError(Throwable t)
                    {
                        Log.d("error","error onError");
                        onFinishedListener.onFailure(t);
                    }

                    @Override
                    public void onComplete() {
                        Log.d("error","error onComplete");

                    }
                }));
    }

    @Override
    public void clear() {
        disposables.clear();
    }

    @Override
    public void dispose() {
        disposables.dispose();
    }
}
