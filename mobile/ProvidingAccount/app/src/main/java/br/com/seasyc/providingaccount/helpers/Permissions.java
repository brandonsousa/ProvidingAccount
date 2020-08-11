package br.com.seasyc.providingaccount.helpers;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class Permissions {
    public static boolean ValidatePermission(Activity activity, String[] permissions, int requestCode) {

        List<String> permissionList = new ArrayList<String>();

        if (Build.VERSION.SDK_INT >= 23) {
            for (String p : permissions) {
                boolean validadte = ContextCompat.checkSelfPermission(activity, p) == PackageManager.PERMISSION_GRANTED;
                if (!validadte) permissionList.add(p);
            }

            if (permissionList.isEmpty()) return true;

            String[] permissionArray = new String[permissionList.size()];
            permissionList.toArray(permissionArray);

            ActivityCompat.requestPermissions(activity, permissionArray, requestCode);
        }

        return true;
    }
}
