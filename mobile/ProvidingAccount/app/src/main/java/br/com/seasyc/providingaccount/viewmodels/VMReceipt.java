package br.com.seasyc.providingaccount.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.HashMap;
import java.util.List;

import br.com.seasyc.providingaccount.models.Receipt;
import br.com.seasyc.providingaccount.repositories.RepoReceipts;

public class VMReceipt {
    private RepoReceipts repoReceipts;

    private MutableLiveData<Receipt> receiptMutableLiveData;
    private MutableLiveData<List<Receipt>> listMutableLiveData;

    public VMReceipt() {
        repoReceipts = new RepoReceipts();
    }

    public LiveData<Receipt> store(HashMap<String, String> auth, Receipt receipt){
        if (receiptMutableLiveData == null){
            receiptMutableLiveData = repoReceipts.store(auth, receipt);
        }
        return receiptMutableLiveData;
    }

    public LiveData<List<Receipt>> all(HashMap<String, String> auth){
        if (listMutableLiveData == null){
            listMutableLiveData = repoReceipts.receipts(auth);
        }
        return listMutableLiveData;
    }

    private LiveData<Receipt> byId(HashMap<String,String> auth, int id){
        if (receiptMutableLiveData == null){
            receiptMutableLiveData = repoReceipts.byId(auth, id);
        }
        return receiptMutableLiveData;
    }

}
