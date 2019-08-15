package com.badran.currencyconverter.di.modules;


import com.badran.currencyconverter.MainActivity;
import com.badran.currencyconverter.di.annotations.ActivityScoped;
import com.badran.currencyconverter.di.submodules.MainSubModules;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = {MainSubModules.class})
    abstract MainActivity bindMainActivity();
}
