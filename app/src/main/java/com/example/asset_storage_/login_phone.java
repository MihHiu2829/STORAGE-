package com.example.asset_storage_;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.asset_storage_.databinding.LoginLayoutBinding;

public class login_phone extends AppCompatActivity {
    private static final String KEY_ACC = "KEY_ACC";
    LoginLayoutBinding binding ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = LoginLayoutBinding.inflate(getLayoutInflater()) ;
        setContentView(binding.getRoot());
        initViews() ;
    }

    private void initViews() {
        binding.cbAcc.setOnClickListener(view -> doSaveAcc());
//        SharedPreferences shrp = getSharedPreferences("pref saving", MODE_PRIVATE) ;
        String phone = CommonUltis.getInstance().getPref(KEY_ACC);
        if(phone != null)
        {        binding.edtPhone.setText(phone);
                    binding.cbAcc.setChecked(true);
        }



    }

    private void doSaveAcc() {
        if(binding.edtPhone.getText().toString().isEmpty())
        {
            Toast.makeText(this, "Pls enter phone! ", Toast.LENGTH_SHORT).show();
            return ;
        }

        if(binding.cbAcc.isChecked())
        CommonUltis.getInstance().savePref(KEY_ACC,binding.edtPhone.getText().toString());
        else        CommonUltis.getInstance().clearPref(KEY_ACC); ;

    }
}
