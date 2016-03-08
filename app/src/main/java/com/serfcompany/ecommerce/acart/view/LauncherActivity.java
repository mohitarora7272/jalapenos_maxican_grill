package com.serfcompany.ecommerce.acart.view;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.serfcompany.ecommerce.acart.Constants;

/**
 * Created by serfcompany on 08.03.16.
 */
public class LauncherActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Intent intent = new Intent();
        intent.setClass(this, MainActivity.class);
        startActivity(intent);
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

        getBaseContext().getResources().flushLayoutCache();
        super.onDestroy();
    }
}
