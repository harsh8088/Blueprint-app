package com.example.harsh.blueprintapp.utils;

import android.content.ActivityNotFoundException;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.ContactsContract;
import android.text.InputFilter;
import android.text.TextUtils;
import android.widget.EditText;

import com.example.harsh.blueprintapp.R;
import com.example.harsh.blueprintapp.base.BaseActivity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public final class AppUtils {

    public static String getVersionName(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(
                    context.getPackageName(), 0);
            return info.versionName;
        } catch (Exception e) {
            LogUtils.printStackTrace(e);
        }
        return null;
    }

    //Other Methods
    public static <T> void copy(ArrayList<T> source, ArrayList<T> destination) {
        for (T t : source) {
            destination.add(t);
        }
    }

    public static <T> List<T> getList(final Class<T[]> clazz, final String json) {
        final T[] jsonToObject = new Gson().fromJson(json, clazz);
        return Arrays.asList(jsonToObject);
    }

    public static void setMaxLength(EditText editText, String sLimit, int defLimit) {
        int length;
        if (TextUtils.isEmpty(sLimit) || getFieldLength(sLimit) == 0) {
            length = defLimit;
        } else {
            length = getFieldLength(sLimit);
        }
        InputFilter[] filters = new InputFilter[1];
        filters[0] = new InputFilter.LengthFilter(length);
        editText.setFilters(filters);
    }

    public static int getFieldLength(String value) {
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            return 0;
        }
    }

    public static void sendEmail(BaseActivity context, String[] emailTo,
                                 String subject, String textBody) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL, emailTo);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, textBody);
        try {
            context.startActivity(Intent.createChooser(intent, context.getString(R.string.label_send_mail)));
        } catch (android.content.ActivityNotFoundException ex) {
            context.showToast(context.getString(R.string.label_no_email_client));
        }
    }

    public static void makeCall(Context context, String phone) {
        Intent intent = new Intent(Intent.ACTION_DIAL,
                Uri.parse("tel:" + phone));
        context.startActivity(intent);
    }

    public static void openChrome(Context context, String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setPackage("com.android.chrome");
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            // Chrome is probably not installed
            // Try with the default browser
            intent.setPackage(null);
            context.startActivity(intent);
        }
    }

    public static void openWebsite(BaseActivity context, String webUrl) {
        if (!TextUtils.isEmpty(webUrl)) {
            if (!webUrl.startsWith("http://") && !webUrl.startsWith("https://"))
                webUrl = "http://" + webUrl;
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(webUrl));
            context.startActivity(intent);
        } else {
            context.showToast(context.getString(R.string.error_invalid_url));
        }
    }

    public static Uri getContactImageUri(long contactId) {
        Uri contactUri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, contactId);
        return Uri.withAppendedPath(contactUri, ContactsContract.Contacts.Photo.CONTENT_DIRECTORY);
    }
}
