package br.com.seasyc.providingaccount.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.HashMap;

import br.com.seasyc.providingaccount.configs.RetrofitConfig;
import br.com.seasyc.providingaccount.models.User;
import br.com.seasyc.providingaccount.models.auth.Login;
import br.com.seasyc.providingaccount.models.auth.Logout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepoAuthentication {

    public MutableLiveData<Login> login(User user) {
        final MutableLiveData<Login> login = new MutableLiveData<>();
        Call<Login> loginCall = new RetrofitConfig().auth().login(user);
        loginCall.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                login.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
            }
        });
        return login;
    }

    public MutableLiveData<Logout> logout(HashMap<String, String> auth) {
        final MutableLiveData<Logout> logout = new MutableLiveData<>();
        Call<Logout> loginCall = new RetrofitConfig().auth().logout(auth);
        loginCall.enqueue(new Callback<Logout>() {
            @Override
            public void onResponse(Call<Logout> call, Response<Logout> response) {
                logout.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Logout> call, Throwable t) {

            }
        });
        return logout;
    }

}
