package com.serfcompany.ecommerce.acart.HTTPHolders;

import android.util.Log;

import com.serfcompany.ecommerce.acart.Constants;
import com.serfcompany.ecommerce.acart.R;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by serfcompany on 17.03.16.
 */
public class UpdateUserProfileHTTP {
    private final String urlString = Constants.APP_URI +
            "?amazingcart=json-api&type=user-profile-update";

    public String updateProfile(String username, String password, String first_name,
                                 String last_name, String display_name, String email) throws IOException {

        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");

        connection.setDoOutput(true);
        String params = "&username="+username+"&password="+password+
                "&first_name="+first_name+
                "&last_name="+last_name+
                "&display_name="+display_name+
                "&email="+email;

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
        Log.i("LOG", "Profile was updated : " +result.toString());
        return result.toString();
    }
}
