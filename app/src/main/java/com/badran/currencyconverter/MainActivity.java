package com.badran.currencyconverter;

import com.badran.currencyconverter.base.BaseActivity;
import com.badran.currencyconverter.data.API.APIServiceImpl;
import com.badran.currencyconverter.fragmentrates.FragmentRates;
import javax.inject.Inject;

public class MainActivity extends BaseActivity {

    @Inject
    APIServiceImpl apiService;
    @Override
    protected int LayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreateExtension() {
        getSupportFragmentManager().beginTransaction().add(R.id.content,new FragmentRates()).commit();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //release
        apiService.dispose();
    }
}
