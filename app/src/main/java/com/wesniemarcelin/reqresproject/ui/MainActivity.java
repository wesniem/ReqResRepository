package com.wesniemarcelin.reqresproject.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.wesniemarcelin.reqresproject.R;
import com.wesniemarcelin.reqresproject.ReqResApplication;
import com.wesniemarcelin.reqresproject.databinding.ActivityMainBinding;
import com.wesniemarcelin.reqresproject.viewmodel.ReqResViewModel;
import com.wesniemarcelin.reqresproject.viewmodel.ReqResViewModelFactory;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    ReqResViewModel viewModel;
    private UserListAdapter adapter;

    @Inject
    ReqResViewModelFactory viewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ReqResApplication.getComponent().inject(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setSupportActionBar(binding.toolbar);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ReqResViewModel.class);
        intializeViews();
        fetchUsers();
    }

    private void intializeViews() {
        binding.toolbarOrgSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void fetchUsers() {
        viewModel.getUserList();
        viewModel.users.observe(this, users -> {
            if (users != null) {
                adapter = new UserListAdapter(users);
                binding.userListRv.setAdapter(adapter);
                binding.userListRv.setLayoutManager(new LinearLayoutManager(this));
            }
        });
    }
}
