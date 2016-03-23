package com.serfcompany.ecommerce.acart.HTTPHolders;

import android.util.Log;

import com.serfcompany.ecommerce.acart.Constants;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by serfcompany on 22.03.16.
 */
public class GetCommentByPostID {

    String urlString = Constants.APP_URI+"?amazingcart=json-api&type=comment-by-post-id";

    public String getComments(String id, String parentID, String username, String password) throws IOException {
        URL url = new URL(urlString+"&id="+id+"&parent="+parentID);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");

        String params = "&username=" + username +
                "&password=" + password;

        Log.i("LOG", "GETTING COMMENTS");

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
