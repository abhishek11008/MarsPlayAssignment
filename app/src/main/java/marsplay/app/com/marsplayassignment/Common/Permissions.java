package marsplay.app.com.marsplayassignment.Common;

import android.Manifest.permission;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.security.Permission;

import marsplay.app.com.marsplayassignment.Constants;
import marsplay.app.com.marsplayassignment.R;

/**
 * Created by Abhishek Singh on 5/3/2016.
 */
public class Permissions {

    public static final int PERMISSION_ALL = 0;
    public static final int PERMISSION_CAMERA = 1;
    public static final int PERMISSION_PHONE = 2;
    public static final int PERMISSION_CONTACTS = 3;
    public static final int PERMISSION_STORAGE = 4;
    public static final int PERMISSION_SMS = 5;
    public static final int PERMISSION_LOCATION = 6;
    public static final int PERMISSION_ESSENTIAL = 7;
    public static final int PERMISSION_RECORDING = 8;
    public static final int PERMISSION_READ_PHONE_STATE = 9;
    /**
     * Use for checking permissions on runtime on Marshmallow+
     *
     * @param context pass current @activity
     * @param permission pass the Manifest.permission.WRITE_CALENDER
     * @return PackageManager.PERMISSION_GRANTED or PERMISSION_DENIED
     */
    //these are essential permissions
    //^^Can reqeust only one set of permissions at a time
    public static String[] essentialPermissions = {
            permission.READ_EXTERNAL_STORAGE,
            permission.WRITE_EXTERNAL_STORAGE,
            permission.CAMERA
    };
    /**
     * DONOT GO ON NAMES. :) Comes at later stage so.
     */
    public static String[] allPermissions = {
            permission.READ_CONTACTS,
            permission.RECEIVE_SMS,
            permission.READ_SMS,
            permission.WRITE_EXTERNAL_STORAGE,
            permission.READ_EXTERNAL_STORAGE,
            permission.CAMERA,
            permission.RECORD_AUDIO
    };
    public static String[] cameraPermissions = {permission.CAMERA};
    public static String[] phonePermissions = {permission.CALL_PHONE, permission.READ_PHONE_STATE};
    public static String[] contactPermissions = {permission.READ_CONTACTS};
    public static String[] readPhoneStatePermission = {permission.READ_PHONE_STATE};
    public static String[] storagePermissions = {permission.WRITE_EXTERNAL_STORAGE, permission.READ_EXTERNAL_STORAGE};
    public static String[] smsPermissions = {permission.RECEIVE_SMS, permission.SEND_SMS, permission.READ_SMS, permission.SEND_SMS};
    public static String[] locationPermissions = {permission.ACCESS_FINE_LOCATION, permission.ACCESS_COARSE_LOCATION};
    public static String[] recordingPermissions = { permission.RECORD_AUDIO};


    public static int checkPermission(Context context, String permission) {
        return ContextCompat.checkSelfPermission(context, permission);
    }

    public static boolean checkPermissions(Activity activity, int permissionType) {
        return getPermissions(activity, permissionType);
    }

    private static boolean getPermissions(Activity activity, int permissionType) {
        /**
         *   MUST READ
         *   --------------
         *   http://stackoverflow.com/a/33080682/1757480
         * <p>
         *     When in AppCompatActivity, you should use ActivityCompat.requestPermissions;<br>
         *     When in android.support.v4.app.Fragment, you should use simply requestPermissions (this is an instance method of android.support.v4.app.Fragment)<br>
         *   If you call ActivityCompat.requestPermissions in a fragment, the onRequestPermissionsResult callback is called on the activity and not the fragment
         * </p>
         **/
        if (Build.VERSION.SDK_INT >= 23) {
            //ask permission
            //^^Can request only one set of permissions at a time
            switch (permissionType) {
                case 0:
                    boolean readContacts = ContextCompat.checkSelfPermission(activity, Permissions.essentialPermissions[0]) != PackageManager.PERMISSION_GRANTED;
                    boolean receiveSMS = ContextCompat.checkSelfPermission(activity, Permissions.essentialPermissions[0]) != PackageManager.PERMISSION_GRANTED;
                    boolean sendSMS = ContextCompat.checkSelfPermission(activity, Permissions.essentialPermissions[1]) != PackageManager.PERMISSION_GRANTED;
                    boolean readSMS = ContextCompat.checkSelfPermission(activity, Permissions.essentialPermissions[2]) != PackageManager.PERMISSION_GRANTED;
                    boolean writeExternalStorage = ContextCompat.checkSelfPermission(activity, Permissions.essentialPermissions[3]) != PackageManager.PERMISSION_GRANTED;
                    boolean readExternalStorage = ContextCompat.checkSelfPermission(activity, Permissions.essentialPermissions[4]) != PackageManager.PERMISSION_GRANTED;
                    boolean readPhoneState = ContextCompat.checkSelfPermission(activity, Permissions.essentialPermissions[5]) != PackageManager.PERMISSION_GRANTED;

                    if (readContacts || receiveSMS || sendSMS || readSMS || writeExternalStorage || readExternalStorage || readPhoneState) {
                        //permission not granted
                        showPermissionInfoPopup(0, activity);
                    } else {
                        //permission granted
                        return true;
                    }
                    break;
                case 1:
                    boolean cameraPermission = ContextCompat.checkSelfPermission(activity, Permissions.cameraPermissions[0]) != PackageManager.PERMISSION_GRANTED;
                    if (cameraPermission) {
                        showPermissionInfoPopup(1, activity);
                    } else {
                        return true;
                    }
                    break;
                case 2:
                    boolean phonePermission = ContextCompat.checkSelfPermission(activity, Permissions.phonePermissions[0]) != PackageManager.PERMISSION_GRANTED;
                    boolean callPermission = ContextCompat.checkSelfPermission(activity, Permissions.phonePermissions[1]) != PackageManager.PERMISSION_GRANTED;
                    if (phonePermission || callPermission) {
                        showPermissionInfoPopup(2, activity);
                    } else {
                        return true;
                    }
                    break;
                case 3:
                    if (activity != null) {
                        boolean contactsPermission = ContextCompat.checkSelfPermission(activity, Permissions.contactPermissions[0]) != PackageManager.PERMISSION_GRANTED;
                        if (contactsPermission) {
                            showPermissionInfoPopup(3, activity);
                        } else {
                            return true;
                        }
                    } else {

                        Log.d("Permission", "activity null");
                    }
                    break;
                case 4:
                    boolean storageWritePermission = ContextCompat.checkSelfPermission(activity, Permissions.storagePermissions[0]) != PackageManager.PERMISSION_GRANTED;
                    boolean storageReadPermission = ContextCompat.checkSelfPermission(activity, Permissions.storagePermissions[1]) != PackageManager.PERMISSION_GRANTED;
                    if (storageReadPermission || storageWritePermission) {
                        showPermissionInfoPopup(4, activity);
                    } else {
                        return true;
                    }
                    break;
                case 5:
                    boolean smsReceivePermission = ContextCompat.checkSelfPermission(activity, Permissions.smsPermissions[0]) != PackageManager.PERMISSION_GRANTED;
                    boolean smsSendPermission = ContextCompat.checkSelfPermission(activity, Permissions.smsPermissions[1]) != PackageManager.PERMISSION_GRANTED;
                    boolean smsReadPermission = ContextCompat.checkSelfPermission(activity, Permissions.smsPermissions[2]) != PackageManager.PERMISSION_GRANTED;
                    if (smsReadPermission || smsReceivePermission || smsSendPermission) {
                        showPermissionInfoPopup(5, activity);
                    } else {
                        return true;
                    }
                    break;

              /*  case 6:
                    boolean fineLocationPermission = ContextCompat.checkSelfPermission(activity, Permissions.locationPermissions[0]) != PackageManager.PERMISSION_GRANTED;
                    boolean coarseLocationPermission = ContextCompat.checkSelfPermission(activity, Permissions.locationPermissions[1]) != PackageManager.PERMISSION_GRANTED;
                    if (fineLocationPermission || coarseLocationPermission) {
                        showPermissionInfoPopup(6, activity);
                    } else {
                        return true;
                    }
                    break;*/
                case 7:
                    writeExternalStorage = ContextCompat.checkSelfPermission(activity, Permissions.essentialPermissions[0]) != PackageManager.PERMISSION_GRANTED;
                    readExternalStorage = ContextCompat.checkSelfPermission(activity, Permissions.essentialPermissions[1]) != PackageManager.PERMISSION_GRANTED;
                    cameraPermission = ContextCompat.checkSelfPermission(activity, Permissions.essentialPermissions[2]) != PackageManager.PERMISSION_GRANTED;

                    if (writeExternalStorage || readExternalStorage || cameraPermission ) {
                        //permission not granted
                        showPermissionInfoPopup(7, activity);
                    } else {
                        //permission granted
                        return true;
                    }
                    break;
                case 9:
                    if (activity != null) {
                        boolean readPhoneStatePer = ContextCompat.checkSelfPermission(activity,
                            Permissions.readPhoneStatePermission[0]) != PackageManager.PERMISSION_GRANTED;
                        if (readPhoneStatePer) {
                            showPermissionInfoPopup(9, activity);
                        } else {
                            return true;
                        }
                    } else {

                        Log.d("Permission", "activity null");
                    }
                    break;
            }

        } else {
            return true;

        }
        return false;
    }

    /**
     * Activity should implement method
     * onRequestPermissionsResult() in calling activity
     **/
    private static void showPermissionInfoPopup(int permissionType, Activity activityX) {
        final Activity activity = activityX;
        switch (permissionType) {
            case 0:
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                View view = activity.getLayoutInflater().inflate(R.layout.alert_permissons, null);
                builder.setView(view);
                final AlertDialog alertDialog = builder.create();
                alertDialog.setCancelable(false);
                alertDialog.setCanceledOnTouchOutside(false);
                Button button = (Button) view.findViewById(R.id.buttonContinue);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                        if (Build.VERSION.SDK_INT >= 23) {
                            activity.requestPermissions(Permissions.essentialPermissions, Constants.PERMISSION_REQUEST_GRANT);
                        }

                    }
                });
                alertDialog.show();
                break;
            case 1:
                if (Build.VERSION.SDK_INT >= 23) {
                    activity.requestPermissions(Permissions.cameraPermissions, Constants.PERMISSION_REQUEST_GRANT);
                }
                break;
            case 2:
                if (Build.VERSION.SDK_INT >= 23) {
                    activity.requestPermissions(Permissions.phonePermissions, Constants.PERMISSION_REQUEST_GRANT);
                }
                break;
            case 3:
                if (Build.VERSION.SDK_INT >= 23) {
                    activity.requestPermissions(Permissions.contactPermissions, Constants.PERMISSION_REQUEST_GRANT);
                }
                break;
            case 4:
                if (Build.VERSION.SDK_INT >= 23) {
                    activity.requestPermissions(Permissions.storagePermissions, Constants.PERMISSION_REQUEST_GRANT);
                }
                break;
            case 5:
                if (Build.VERSION.SDK_INT >= 23) {
                    activity.requestPermissions(Permissions.smsPermissions, Constants.PERMISSION_REQUEST_GRANT);
                }
                break;
            case 6:
                if (Build.VERSION.SDK_INT >= 23) {
                    activity.requestPermissions(Permissions.locationPermissions, Constants.PERMISSION_REQUEST_GRANT);
                }
                break;
            case 7:
                if (Build.VERSION.SDK_INT >= 23) {
                    activity.requestPermissions(Permissions.essentialPermissions, Constants.PERMISSION_REQUEST_GRANT);
                }
                break;
            case 9: {
                if (Build.VERSION.SDK_INT >= 23) {
                    activity.requestPermissions(Permissions.readPhoneStatePermission,
                        PERMISSION_READ_PHONE_STATE);
                }
                break;
            }
        }
    }


    public static AlertDialog openPermissions(final Activity context, String header, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(header);
        builder.setMessage(message);
        builder.setNegativeButton(R.string.deny, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                context.finish();
            }
        });

        builder.setPositiveButton(R.string.allow, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                Uri uri = Uri.fromParts("package", context.getPackageName(), null);
                intent.setData(uri);
                context.startActivityForResult(intent, Constants.PERMISSION_REQUEST_GRANT);
                dialogInterface.dismiss();
                context.finish();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(false);
        if (!(context.isFinishing())) {
            alertDialog.show();
        }
        return alertDialog;
    }


}
