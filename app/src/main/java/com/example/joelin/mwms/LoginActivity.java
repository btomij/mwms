package com.example.joelin.mwms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends BaseActivity {

    Button btn_login;
    EditText txt_user;
    EditText txt_pass;

    @Override
    protected int getLayoutResourceId(){
        return R.layout.activity_login;
    }

    @Override
    protected void initView(){
        btn_login = (Button) findViewById(R.id.btn_login);
        txt_user = (EditText) findViewById(R.id.txt_user);
        txt_pass = (EditText) findViewById(R.id.txt_pass);
    }

    @Override
    protected void initEvent() {

        btn_login.setOnClickListener(this);
        txt_user.setOnKeyListener(this);
        txt_pass.setOnKeyListener(this);
    }

    @Override
    protected void initMember() {

    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_login:
                Login(txt_user.getText().toString(), txt_pass.getText().toString());
                break;
        }

    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN
                && (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_DOWN)) {
            return true;
        }

        if (event.getAction() == KeyEvent.ACTION_UP
                && keyCode == KeyEvent.KEYCODE_ENTER){
            switch (v.getId()){
                case R.id.txt_user:
                    txt_pass.requestFocus();
                    break;
                case R.id.txt_pass:
                    Login(txt_user.getText().toString(), txt_pass.getText().toString());
                    break;
            }

        }
        return false;
    }

    private void Login(String user, String pass){
        if (user.length() == 0){
            txt_user.requestFocus();
            txt_user.selectAll();
            return;
        }

        if (pass.length() == 0){
            txt_pass.requestFocus();
            txt_pass.selectAll();
            return;
        }

        //check user
        Intent intent = new Intent(this, MainMenuActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);

        txt_user.setText("");
        txt_pass.setText("");

        txt_user.requestFocus();
    }
}
