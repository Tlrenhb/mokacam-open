package com.aee.mokacam.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.aee.mokacam.MokacamApp;

/**
 * Common base activity. Replacement of the original com.aee.zone.activity.BaseActivity
 * (FragmentActivity + Handler + activity tracking).
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MokacamApp.get().registerActivity(this);
    }

    @Override
    protected void onDestroy() {
        MokacamApp.get().unregisterActivity(this);
        super.onDestroy();
    }

    protected Context ctx() {
        return this;
    }

    @SuppressWarnings("unchecked")
    protected <T extends View> T find(int id) {
        return (T) findViewById(id);
    }
}
