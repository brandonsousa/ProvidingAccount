package br.com.seasyc.providingaccount.configs;

import br.com.seasyc.providingaccount.common.Api;
import br.com.seasyc.providingaccount.services.IAuth;
import br.com.seasyc.providingaccount.services.IReceipt;
import br.com.seasyc.providingaccount.services.IUser;
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

    public IAuth auth(){
        return this.retrofit.create(IAuth.class);
    }

    public IUser user(){
        return this.retrofit.create(IUser.class);
    }

    public IReceipt receipt(){
        return this.retrofit.create(IReceipt.class);
    }
}
