package com.wesniemarcelin.reqresproject.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.wesniemarcelin.reqresproject.service.ReqService;

import io.reactivex.disposables.CompositeDisposable;

public class ReqResViewModel extends ViewModel {
    private static final String TAG = ReqResViewModel.class.getName();
    private ReqService reqService;
    private CompositeDisposable compositeDisposable;

    public ReqResViewModel(ReqService reqService) {
        this.reqService = reqService;
        compositeDisposable = new CompositeDisposable();
    }
}
