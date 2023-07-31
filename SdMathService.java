package com.android.server;

import android.util.Slog;
import android.os.RemoteException;
import android.os.ISdMathService;
import java.util.HashMap;

public class SdMathService extends ISdMathService.Stub {
    private static final String TAG="SdMathService";

    public SdMathService() {
        Slog.d(TAG, "SdMathService starting.");
    }

    public int add(int no1, int no2) throws RemoteException {
        return no1 + no2;
    }

    public int multiply(int no1, int no2) throws RemoteException {
        return no1 * no2;
    }

}
