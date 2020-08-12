package br.com.seasyc.providingaccount.views.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.google.android.material.textfield.TextInputLayout;

import br.com.seasyc.providingaccount.R;
import br.com.seasyc.providingaccount.common.CMToken;
import br.com.seasyc.providingaccount.models.User;
import br.com.seasyc.providingaccount.models.auth.Logout;
import br.com.seasyc.providingaccount.preferences.Authentication;
import br.com.seasyc.providingaccount.viewmodels.VMAuthentication;
import br.com.seasyc.providingaccount.viewmodels.VMUser;
import br.com.seasyc.providingaccount.views.activities.LoginActivity;


public class MeFragment extends Fragment implements View.OnClickListener {

    private TextInputLayout name, email, code;
    private Button logout;
    private ProgressBar progressBar;

    private VMAuthentication vmAuthentication;
    private VMUser vmUser;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_me, container, false);

        initComponents(root);
        clickListeners();
        loadUserData();
        return root;
    }

    private void loadUserData() {
        vmUser.profile(new CMToken().header(getContext())).observe(getViewLifecycleOwner(), new Observer<User>() {
            @Override
            public void onChanged(User user) {
                while (user == null) {
                    progressBar.setVisibility(View.VISIBLE);
                }
                progressBar.setVisibility(View.VISIBLE);
                name.getEditText().setText(user.getName());
                email.getEditText().setText(user.getEmail());
                code.getEditText().setText(user.getCode());
            }
        });
    }

    private void initComponents(View v) {
        vmUser = new VMUser();
        progressBar = v.findViewById(R.id.frg_me_pb);
        name = v.findViewById(R.id.frg_me_name);
        email = v.findViewById(R.id.frg_me_email);
        code = v.findViewById(R.id.frg_me_code);
        logout = v.findViewById(R.id.frg_me_logout);
    }

    private void clickListeners() {
        logout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.frg_me_logout:
                logout();
        }
    }

    private void logout() {
        vmAuthentication = new VMAuthentication();
        vmAuthentication.logout(new CMToken().header(getContext())).observe(getViewLifecycleOwner(), new Observer<Logout>() {
            @Override
            public void onChanged(Logout logout) {
                while (logout == null) {
                    progressBar.setVisibility(View.VISIBLE);
                }
                progressBar.setVisibility(View.VISIBLE);
                if (logout.isLogout()) {
                    if (new Authentication(getContext()).logout()) {
                        startActivity(new Intent(getContext(), LoginActivity.class));
                    }
                }
            }
        });
    }
}