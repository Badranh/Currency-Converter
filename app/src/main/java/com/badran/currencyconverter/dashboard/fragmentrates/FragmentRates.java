package com.badran.currencyconverter.dashboard.fragmentrates;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.badran.currencyconverter.R;
import com.badran.currencyconverter.base.BaseFragment;
import com.badran.currencyconverter.base.BaseNetworkViewModel;
import com.badran.currencyconverter.base.BaseRecyclerViewAdapter;
import com.badran.currencyconverter.base.BaseViewHolder;
import com.badran.currencyconverter.dashboard.fragmentrates.viewholders.RvRatesAdapter;
import com.badran.currencyconverter.dashboard.fragmentrates.viewholders.RvViewHolderRates;
import com.badran.currencyconverter.databinding.FragmentRatesBinding;
import com.badran.currencyconverter.di.annotations.ActivityScoped;
import com.badran.currencyconverter.utils.UIHelpers;

import javax.inject.Inject;

@ActivityScoped
public class FragmentRates extends BaseFragment<FragmentRatesBinding> implements ContractRates.View {
    @Inject
    ViewModelRates viewModelRates;
    @Inject
    ContractRates.Presenter presenter;
    @Inject
    UIHelpers uiHelpers;

    private RecyclerView recyclerView;

    private RvRatesAdapter baseRecyclerViewAdapter;

    private  LinearLayoutManager llm;

    @Inject
    public FragmentRates() {
    }

    @Override
    protected int LayoutRes() {
        return R.layout.fragment_rates;
    }

    @Override
    protected BaseNetworkViewModel viewModel() {
        return viewModelRates;
    }

    @Override
    protected BaseRecyclerViewAdapter adapter() {
        return baseRecyclerViewAdapter;
    }


    @Override
    protected void refresh() {
        if(recyclerView.getChildCount() == 0)
            adapter().notifyDataSetChanged();
        else
            //let me write in peace
            adapter().notifyItemRangeChanged(1,llm.getItemCount());
    }

    @Override
    protected void onError() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getBaseActivity());
        builder.setMessage("We literally poll data every second so how are you planning to use this app with no Connection!");
        //we don't need to dispose since it's already completed here buttttt with failure
        builder.setPositiveButton("Retry", (dialog, which) -> presenter.fetchData());
        builder.setNegativeButton("Get Me Out Of Here",(dialog, which)  -> System.exit(0));
        builder.show();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dynamicViewLoad();
        presenter.fetchData();
        initObs();
    }

    private void initObs() {
        initObservers();
        viewModelRates.getItemMoved().observe(getViewLifecycleOwner(),aBoolean -> {
            if(aBoolean!=null){
                baseRecyclerViewAdapter.notifyItemMoved(aBoolean,0);
                llm.scrollToPosition(0);
            }
        });
    }

    private void dynamicViewLoad() {
        loadRatesRv();
        setPageTitle();
    }

    private void setPageTitle() { binding.setPageTitle("RATES"); }

    private void loadRatesRv() {
        baseRecyclerViewAdapter = new RvRatesAdapter();
        recyclerView = new RecyclerView(getBaseActivity());
        recyclerView.setFocusable(false);
        uiHelpers.setupRecycler(recyclerView,baseRecyclerViewAdapter,(BaseViewHolder.Binder<RvViewHolderRates>) presenter,RvViewHolderRates.class,R.layout.item_currency);
        //oh no, my eyes please section
        recyclerView.setItemAnimator(null);
        //
        llm = new LinearLayoutManager(getContext());
        llm.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        binding.root.addView(recyclerView);
    }


}
