package br.com.seasyc.providingaccount.repositories;

import androidx.lifecycle.MutableLiveData;

import java.util.HashMap;
import java.util.List;

import br.com.seasyc.providingaccount.configs.RetrofitConfig;
import br.com.seasyc.providingaccount.models.Receipt;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepoReceipts {

    public MutableLiveData<List<Receipt>> receipts(HashMap<String, String> auth) {
        final MutableLiveData<List<Receipt>> receipts = new MutableLiveData<>();
        Call<List<Receipt>> listCall = new RetrofitConfig().receipt().receipts(auth);
        listCall.enqueue(new Callback<List<Receipt>>() {
            @Override
            public void onResponse(Call<List<Receipt>> call, Response<List<Receipt>> response) {
                receipts.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Receipt>> call, Throwable t) {

            }
        });
        return receipts;
    }

    public MutableLiveData<Receipt> store(HashMap<String, String> auth, Receipt receipt) {
        final MutableLiveData<Receipt> store = new MutableLiveData<>();
        Call<Receipt> receiptCall = new RetrofitConfig().receipt().store(auth, receipt);
        receiptCall.enqueue(new Callback<Receipt>() {
            @Override
            public void onResponse(Call<Receipt> call, Response<Receipt> response) {
                store.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Receipt> call, Throwable t) {

            }
        });
        return store;
    }

    public MutableLiveData<Receipt> byId(HashMap<String, String> auth, int id) {
        final MutableLiveData<Receipt> byId = new MutableLiveData<>();
        Call<Receipt> receiptCall = new RetrofitConfig().receipt().byId(auth, id);
        receiptCall.enqueue(new Callback<Receipt>() {
            @Override
            public void onResponse(Call<Receipt> call, Response<Receipt> response) {
                byId.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Receipt> call, Throwable t) {

            }
        });
        return byId;
    }


}
