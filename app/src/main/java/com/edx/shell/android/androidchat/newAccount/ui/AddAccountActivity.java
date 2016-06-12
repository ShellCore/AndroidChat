package com.edx.shell.android.androidchat.newAccount.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.edx.shell.android.androidchat.R;
import com.edx.shell.android.androidchat.contactList.ui.ContactListActivity;
import com.edx.shell.android.androidchat.login.ui.LoginActivity;
import com.edx.shell.android.androidchat.newAccount.AddAccountPresenter;
import com.edx.shell.android.androidchat.newAccount.AddAccountPresenterImpl;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddAccountActivity extends AppCompatActivity implements AddAccountView {

    // Controladores
    private AddAccountPresenter presenter;

    // Components
    @Bind(R.id.rel_main_container)
    RelativeLayout relMainContainer;
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
    @Bind(R.id.btn_cancel)
    Button btnCancel;
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
        setInputs(true);
    }

    @Override
    public void disableInputs() {
        setInputs(false);
    }

    @Override
    public void showProgress() {
        prgBarAdd.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        prgBarAdd.setVisibility(View.GONE);
    }

    @OnClick(R.id.btn_create)
    public void handleSignup() {
        presenter.registerNewUser(edtEmail.getText().toString(),
                edtPass.getText().toString());
    }

    @OnClick(R.id.btn_cancel)
    public void handleCancel() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void newUserSucess() {
        Snackbar.make(relMainContainer, R.string.login_notice_signup, Snackbar.LENGTH_SHORT)
                .show();
    }

    @Override
    public void newUserError(String error) {
        edtPass.setText("");
        String msgError = String.format(getString(R.string.login_error_signup), error);
        tilPass.setError(msgError);
    }

    @Override
    public void navigateToMainScreen() {
        startActivity(new Intent(this, ContactListActivity.class));
    }

    @Override
    public void loginError(String error) {
        edtPass.setText("");
        String msgError = String.format(getString(R.string.login_error_signin), error);
        tilPass.setError(msgError);
    }

    private void setInputs(boolean enabled) {
        edtEmail.setEnabled(enabled);
        edtPass.setEnabled(enabled);
    }
}
