package br.com.seasyc.providingaccount.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.google.android.material.textfield.TextInputLayout;

import br.com.seasyc.providingaccount.R;
import br.com.seasyc.providingaccount.models.User;
import br.com.seasyc.providingaccount.models.auth.Login;
import br.com.seasyc.providingaccount.preferences.Authentication;
import br.com.seasyc.providingaccount.util.QuicklyMessage;
import br.com.seasyc.providingaccount.viewmodels.VMAuthentication;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout code, password;
    private ProgressBar progressBar;

    private VMAuthentication vmAuthentication;

    private Authentication authentication;

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initDependencies();
        //TODO: remove this
        if (!authentication.hasToken()) {
            goToMain();
        }

        initComponents();
    }

    private void initDependencies() {
        authentication = new Authentication(this);
        vmAuthentication = new VMAuthentication();
    }

    private void initComponents() {
        code = findViewById(R.id.edtCode);
        password = findViewById(R.id.edtPassword);
        progressBar = findViewById(R.id.pb_login);
    }

    private void goToMain() {
        startActivity(new Intent(this, MainActivity.class));
    }

    public void signin(View view) {
        if (verification()) {
            setUser();
            vmAuthentication.login(user).observe(this, new Observer<Login>() {
                @Override
                public void onChanged(Login login) {
                    while (login == null) {
                        progressBar.setVisibility(View.VISIBLE);
                    }
                    progressBar.setVisibility(View.GONE);
                    if (login.isLogin()) {
                        authentication.setToken(login.getToken(), login.getCode());
                        goToMain();
                    } else {
                        QuicklyMessage.toast(getApplicationContext(), login.getToken());
                    }
                }
            });
        }
    }

    private void setUser() {
        user = new User();
        user.setCode(code.getEditText().getText().toString());
        user.setPassword(password.getEditText().getText().toString());
    }

    private boolean verification() {
        if (code.getEditText().getText().length() < 6) {
            code.setError("Invalid code");
            if (password.getEditText().getText().length() < 8) {
                password.setError("Invalid password");
                return false;
            }
        }
        code.setError(null);
        password.setError(null);
        return true;
    }
}