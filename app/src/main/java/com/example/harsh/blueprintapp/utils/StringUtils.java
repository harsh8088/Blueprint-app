package com.example.harsh.blueprintapp.utils;

import android.text.TextUtils;
import android.util.Patterns;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class StringUtils {

    private StringUtils() {
    }

    public final static boolean isValidWebSite(String url) {
        return Patterns.WEB_URL.matcher(url).matches();
    }

    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    public final static boolean isValidMobile(String phone) {
        boolean check = false;
        if (!Pattern.matches("[a-zA-Z]+", phone)) {
            check = phone.length() >= 10 && phone.length() <= 15;
        } else {
            check = false;
        }
        return check;
    }

    public static boolean isValidPhoneNo(String number) {
        String regex = "^\\+?[0-9. ()-]{10,25}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(number);
        boolean digit;
        switch (number) {
            case "0000000000":
            case "00000000000":
            case "000000000000":
            case "0000000000000":
                digit = false;
                break;
            default:
                digit = true;
                break;
        }
        return matcher.matches() && digit;
    }

    // validating email id
    public static boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // validating password with retype password
    public static boolean isValidPassword(String pass) {
        return pass != null && pass.length() > 5;
    }

    /**
     * Normalize a phone number by removing the characters other than digits.
     *
     * @param number - Number to normalize
     * @return
     */
    public static String normalizeNumber(String number) {
        return number.replaceAll("[\\D]", "");
    }

    public static String convertToLong(String exp) {
        try {
            BigDecimal d = new BigDecimal(exp);
            return String.valueOf(d.longValue());
        } catch (Exception e) {
            return exp;
        }
    }

    public static int convertToInt(String exp) {
        try {
            if (TextUtils.isEmpty(exp))
                return 0;
            return Integer.parseInt(exp);
        } catch (Exception e) {
            return 0;
        }
    }

    public static boolean isScientificNotation(String numberString) {
        // Validate number
        try {
            new BigDecimal(numberString);
        } catch (Exception e) {
            return false;
        }
        // Check for scientific notation
        return numberString.toUpperCase().contains("E");
    }

    public static String assignEmptyIfNull(String str) {
        if (str == null) {
            return "";
        } else {
            return str;
        }
    }

    public static String getDomain(String email) {
        if (TextUtils.isEmpty(email))
            return null;
        try {
            String[] parts = email.split("@");
            return parts[1].substring(0, parts[1].indexOf('.'));
        } catch (Exception e) {
            return null;
        }
    }

    public static String capsFirstCharacter(String headerText) {
        return headerText.substring(0, 1).toUpperCase() + headerText.substring(1);
    }
}
