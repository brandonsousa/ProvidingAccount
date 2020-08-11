package br.com.seasyc.providingaccount.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.HashMap;

import br.com.seasyc.providingaccount.models.User;
import br.com.seasyc.providingaccount.models.auth.Login;
import br.com.seasyc.providingaccount.models.auth.Logout;
import br.com.seasyc.providingaccount.repositories.RepoAuthentication;

public class VMAuthetication {

    private RepoAuthentication repoAuthentication;

    private MutableLiveData<Login> loginMutableLiveData;
    private MutableLiveData<Logout> logoutMutableLiveData;

    public VMAuthetication() {
        repoAuthentication = new RepoAuthentication();
    }

    public LiveData<Login> login(User user){
        if (loginMutableLiveData == null){
            loginMutableLiveData = repoAuthentication.login(user);
        }
        return loginMutableLiveData;
    }

    public LiveData<Logout> logout(HashMap<String, String> auth){
        if (logoutMutableLiveData == null){
            logoutMutableLiveData = repoAuthentication.logout(auth);
        }
        return logoutMutableLiveData;
    }

}
