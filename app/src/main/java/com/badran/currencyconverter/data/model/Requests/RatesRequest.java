package com.badran.currencyconverter.data.model.Requests;

import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RatesRequest {
    private String error;
    private String base;
    private Map<String,String> rates;
}
