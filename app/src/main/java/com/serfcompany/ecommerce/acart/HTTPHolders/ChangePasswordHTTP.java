package com.serfcompany.ecommerce.acart.HTTPHolders;

import android.util.Log;

import com.serfcompany.ecommerce.acart.Constants;
import com.serfcompany.ecommerce.acart.Utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class ChangePasswordHTTP {
    String urlString = Constants.APP_URI + "?amazingcart=json-api&type=change-password";

    public String changePassword(String username, String password, String newPassword) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");

        String params = "&username=" + username +
                "&currentpassword=" + password +
                "&newpassword=" + newPassword;

        Log.i("LOG", "PRE CHECKING CART");

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
