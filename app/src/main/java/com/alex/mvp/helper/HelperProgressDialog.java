package com.alex.mvp.helper;

import android.app.ProgressDialog;
import android.content.Context;

import com.alex.weather.R;

public class HelperProgressDialog {

    public HelperProgressDialog(Context ctx){
        initProgressDialog(ctx);
    }

    private ProgressDialog progressDialog;
    private void initProgressDialog(Context ctx) {
        progressDialog = new ProgressDialog(ctx);
        progressDialog.setTitle(R.string.loading);
        progressDialog.setMessage(ctx.getResources().getString(R.string.please_wait));
    }

    public void showProgress(){
        progressDialog.show();
    }
    public void dismissProgress(){
        progressDialog.dismiss();
    }


}
