package br.com.seasyc.providingaccount.services;

import java.util.HashMap;

import br.com.seasyc.providingaccount.models.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;

public interface IUser {
    @GET("/user")
    Call<User> profile(@HeaderMap HashMap<String, String> auth);
}
