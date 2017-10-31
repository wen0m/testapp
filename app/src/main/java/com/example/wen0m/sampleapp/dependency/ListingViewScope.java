package com.example.wen0m.sampleapp.dependency;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.inject.Scope;


@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ListingViewScope {
}
