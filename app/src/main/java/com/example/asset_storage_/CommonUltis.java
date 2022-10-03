package com.example.asset_storage_;

import android.content.Context;
import android.content.SharedPreferences;

public class CommonUltis {
    private static final String PREF_FILE = "pref_saving";
    private static CommonUltis instance ;
    private CommonUltis()
    {

    }

    public static CommonUltis getInstance() {
        if(getInstance() == null)
             instance = new CommonUltis() ;
        return instance;
    }

    public void savePref(String key, String value)
    {
        SharedPreferences shrd = App.getInstance().getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE) ;
        shrd.edit().putString(key, value).apply(); ;

    }
    public String getPref(String key)
    {
        SharedPreferences shrd = App.getInstance().getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE) ;

        return shrd.getString(PREF_FILE,null) ;
    }
    public void clearPref(String key)
    {
        SharedPreferences shrd = App.getInstance().getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE) ;
        shrd.edit().remove(key).apply(); ;

    }
}
