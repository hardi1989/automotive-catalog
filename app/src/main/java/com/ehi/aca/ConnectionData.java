package com.ehi.aca;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

/*
 * File Description
 * Author: Hardi
 */


public class ConnectionData extends LiveData<Boolean> {
    Context context;

    ConnectionData(Context context)
    {
        this.context = context;
    }

    @Override
    protected void onActive() {
        super.onActive();
        Global.eLog("CheckConnection onActive",Global.isConnected+"");
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
            context.registerReceiver(networkReceiver, filter);
            return;
        } else {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkRequest.Builder builder = new NetworkRequest.Builder();
            connectivityManager.registerNetworkCallback(builder.build(), networkCallback);
        }
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        Global.eLog("CheckConnection In onActive",Global.isConnected+"");
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            context.unregisterReceiver(networkReceiver);
        } else {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            connectivityManager.unregisterNetworkCallback(networkCallback);
        }
    }

    private BroadcastReceiver networkReceiver = new BroadcastReceiver() {
        @SuppressWarnings("deprecation")
        @Override
        public void onReceive(Context context, Intent intent) {
            Global.eLog("CheckConnection networkReceiver",Global.isConnected+"");
            if (intent.getExtras() != null) {
                NetworkInfo activeNetwork = (NetworkInfo) intent.getExtras().get(ConnectivityManager.EXTRA_NETWORK_INFO);
                boolean isConnected = activeNetwork != null &&
                        activeNetwork.isConnectedOrConnecting();
                if (isConnected) {
                    Global.isConnected=true;
                    Global.eLog("CheckConnection if",Global.isConnected+"");
                    postValue(true);
                }
            } else {
                Global.isConnected=false;
                Global.eLog("CheckConnection else",Global.isConnected+"");
                postValue(false);
            }
        }
    };


    ConnectivityManager.NetworkCallback networkCallback = new ConnectivityManager.NetworkCallback() {
        @Override
        public void onAvailable(@NonNull Network network) {
            super.onAvailable(network);
            Global.isConnected=true;
            Global.eLog("CheckConnection onAvailable",Global.isConnected+"");
            postValue(true);
        }

        @Override
        public void onLosing(@NonNull Network network, int maxMsToLive) {
            super.onLosing(network, maxMsToLive);
            Global.isConnected=false;
            Global.eLog("CheckConnection onLosing",Global.isConnected+"");
            postValue(false);
        }

        @Override
        public void onLost(@NonNull Network network) {
            Global.isConnected=false;
            Global.eLog("CheckConnection onLost",Global.isConnected+"");
            postValue(false);
        }
    };


}
