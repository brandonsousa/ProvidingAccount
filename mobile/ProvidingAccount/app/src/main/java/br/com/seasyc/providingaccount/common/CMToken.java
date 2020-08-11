package br.com.seasyc.providingaccount.common;

import android.content.Context;

import java.util.HashMap;

import br.com.seasyc.providingaccount.preferences.Authentication;

public class CMToken {
    public static final String KEY_PREFERENCE = "nncicnbidsbcsnjksfnvkj";
    public static final String TOKEN = "token";
    public static final String CONTENT = "Content-Type";
    public static final String TYPE = "application/json; charset=UTF-8";
    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER = "Bearer ";

    public static HashMap<String, String> header(Context context) {
        Authentication token = new Authentication(context);

        HashMap<String, String> header = new HashMap<>();

        header.put(CONTENT, TYPE);
        header.put(AUTHORIZATION, BEARER + token.getToken());

        return header;
    }
}
