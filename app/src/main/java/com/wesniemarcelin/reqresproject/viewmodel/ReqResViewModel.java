package com.wesniemarcelin.reqresproject.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.wesniemarcelin.reqresproject.model.NewUser;
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
    public MutableLiveData <NewUser> newUserCreated = new MutableLiveData<>();

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

    public void createNewUser(String name) {
        Disposable newUserDisposable = reqService.createUser(name, "student")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<NewUser>() {

                    @Override
                    public void onNext(NewUser newUser) {
                        newUserCreated.setValue(newUser);
                        Log.i(TAG, "New User: " + newUser.getName());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("ERROR", e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        compositeDisposable.add(newUserDisposable);
    }
}
