package com.serfcompany.ecommerce.acart;

import java.util.Map;

public class Utils {

    public static String getProdIDJsonFromMap(Map<String, String> map){
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

    public static String getCouponJsonFromMap(Map<String, String> map) {
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
