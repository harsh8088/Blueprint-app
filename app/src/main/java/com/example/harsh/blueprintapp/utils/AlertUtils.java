package com.example.harsh.blueprintapp.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.ContextThemeWrapper;

import com.example.harsh.blueprintapp.R;


public final class AlertUtils {

    private AlertUtils() {
    }

    public interface OnAlertClickListener {

        void onNegativeClick(DialogInterface dialog);

        void onPositiveClick(DialogInterface dialog);
    }


    public static AlertDialog showOkDialog(Context context, String title, String message) {
        return new AlertDialog.Builder(new ContextThemeWrapper(context, R.style.DefaultAlertDialog))
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    public static AlertDialog showOkDialog(Context context, String title, String message, final OnAlertClickListener listener) {
        return new AlertDialog.Builder(new ContextThemeWrapper(context, R.style.DefaultAlertDialog))
                .setTitle(title)
                .setMessage(message)
                .setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        if (listener != null)
                            listener.onNegativeClick(dialogInterface);
                    }
                })
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (listener != null)
                            listener.onPositiveClick(dialog);
                    }
                })
                .show();
    }

    public static AlertDialog showOkDialogNonCancelable(Context context, String title, String message, final OnAlertClickListener listener) {
        return new AlertDialog.Builder(new ContextThemeWrapper(context, R.style.DefaultAlertDialog))
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (listener != null)
                            listener.onPositiveClick(dialog);
                    }
                })
                .setCancelable(false)
                .show();
    }

    public static AlertDialog showYesNoDialog(Context context, String title, String message, final OnAlertClickListener listener) {
        return new AlertDialog.Builder(new ContextThemeWrapper(context, R.style.DefaultAlertDialog))
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (listener != null)
                            listener.onPositiveClick(dialog);
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (listener != null)
                            listener.onNegativeClick(dialog);
                    }
                })
                .show();
    }

    public static AlertDialog showOkCancelDialog(Context context, String title, String message, final OnAlertClickListener listener) {
        return new AlertDialog.Builder(new ContextThemeWrapper(context, R.style.DefaultAlertDialog))
                .setTitle(title)
                .setMessage(message)
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (listener != null)
                            listener.onNegativeClick(dialog);
                    }
                })
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (listener != null)
                            listener.onPositiveClick(dialog);
                    }
                })
                .show();
    }


}
