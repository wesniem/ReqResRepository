package com.wesniemarcelin.reqresproject.di;

import com.wesniemarcelin.reqresproject.ReqResApplication;
import com.wesniemarcelin.reqresproject.ui.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, ContextModule.class})
public interface AppComponent {
    void inject(ReqResApplication application);
    void inject(MainActivity mainActivity);
}
