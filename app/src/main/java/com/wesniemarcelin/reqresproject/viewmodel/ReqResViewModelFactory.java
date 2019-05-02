package com.wesniemarcelin.reqresproject.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.wesniemarcelin.reqresproject.service.ReqService;

import javax.inject.Singleton;

@Singleton
public class ReqResViewModelFactory implements ViewModelProvider.Factory {
    private ReqService reqService;

    public ReqResViewModelFactory(ReqService reqService) {
        this.reqService = reqService;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ReqResViewModel.class)) {
            return (T) new ReqResViewModel(reqService);
        }
        throw new IllegalArgumentException("Unknown ViewModel Class");
    }
}
