package com.badran.currencyconverter.di.component;


import android.app.Application;

import com.badran.currencyconverter.base.BaseApplication;
import com.badran.currencyconverter.di.modules.ActivityBindingModule;
import com.badran.currencyconverter.di.modules.ApplicationModule;


import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        ActivityBindingModule.class,
        AndroidSupportInjectionModule.class})
public interface AppComponent extends AndroidInjector<BaseApplication> {


    @Component.Builder
    interface Builder {

        @BindsInstance
        AppComponent.Builder application(Application application);

        AppComponent build();
    }
}



