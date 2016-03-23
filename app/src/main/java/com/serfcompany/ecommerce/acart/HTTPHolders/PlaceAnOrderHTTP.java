package com.serfcompany.ecommerce.acart.HTTPHolders;

import android.util.Log;

import com.serfcompany.ecommerce.acart.Constants;
import com.serfcompany.ecommerce.acart.R;
import com.serfcompany.ecommerce.acart.Utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Map;

public class PlaceAnOrderHTTP {

    private String urlString = Constants.APP_URI +
            "?amazingcart=json-api&type=place-an-order-api";

    public String placeOrder(String username, String password,
                           Map<String, String> idQuantityMap, Map<String, String> couponCode, String paymentMethodID, String orderNotes) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");

        String params = "&username=" + username +
                "&password=" + password +
                "&productIDJson=" + Utils.getProdIDJsonFromMap(idQuantityMap) +
                "&couponCodeJson=" + Utils.getCouponJsonFromMap(couponCode)+
                "&paymentMethodID=" + paymentMethodID +
                "&orderNotes=" + orderNotes;

        Log.i("LOG", "PLACING AND ORDER");

        connection.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
        wr.writeBytes(params);
        wr.flush();
        wr.close();

        BufferedReader rd = new BufferedReader(
                new InputStreamReader(connection.getInputStream()));
        String line = "";
        StringBuilder result = new StringBuilder();
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
        Log.i("LOG", result.toString());
        return result.toString();
    }
}
