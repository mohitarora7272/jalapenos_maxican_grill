package com.serfcompany.ecommerce.acart.HTTPHolders;

import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by serfcompany on 14.03.16.
 */
public class UpdateShippingHTTP {
    private final String urlString = "http://woocommerce.serfcompany.com/?amazingcart=json-api&type=user-shipping-update";

    public String updateShipping(String username, String password, String firstName,
                                 String lastName, String companyName, String address1,
                                 String address2, String cityName, String postcode,
                                 String stateName, String country) throws IOException {

        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");

        connection.setDoOutput(true);
        String params = "&username="+username+"&password="+password+
                "&shipping_first_name="+firstName+
                "&shipping_last_name="+lastName+
                "&shipping_company="+companyName+
                "&shipping_address_1="+address1+
                "&shipping_address_2="+address2+
                "&shipping_city="+cityName+
                "&shipping_postcode="+postcode+
                "&shipping_state="+stateName+
                "&shipping_country="+country;

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
        Log.i("LOG", "Shipping was updated : " +result.toString());
        return result.toString();
    }
}
