package com.badran.currencyconverter.base;


import com.badran.currencyconverter.di.component.AppComponent;
import com.badran.currencyconverter.di.component.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class BaseApplication extends DaggerApplication {


    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Inject
    public BaseApplication() {
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        AppComponent component = DaggerAppComponent.builder().application(this).build();
        component.inject(this);
        return component;
    }

}
