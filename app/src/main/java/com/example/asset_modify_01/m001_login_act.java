package com.example.asset_modify_01;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.asset_modify_01.databinding.M001LoginActBinding;

public class m001_login_act extends AppCompatActivity {
    private static final String KEY_ACC ="KEY_ACC" ;
    private M001LoginActBinding binding  ;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = M001LoginActBinding.inflate(getLayoutInflater()) ;
        setContentView(binding.getRoot());
        initViews() ;


    }

    private void initViews() {
            binding.cbAcc.setOnClickListener(view -> doSaveAccount());
            SharedPreferences shref = getSharedPreferences("sherf_saving",MODE_PRIVATE) ;
         String phone =  shref.getString(KEY_ACC,null) ;
                if(phone!= null)
                {
                    binding.cbAcc.setChecked(true);
                    binding.edtPhone.setText(phone);

                }

    }

    private void doSaveAccount() {
        if(binding.edtPhone.getText().toString().isEmpty())
        {
            Toast.makeText(this, "Please, Enter your data", Toast.LENGTH_SHORT).show();
                return ;
        }

        SharedPreferences sherf = getSharedPreferences("sherf_saving",MODE_PRIVATE) ;
                    if(binding.cbAcc.isChecked())
                    {

                        sherf.edit().putString(KEY_ACC,binding.edtPhone.getText().toString()).apply();

                    }
                    else {
                        sherf.edit().remove(KEY_ACC)  ;
                    }
    }
}
