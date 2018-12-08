package marsplay.app.com.marsplayassignment;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Window;

import java.util.Arrays;

import marsplay.app.com.marsplayassignment.Common.Permissions;

public class SplashScreen extends AppCompatActivity {

    int permissionFalseCount = 0;
    boolean granted=false;
    boolean[] permissionsAlertShown = new boolean[3];
    AlertDialog alertDialogPermissions2, alertDialogPermissions3;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);

        initializeProcess();
    }

    private void initializeProcess() {
        if(Permissions.checkPermissions(SplashScreen.this,Permissions.PERMISSION_ESSENTIAL)) {
            //Permissions.
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //Do something after 100ms
                    Intent intent = new Intent(SplashScreen.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, 1000);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case Constants.PERMISSION_REQUEST_GRANT:
                int resLen = permissions.length;
                permissionFalseCount = 0;//reset count for each new perm request
                Arrays.fill(permissionsAlertShown, true);
                for (int i = 0; i < resLen; i++) {
                    Log.i("permissionResult", "" + grantResults[i]);
                    Log.i("permissions", "" + permissions[i]);
                    if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        //all ok
                        granted = true;
                        initializeProcess();

                    } else {
                        if (Build.VERSION.SDK_INT >= 23) {
                            //permissionFalseCount++;
                            boolean showRationale = shouldShowRequestPermissionRationale(permissions[i]);
                            if (!showRationale) {
                                granted = false;
                                switch (permissions[i]) {
                                    case Manifest.permission.WRITE_EXTERNAL_STORAGE:
                                    case Manifest.permission.READ_EXTERNAL_STORAGE:
                                        permissionsAlertShown[0] = false;
                                        permissionsAlertShown[1] = false;
                                        alertDialogPermissions3 = Permissions.openPermissions(SplashScreen.this, getString(R.string.external_storage_permission), getString(
                                                R.string.external_storage_permission));
                                        break;
                                    case Manifest.permission.CAMERA:
                                        permissionsAlertShown[2] = false;
                                        Permissions.openPermissions(SplashScreen.this, getString(R.string.camera_permission), getString(
                                                R.string.camera_permission_message));
                                        break;
                                }
                            } else {
                                granted = true;
                                initializeProcess();
                            }
                        } else {
                            granted = true;
                            initializeProcess();
                        }
                    }
                }
                //initializeProcess();
                break;
        }

    }
}
