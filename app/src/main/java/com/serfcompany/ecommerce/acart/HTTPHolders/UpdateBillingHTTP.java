package com.serfcompany.ecommerce.acart.HTTPHolders;

import android.util.Log;

import com.serfcompany.ecommerce.acart.Constants;
import com.serfcompany.ecommerce.acart.R;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class UpdateBillingHTTP {

    private final String urlString = Constants.APP_URI +
            "?amazingcart=json-api&type=user-billing-update";

    public String updateBilling(String username, String password, String firstName,
                                String lastName, String companyName, String address1,
                                String address2, String cityName, String postcode,
                                String stateName, String country, String phone, String email) throws IOException {

        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");

        connection.setDoOutput(true);
        String params = "&username="+username+"&password="+password+
                "&billing_first_name="+firstName+
                "&billing_last_name="+lastName+
                "&billing_company="+companyName+
                "&billing_address_1="+address1+
                "&billing_address_2="+address2+
                "&billing_city="+cityName+
                "&billing_postcode="+postcode+
                "&billing_state="+stateName+
                "&billing_country="+country+
                "&billing_phone="+phone+
                "&billing_email="+email;

        DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
        wr.writeBytes(params);
        wr.flush();
        wr.close();


        BufferedReader rd = new BufferedReader(
                new InputStreamReader(connection.getInputStream()));
        StringBuilder result = new StringBuilder();
        String line = "";
        while ((line = rd.readLine()) != null){
            result.append(line);
            Log.i("LOG", "line = " + line);
        }
        rd.close();
        Log.i("LOG", "Billing was updated : " +result.toString());
        return result.toString();
    }
}
