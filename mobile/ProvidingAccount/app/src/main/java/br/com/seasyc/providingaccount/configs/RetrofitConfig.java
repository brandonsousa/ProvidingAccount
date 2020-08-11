package br.com.seasyc.providingaccount.configs;

import br.com.seasyc.providingaccount.common.Api;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitConfig {
    private final Retrofit retrofit;

    public RetrofitConfig() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }


}
