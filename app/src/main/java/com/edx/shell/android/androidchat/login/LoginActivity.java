package com.edx.shell.android.androidchat.login;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.edx.shell.android.androidchat.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {
    @Bind(R.id.til_email)
    TextInputLayout tilEmail;
    @Bind(R.id.til_pass)
    TextInputLayout tilPass;
    @Bind(R.id.rel_main_container)
    RelativeLayout relMainContainer;
    @Bind(R.id.btn_signin)
    Button btnSignin;
    @Bind(R.id.btn_signup)
    Button btnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_signin)
    public void onClickBtnSignin() {
        String email = "";
        if (tilEmail.getEditText() != null) {
            email = tilEmail.getEditText().getText().toString().trim();
        }
        Log.d("AndroidChat", email);
    }

    @OnClick(R.id.btn_signin)
    public void onClickBtnSignup() {
    }
}
