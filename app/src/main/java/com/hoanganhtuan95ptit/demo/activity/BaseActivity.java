package com.hoanganhtuan95ptit.demo.activity;

import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.nobrain.android.permissions.AndroidPermissions;
import com.nobrain.android.permissions.Checker;
import com.nobrain.android.permissions.Result;

/**
 * Created by Hoang Anh Tuan on 3/10/2017.
 */

public abstract class BaseActivity extends AppCompatActivity {

    public void showMessage(int message) {
        Toast.makeText(this, getString(message), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull final String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        AndroidPermissions.result(this)
                .addPermissions(1, permissions)
                .putActions(1, new Result.Action0() {
                    @Override
                    public void call() {
                        acceptPermisstion(permissions, new String[0]);
                    }
                }, new Result.Action1() {
                    @Override
                    public void call(final String[] hasPermissions, final String[] noPermissions) {
                        acceptPermisstion(hasPermissions, noPermissions);
                    }
                })
                .result(requestCode, permissions, grantResults);
    }

    public void addPermisstions(String... permissions) {
        AndroidPermissions.check(this)
                .permissions(permissions)
                .hasPermissions(new Checker.Action0() {
                    @Override
                    public void call(String[] permissions) {
                        acceptPermisstion(permissions, new String[0]);
                    }
                })
                .noPermissions(new Checker.Action1() {
                    @Override
                    public void call(String[] permissions) {
                        ActivityCompat.requestPermissions(BaseActivity.this, permissions, 1);
                    }
                })
                .check();
    }


    public void acceptPermisstion(String[] hasPermissions, String[] noPermissions) {
    }


}
