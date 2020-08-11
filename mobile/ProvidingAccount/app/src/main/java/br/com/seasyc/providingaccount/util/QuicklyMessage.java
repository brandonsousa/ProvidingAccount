package br.com.seasyc.providingaccount.util;

import android.content.Context;
import android.widget.Toast;

public class QuicklyMessage {
    public static final void toast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
