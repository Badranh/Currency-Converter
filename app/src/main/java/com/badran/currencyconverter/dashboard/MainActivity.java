package com.badran.currencyconverter.dashboard;

import com.badran.currencyconverter.R;
import com.badran.currencyconverter.base.BaseActivity;
import com.badran.currencyconverter.dashboard.fragmentrates.FragmentRates;
import com.badran.currencyconverter.data.API.APIServiceImpl;
import com.badran.currencyconverter.di.annotations.ActivityScoped;

import javax.inject.Inject;

@ActivityScoped
public class MainActivity extends BaseActivity {

    @Inject
    APIServiceImpl apiService;
    @Inject
    FragmentRates fragmentRates;

    @Override
    protected int LayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreateExtension() {
        getSupportFragmentManager().beginTransaction().add(R.id.content, fragmentRates).commit();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //release
        //apiService.dispose();
    }
}
