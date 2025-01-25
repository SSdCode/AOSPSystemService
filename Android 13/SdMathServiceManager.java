package android.app;

import android.annotation.SdkConstant;
import android.annotation.SystemApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.os.ISdMathService;
import android.util.Log;

public class SdMathServiceManager {

    private static final String TAG = "SdMathServiceManager";
    private ISdMathService mService;

    /**
    * @hide
    */
    public SdMathServiceManager(Context context, ISdMathService service) {
        mService = service;
    }

    public int add(int no1, int no2) {
        try {
            return mService.add(no1, no2);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
            e.printStackTrace();
        }
        return 0;
    }

    public int multiply(int no1, int no2) {
        try {
            return mService.multiply(no1, no2);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
            e.printStackTrace();
        }
        return 0;
    }
}