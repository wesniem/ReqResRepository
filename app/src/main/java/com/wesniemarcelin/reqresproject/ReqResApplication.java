package com.wesniemarcelin.reqresproject;

import android.app.Application;

import com.wesniemarcelin.reqresproject.di.AppComponent;
import com.wesniemarcelin.reqresproject.di.ContextModule;
import com.wesniemarcelin.reqresproject.di.DaggerAppComponent;

public class ReqResApplication extends Application {
    private static AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerAppComponent.builder().
                contextModule(new ContextModule(getApplicationContext()))
                .build();
    }

    public static AppComponent getComponent() {
        return component;
    }
}
