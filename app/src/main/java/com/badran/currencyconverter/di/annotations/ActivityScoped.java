package com.badran.currencyconverter.di.annotations;


import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

//A local forever alone marker
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Scope
public @interface ActivityScoped {
}
