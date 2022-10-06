package com.example.asset_modify_01;

import android.content.Context;
import android.content.SharedPreferences;
//  không dùng được do lỗi :
//java.lang.NullPointerException: Attempt to invoke virtual method 'android.view.View android.app.Activity.findViewById(int)' on a null object reference
// hiện tại chưa bít fix, nên sharePreferences chưa được tối ưuu. :((((
public class CommonUtils {
    private static final String PREF_FILE = "PREF_SAVING";
    private static CommonUtils instance ;

    private CommonUtils(){
        //for singleton
    }

    public static CommonUtils getInstance() {
        if(instance == null)
             instance = new CommonUtils() ;
        return instance;
    }
    public void SavePref(String key, String values)
    {
        SharedPreferences sherf =  App.getInstance().getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE) ;
        sherf.edit().putString(key,values).apply(); ;
    }
    public String getPref(String key)
    {
        SharedPreferences sherf = App.getInstance().getSharedPreferences(PREF_FILE,Context.MODE_PRIVATE);
        return sherf.getString(key,null) ;
    }
    public void clearPref(String key)
    {
        SharedPreferences sherf = App.getInstance().getSharedPreferences(PREF_FILE,Context.MODE_PRIVATE);
        sherf.edit().remove(key).apply();
    }
}
