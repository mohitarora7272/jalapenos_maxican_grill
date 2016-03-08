package com.serfcompany.ecommerce.acart.HTTPHolders;

import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Map;

/**
 * Created by serfcompany on 07.03.16.
 */
public class CheckCartHTTP {

    private static final String urlString =
            "http://woocommerce.serfcompany.com/?amazingcart=json-api&type=cart-api";

    public String loadCart(String username, String password,
                            Map<String, String> idQuantityMap, Map<String, String> couponCode){
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");

            String params = "&username=" + username +
                    "&password=" + password +
                    "&productIDJson=" + getProdIDJsonFromMap(idQuantityMap) +
                    "&couponCodeJson=" + getCouponJsonFromMap(couponCode);

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
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getProdIDJsonFromMap (Map<String, String> map){
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (Map.Entry entry : map.entrySet()){
            sb.append("\"");
            sb.append(entry.getKey());
            sb.append("\":\"");
            sb.append(entry.getValue());
            sb.append("\"");
            if (map.entrySet().size() > 1) {sb.append(",");}
        }
        if (map.entrySet().size() > 1) {
            sb.deleteCharAt(sb.length()-1);
        }
        sb.append("}");
        return sb.toString();
    }

    private String getCouponJsonFromMap (Map<String, String> map) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (map == null) {
            return "";
        } else {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                sb.append("\"");
                sb.append(entry.getKey());
                sb.append("\":\"");
                sb.append(entry.getValue());
                sb.append("\"");
                if (map.entrySet().size() > 1) {
                    sb.append(",");
                }
            }
            if (map.entrySet().size() > 1) {
                sb.deleteCharAt(sb.length() - 1);
            }
            sb.append("}");
            return sb.toString();
        }
    }

}
