package com.wesniemarcelin.reqresproject.di;

import com.wesniemarcelin.reqresproject.service.ReqService;
import com.wesniemarcelin.reqresproject.viewmodel.ReqResViewModelFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {
    @Singleton
    @Provides
    Retrofit provideRetrofit(RxJava2CallAdapterFactory factory) {
        return new Retrofit.Builder()
                .baseUrl(ReqService.REQ_API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(factory)
                .build();
    }

    @Singleton
    @Provides
    ReqResViewModelFactory provideViewModelFactory(ReqService reqService) {
        return new ReqResViewModelFactory(reqService);
    }

    @Singleton
    @Provides
    RxJava2CallAdapterFactory provideCallAdapterFactory() {
        return RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io());
    }

    @Singleton
    @Provides
    ReqService provideReqResService(Retrofit retrofit) {
        return retrofit.create(ReqService.class);
    }
}
