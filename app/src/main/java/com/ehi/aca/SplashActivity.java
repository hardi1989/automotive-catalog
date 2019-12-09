package com.ehi.aca;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.ehi.aca.data.remote.model.GetManufacturer;
import com.ehi.aca.viewmodel.ManufacturerDetailsViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/*
 * File Description
 * Author: Hardi
 */

public class SplashActivity extends AppCompatActivity {
    private static final String TAG = SplashActivity.class.getName();
    private Context mContext;

    @BindView(R.id.button_ok1)
    Button button_ok1;
    @BindView(R.id.tv_title1)
    TextView tv_title1;
    @BindView(R.id.tv_data1)
    TextView tv_data1;

    ManufacturerDetailsViewModel manufacturerDetailsViewModel;
    private AlertDialog alertDialog_message;
    private Unbinder unbinder;
    private boolean connected = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = SplashActivity.this;

        manufacturerDetailsViewModel = ViewModelProviders.of(this).get(ManufacturerDetailsViewModel.class);

        ConnectionData connectionLiveData = new ConnectionData(mContext);
        connectionLiveData.onActive();
        connectionLiveData.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean connection) {
                /* every time connection state changes, we'll be notified and can perform action accordingly */
                if (connection) {
                    manufacturerDetailsViewModel.init(getApplication(), Global.dataType2);
                    manufacturerDetailsViewModel.getManuFactureData().observe(SplashActivity.this, new Observer<GetManufacturer>() {
                        @Override
                        public void onChanged(GetManufacturer getManufacturer) {

                        }
                    });
                    Intent act_Main = new Intent(mContext, MainActivity.class);
                    startActivity(act_Main);
                    finish();

                } else {
                    connected = false;
                    showDialog(mContext, "", "", "");
                }
            }
        });

    }

    private void showDialog(Context ctx, String title, String message, String btn_text) {


        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
        builder.setCancelable(false);
        LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.alert_dialog, null);
        unbinder = ButterKnife.bind(this, layout);
        button_ok1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                alertDialog_message.dismiss();
            }
        });
        builder.setView(layout);
        alertDialog_message = builder.create();
        alertDialog_message.setCanceledOnTouchOutside(false);
        alertDialog_message.setCancelable(false);
        if (title.length() > 0)
            tv_title1.setText(title);
        if (message.length() > 0)
            tv_data1.setText(message);
        if (btn_text.length() > 0)
            button_ok1.setText(btn_text);
        alertDialog_message.show();

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        unbinder.unbind();
    }
}
