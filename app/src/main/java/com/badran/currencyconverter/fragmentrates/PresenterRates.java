package com.badran.currencyconverter.fragmentrates;

import android.util.Log;

import com.badran.currencyconverter.base.BaseViewHolder;
import com.badran.currencyconverter.data.API.APIDataSource;
import com.badran.currencyconverter.data.API.APIServiceImpl;
import com.badran.currencyconverter.data.model.Rates;
import com.badran.currencyconverter.data.model.Requests.RatesRequest;
import com.badran.currencyconverter.databinding.ItemCurrencyBinding;
import com.badran.currencyconverter.databinding.ItemCurrencyFirstBinding;
import com.badran.currencyconverter.di.annotations.ActivityScoped;
import java.text.DecimalFormat;
import javax.inject.Inject;
import io.reactivex.disposables.CompositeDisposable;
import static com.badran.currencyconverter.AppConstants.BASE_AT_START_UP;
import static com.badran.currencyconverter.AppConstants.INITIAL_STARTING_AMOUNT;
import static com.badran.currencyconverter.AppConstants.NUMBERS_FORMATTING;

@ActivityScoped
public class PresenterRates implements ContractRates.Presenter, BaseViewHolder.Binder<ContractRates.ViewHolderRates> {
    private APIServiceImpl apiDataSource;
    private ViewModelRates viewModelRates;
    private CompositeDisposable disposable = new CompositeDisposable();
    private final Rates rates = new Rates();
    private final DecimalFormat df2 = new DecimalFormat(NUMBERS_FORMATTING);
    private double amount = INITIAL_STARTING_AMOUNT;
    private String CHOSEN_BASE = BASE_AT_START_UP;

    @Inject
    public PresenterRates(APIServiceImpl apiDataSource, ViewModelRates viewModelRates) {
        this.apiDataSource = apiDataSource;
        this.viewModelRates = viewModelRates;
    }

    @Override
    public void fetchData() {
       // viewModelRates.setIsFetchingData(true);
        apiDataSource.getRate(CHOSEN_BASE,new APIDataSource.onFinishedListener<RatesRequest>() {
            @Override
            public void onSuccess(RatesRequest data) {
                viewModelRates.setSuccessfullyFetchedData(true);
                viewModelRates.setIsFetchingData(false);
                rates.setRates(data.getRates());
                rates.setBase(data.getBase());
            }
            @Override
            public void onFailure(Throwable t) {
                //log the error...or take an action according to it
                Log.d("errroooorrr","asd");
                viewModelRates.setShowError(true);
                viewModelRates.setIsFetchingData(false);
            }
        });
    }

    @Override
    public void onRateClick(int pos) {
        CHOSEN_BASE = (String) rates.getRates().keySet().toArray()[pos];
        rates.setBase(CHOSEN_BASE);
        viewModelRates.setNotifyItemMoved(pos);
        //remove any running threads of previous base currency
        reset();

    }

    @Override
    public void bindViewHolders(ContractRates.ViewHolderRates viewHolderRates,int pos) {
        final String currencyName = (String) rates.getRates().keySet().toArray()[pos];
        if(pos==0){
            ((ItemCurrencyFirstBinding)viewHolderRates.getBinding()).tvCurrencyName.setText(CHOSEN_BASE);
            ((ItemCurrencyFirstBinding)viewHolderRates.getBinding()).edtMain.setText(String.valueOf(amount));
            disposable.add(viewHolderRates.observeValue().subscribe(aDouble -> {
                if(amount!=aDouble) {
                    amount = aDouble;
                    reset();
                }
            }));
        }else {
            ((ItemCurrencyBinding)viewHolderRates.getBinding()).tvCurrencyName.setText(currencyName);
            Double total= (Double.valueOf(rates.getRates().get(currencyName)) * amount);
            ((ItemCurrencyBinding)viewHolderRates.getBinding()).edtValue.setCurrentText(String.valueOf(df2.format(total)));
        }
        viewHolderRates.setPresenter(this);
    }

    @Override
    public int getViewHoldersCount() {
        return rates.getRates() ==null? 0 : rates.getRates().values().size();
    }
    public void reset(){
        apiDataSource.clear();
        fetchData();
    }

}
