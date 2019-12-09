package com.ehi.aca;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/*
 * File Description
 * Author: Hardi
 */

public class Global {

    public static boolean showToast = true;
    public static boolean showLog = true;

    public static boolean isConnected=false;

    public static String baseUrl = "https://vpic.nhtsa.dot.gov/api/vehicles/";

    public static void eLog(String tagName, String logInfo) {
        if (showLog)
            Log.i(tagName, logInfo);
    }

    public static void eToast(Context ctx, String msg) {
        if (showToast)
            Toast.makeText(ctx, msg, Toast.LENGTH_LONG).show();
    }
}
