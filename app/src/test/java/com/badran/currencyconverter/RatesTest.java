package com.badran.currencyconverter;

import androidx.annotation.NonNull;

import com.badran.currencyconverter.data.API.APIDataSource;
import com.badran.currencyconverter.data.API.APIService;
import com.badran.currencyconverter.data.API.APIServiceImpl;
import com.badran.currencyconverter.data.model.Rates;
import com.badran.currencyconverter.data.model.Requests.RatesRequest;
import com.badran.currencyconverter.fragmentrates.PresenterRates;
import com.badran.currencyconverter.fragmentrates.ViewModelRates;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.stubbing.Answer;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.plugins.RxJavaPlugins;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.badran.currencyconverter.AppConstants.BASE_URL;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.when;

public class RatesTest {


    private APIService service;


    private PresenterRates presenterRates;


    @BeforeClass
    public static void setUpRxSchedulers() {
        Scheduler immediate = new Scheduler() {
            @Override
            public Disposable scheduleDirect(@NonNull Runnable run, long delay, @NonNull TimeUnit unit) {
                // this prevents StackOverflowErrors when scheduling with a delay
                return super.scheduleDirect(run, 0, unit);
            }

            @Override
            public Worker createWorker() {
                return new ExecutorScheduler.ExecutorWorker(Runnable::run,false);
            }
        };

        RxJavaPlugins.setInitIoSchedulerHandler(scheduler -> immediate);
        RxJavaPlugins.setInitComputationSchedulerHandler(scheduler -> immediate);
        RxJavaPlugins.setInitNewThreadSchedulerHandler(scheduler -> immediate);
        RxJavaPlugins.setInitSingleSchedulerHandler(scheduler -> immediate);
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(scheduler -> immediate);
    }

    @Before
    public void setUp(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(APIService.class);
        APIServiceImpl apiService = new APIServiceImpl(service);
        ViewModelRates viewModelRates = new ViewModelRates();
        presenterRates =new PresenterRates(apiService,viewModelRates);
    }

    @Test(expected = Exception.class)
    public void negativeClick(){
        doThrow().when(presenterRates).onRateClick(-2);
        presenterRates.onRateClick(-2);
    }

    @Test
    public void successAPICall(){
        Single<RatesRequest> ratesRequestSingle = service.getRates("EUR");
        assertNull(ratesRequestSingle.blockingGet().getError());
    }

    @Test(expected = Exception.class)
    public void FailAPICall(){
        Single<RatesRequest> ratesRequestSingle = service.getRates("");
        assertNotNull(ratesRequestSingle.blockingGet().getError());
    }

}
