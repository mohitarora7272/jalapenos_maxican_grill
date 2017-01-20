package com.serfcompany.ecommerce.acart.view;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.serfcompany.ecommerce.acart.Constants;
import com.serfcompany.ecommerce.acart.R;

public class LauncherActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        setTheme(R.style.AppDefault);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent();
                intent.setClass(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        }, 4000);
    }

    @Override
    protected void onDestroy() {

        SharedPreferences sharedPreferences = getSharedPreferences(Constants.LOGIN_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (!sharedPreferences.getBoolean(Constants.REMEMBER, false)){
            editor.clear();
            editor.apply();
            Log.i("LOG", "Login preferences deleted");
            SharedPreferences cartPrefs = getSharedPreferences(Constants.CART_PREFS, MODE_PRIVATE);
            SharedPreferences.Editor cartEditor = cartPrefs.edit();
            cartEditor.clear();
            cartEditor.apply();
            Log.i("LOG", "Cart preferences deleted");
        }
        Log.i("LOG", "Coupons preferences deleted");
        SharedPreferences couponPrefs = getSharedPreferences(Constants.COUPON_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor couponPrefsEditor = couponPrefs.edit();
        couponPrefsEditor.clear();
        couponPrefsEditor.apply();


        getBaseContext().getResources().flushLayoutCache();
        super.onDestroy();
    }
}
