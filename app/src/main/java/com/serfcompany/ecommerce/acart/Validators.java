package com.serfcompany.ecommerce.acart;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validators {

    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
    private static final String LOGIN_PATTERN = "^[a-z0-9_-]{3,15}$";
    private static final String NAMES_PATTERN = "^[a-zA-Z0-9_-]{2,15}$";

    public static boolean isValidEmailAddress(String email) {
        Pattern p = Pattern.compile(EMAIL_PATTERN);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    public static boolean isValidLoginName(String login) {
        Pattern p = Pattern.compile(LOGIN_PATTERN);
        Matcher m = p.matcher(login);
        return m.matches();
    }
    public static boolean isValidNames(String name) {
        Pattern p = Pattern.compile(NAMES_PATTERN);
        Matcher m = p.matcher(name);
        return m.matches();
    }
}
