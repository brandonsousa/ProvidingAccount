package br.com.seasyc.providingaccount.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.HashMap;

import br.com.seasyc.providingaccount.models.User;
import br.com.seasyc.providingaccount.repositories.RepoUser;

public class VMUser {

    private RepoUser repoUser;

    private MutableLiveData<User> userMutableLiveData;

    public VMUser() {
        repoUser = new RepoUser();
    }

    public LiveData<User> profile(HashMap<String, String> auth) {
        if (userMutableLiveData == null) {
            userMutableLiveData = repoUser.profile(auth);
        }
        return userMutableLiveData;
    }
}
