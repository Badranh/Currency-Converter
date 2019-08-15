package com.badran.currencyconverter.base;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BaseNetworkViewModel extends ViewModel {

    private final MutableLiveData<Boolean> isDataFetched = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isFetchingData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> showError = new MutableLiveData<>();



    public void setSuccessfullyFetchedData(Boolean val){
        isDataFetched.setValue(val);
    }

    public void setIsFetchingData(Boolean val){
        isFetchingData.setValue(val);
    }

    public void setShowError(Boolean val){
        showError.setValue(val);
    }


    public LiveData<Boolean> getSuccessfullyFetchedDataView() {
        return isDataFetched;
    }

    public LiveData<Boolean> getIsFetchingDataView() {
        return isFetchingData;
    }

    public LiveData<Boolean> getShowErrorView() {
        return showError;
    }



}
