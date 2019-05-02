package com.wesniemarcelin.reqresproject.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.wesniemarcelin.reqresproject.model.User;
import com.wesniemarcelin.reqresproject.model.UserList;
import com.wesniemarcelin.reqresproject.service.ReqService;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;


public class ReqResViewModel extends ViewModel {
    private static final String TAG = ReqResViewModel.class.getName();
    private ReqService reqService;
    private CompositeDisposable compositeDisposable;
    public MutableLiveData<List<User>> users = new MutableLiveData<>();

    public ReqResViewModel(ReqService reqService) {
        this.reqService = reqService;
        compositeDisposable = new CompositeDisposable();
    }

    public void getUserList() {
        Disposable disposable = reqService.getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<UserList>() {

                    @Override
                    public void onNext(UserList response) {
                        users.setValue(response.getData());
                        Log.i(TAG, response.getData().get(0).getFirstName());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("ERROR", e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        compositeDisposable.add(disposable);
    }

    public void createNewUser(String name){

    }
}
