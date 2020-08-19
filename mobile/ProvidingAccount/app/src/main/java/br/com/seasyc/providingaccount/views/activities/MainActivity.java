package br.com.seasyc.providingaccount.views.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import br.com.seasyc.providingaccount.R;
import br.com.seasyc.providingaccount.common.CMToken;
import br.com.seasyc.providingaccount.models.auth.Logout;
import br.com.seasyc.providingaccount.models.responses.Deleted;
import br.com.seasyc.providingaccount.preferences.Authentication;
import br.com.seasyc.providingaccount.services.FStorage.Delete;
import br.com.seasyc.providingaccount.util.QuicklyMessage;
import br.com.seasyc.providingaccount.viewmodels.VMAuthentication;
import br.com.seasyc.providingaccount.viewmodels.VMReceipt;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_receipts, R.id.navigation_add, R.id.navigation_me)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.main_activity_menu_pey_off:
                payOff();
                return true;
            case R.id.main_activity_menu_logout:
                logout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void payOff() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Warning");
        builder.setMessage("You are about to pay off, make sure that the month is over and that your accounts have already been lowered");
        builder.setPositiveButton("Pay off", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                new VMReceipt().deleteAll(new CMToken().header(getApplicationContext())).observe(MainActivity.this, new Observer<Deleted>() {
                    @Override
                    public void onChanged(Deleted deleted) {
                        while (deleted == null) {
                            QuicklyMessage.toast(getApplicationContext(), "Wait... deleteing");
                        }
                        if (deleted.isDeleted()) {
                            Delete.file(MainActivity.this, new Authentication(MainActivity.this).getCode());
                            deleted.getData();
                            Intent intent = getIntent();
                            finish();
                            startActivity(intent);
                        }
                        QuicklyMessage.toast(getApplicationContext(), deleted.getData());
                    }
                });
            }
        });
        builder.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void logout() {
        new VMAuthentication().logout(new CMToken().header(this)).observe(this, new Observer<Logout>() {
            @Override
            public void onChanged(Logout logout) {
                if (new Authentication(MainActivity.this).logout()) {
                    QuicklyMessage.toast(MainActivity.this, "logout");
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                }
            }
        });
    }
}