package com.badran.currencyconverter.di.submodules;

import com.badran.currencyconverter.dashboard.fragmentrates.ContractRates;
import com.badran.currencyconverter.dashboard.fragmentrates.FragmentRates;
import com.badran.currencyconverter.dashboard.fragmentrates.PresenterRates;
import com.badran.currencyconverter.di.annotations.ActivityScoped;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainSubModules {

    @ContributesAndroidInjector
    abstract FragmentRates
    bindFragmentRates();

    @ActivityScoped
    @Binds
    abstract ContractRates.Presenter
    providesPresenter(PresenterRates presenterRates);

}
