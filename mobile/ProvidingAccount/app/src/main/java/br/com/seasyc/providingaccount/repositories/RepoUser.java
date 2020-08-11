package br.com.seasyc.providingaccount.repositories;

import androidx.lifecycle.MutableLiveData;

import java.util.HashMap;

import br.com.seasyc.providingaccount.configs.RetrofitConfig;
import br.com.seasyc.providingaccount.models.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepoUser {
    public MutableLiveData<User> profile(HashMap<String, String> auth) {
        final MutableLiveData<User> profile = new MutableLiveData<>();
        Call<User> userCall = new RetrofitConfig().user().profile(auth);
        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                profile.setValue(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
        return profile;
    }
}
