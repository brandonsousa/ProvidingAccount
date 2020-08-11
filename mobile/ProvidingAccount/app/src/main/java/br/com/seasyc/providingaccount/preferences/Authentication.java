package br.com.seasyc.providingaccount.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import br.com.seasyc.providingaccount.common.CMPreference;
import br.com.seasyc.providingaccount.common.CMToken;

public class Authentication {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Context context;

    public Authentication(Context context) {
        this.context = context;
    }

    public String getToken() {
        if (hasToken()) {
            sharedPreferences = context.getSharedPreferences(CMPreference.FILE_PREFERENCE, Context.MODE_PRIVATE);
            if (sharedPreferences.contains(CMToken.KEY_PREFERENCE)) {
                return sharedPreferences.getString(CMToken.KEY_PREFERENCE, null);
            }
        }
        return null;
    }

    public void setToken(String token) {
        sharedPreferences = context.getSharedPreferences(CMPreference.FILE_PREFERENCE, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString(CMToken.KEY_PREFERENCE, token);
        editor.commit();
    }

    public boolean hasToken() {
        sharedPreferences = context.getSharedPreferences(CMPreference.FILE_PREFERENCE, Context.MODE_PRIVATE);
        if (sharedPreferences.contains(CMToken.KEY_PREFERENCE)) {
            return true;
        }
        return false;
    }

    public boolean logout() {
        sharedPreferences = context.getSharedPreferences(CMPreference.FILE_PREFERENCE, Context.MODE_PRIVATE);
        if (hasToken()) {
            editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();
            return true;
        }
        return false;
    }
}
