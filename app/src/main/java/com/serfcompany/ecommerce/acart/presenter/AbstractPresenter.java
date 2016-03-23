package com.serfcompany.ecommerce.acart.presenter;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class AbstractPresenter {
    public boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager
                .getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }
}
