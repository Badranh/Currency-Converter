package com.badran.currencyconverter.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import dagger.android.support.DaggerFragment;

public abstract class BaseFragment<T extends ViewDataBinding> extends DaggerFragment {

    protected T binding;
    private AppCompatActivity activity;

    @LayoutRes
    protected abstract int LayoutRes();

    protected abstract BaseNetworkViewModel viewModel();
    protected abstract BaseRecyclerViewAdapter adapter();
    protected abstract void refresh();
    protected abstract void onError();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        assert container != null;
        binding = DataBindingUtil.inflate(inflater, LayoutRes(), container, false);
        return binding.getRoot();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (AppCompatActivity) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activity = null;
    }
    public AppCompatActivity getBaseActivity() {
        return activity;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    public void showMessage(String text){
        Toast.makeText(getBaseActivity(),text,Toast.LENGTH_SHORT).show();
    }

    protected void initObservers(){
        if(viewModel()==null || adapter()==null) return;
        viewModel().getIsFetchingDataView().observe(getViewLifecycleOwner(),aBoolean -> {
            if(aBoolean!=null)
                if(aBoolean){
                    showMessage("Loading");
                }

        });
        viewModel().getShowErrorView().observe(getViewLifecycleOwner(),aBoolean -> {
            if(aBoolean!=null && aBoolean){
                showMessage("Error");
                onError();
            }
        });
        viewModel().getSuccessfullyFetchedDataView().observe(getViewLifecycleOwner(),aBoolean -> {
            if(aBoolean!=null && aBoolean){
                refresh();
            }
        });
    }

}
