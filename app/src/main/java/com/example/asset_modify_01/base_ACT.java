package com.example.asset_modify_01;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

public abstract class base_ACT <T extends ViewBinding, M extends ViewModel> extends AppCompatActivity implements View.OnClickListener {
    T binding ; 
    M viewModel ; 
    @Override
    public void onClick(View v) {
        //do nothing
        
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = initViewBinding() ;  
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(CLassVM()) ; 
        initViews() ;
    }

    protected abstract void initViews();

    protected abstract T initViewBinding();

    protected abstract Class<M> CLassVM();
}
