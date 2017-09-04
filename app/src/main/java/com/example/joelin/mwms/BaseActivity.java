package com.example.joelin.mwms;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public abstract class BaseActivity extends Activity implements View.OnClickListener, View.OnKeyListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourceId());

        initView();
        initEvent();
        initMember();
    }

    protected abstract int getLayoutResourceId();

    protected abstract void initView();

    protected abstract void initEvent();

    protected abstract void initMember();
}
