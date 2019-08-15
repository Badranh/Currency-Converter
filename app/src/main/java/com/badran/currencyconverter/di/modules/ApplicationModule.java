package com.badran.currencyconverter.di.modules;


import android.content.SharedPreferences;

import com.badran.currencyconverter.base.BaseApplication;
import com.badran.currencyconverter.data.API.APIService;
import com.badran.currencyconverter.data.API.APIServiceImpl;
import com.badran.currencyconverter.utils.UIHelpers;

import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.badran.currencyconverter.AppConstants.BASE_URL;

@Module
public abstract class ApplicationModule {
    public static BaseApplication baseApplication;

    public ApplicationModule(BaseApplication baseApplication) {
        ApplicationModule.baseApplication = baseApplication;
    }

    @Singleton
    @Provides
    static Retrofit provideRetrofit() {
        return new Retrofit.Builder().baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }
    @Singleton
    @Provides
    static APIService provideAPIService(Retrofit retrofit){
        return retrofit.create(APIService.class);
    }

    @Singleton
    @Provides
    static APIServiceImpl providesServiceImpl(APIService service){
        return new APIServiceImpl(service);
    }

    @Singleton
    @Provides
    static UIHelpers provideUIHelpers(){
        return new UIHelpers();
    }
}
