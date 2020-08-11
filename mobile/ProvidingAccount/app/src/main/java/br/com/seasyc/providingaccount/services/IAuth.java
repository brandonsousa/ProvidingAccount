package br.com.seasyc.providingaccount.services;

import java.util.HashMap;

import br.com.seasyc.providingaccount.models.User;
import br.com.seasyc.providingaccount.models.auth.Login;
import br.com.seasyc.providingaccount.models.auth.Logout;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;

public interface IAuth {
    @POST("/")
    Call<Login> login(@Body User user);

    @GET("/")
    Call<Logout> logout(@HeaderMap HashMap<String, String> auth);
}
