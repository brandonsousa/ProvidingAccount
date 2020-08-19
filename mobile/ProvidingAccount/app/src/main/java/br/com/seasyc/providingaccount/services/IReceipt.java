package br.com.seasyc.providingaccount.services;

import java.util.HashMap;
import java.util.List;

import br.com.seasyc.providingaccount.models.Receipt;
import br.com.seasyc.providingaccount.models.responses.Deleted;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IReceipt {
    @GET("/receipt")
    Call<List<Receipt>> receipts(@HeaderMap HashMap<String, String> auth);

    @POST("/receipt")
    Call<Receipt> store(@HeaderMap HashMap<String, String> auth, @Body Receipt receipt);

    @GET("/receipt/{id}")
    Call<Receipt> byId(@HeaderMap HashMap<String, String> auth, @Path("id") int id);

    @DELETE("/receipt/1")
    Call<Deleted> deleteAll(@HeaderMap HashMap<String, String> auth);
}
