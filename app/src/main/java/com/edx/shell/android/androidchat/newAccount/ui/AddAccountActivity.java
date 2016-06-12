package com.edx.shell.android.androidchat.newAccount.ui;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.edx.shell.android.androidchat.R;
import com.edx.shell.android.androidchat.newAccount.AddAccountPresenter;
import com.edx.shell.android.androidchat.newAccount.AddAccountPresenterImpl;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AddAccountActivity extends AppCompatActivity implements AddAccountView {

    // Controladores
    private AddAccountPresenter presenter;

    // Components
    @Bind(R.id.edt_email)
    EditText edtEmail;
    @Bind(R.id.til_email)
    TextInputLayout tilEmail;
    @Bind(R.id.edt_pass)
    EditText edtPass;
    @Bind(R.id.til_pass)
    TextInputLayout tilPass;
    @Bind(R.id.btn_create)
    Button btnCreate;
    @Bind(R.id.btn_ccancel)
    Button btnCcancel;
    @Bind(R.id.prg_bar_add)
    ProgressBar prgBarAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account);
        ButterKnife.bind(this);

        presenter = new AddAccountPresenterImpl(this);
        presenter.onCreate();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void enableInputs() {

    }

    @Override
    public void disableInputs() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void newUserSucess() {

    }

    @Override
    public void newUserError(String error) {

    }

    @Override
    public void navigateToMainScreen() {

    }

    @Override
    public void loginError(String errorMessage) {

    }
}
