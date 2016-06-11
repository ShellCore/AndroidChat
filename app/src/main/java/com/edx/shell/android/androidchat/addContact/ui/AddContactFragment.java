package com.edx.shell.android.androidchat.addContact.ui;


import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.edx.shell.android.androidchat.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AddContactFragment extends DialogFragment implements AddContactView {

    // Components
    @Bind(R.id.edt_add_email)
    EditText edtAddEmail;
    @Bind(R.id.til_add_email)
    TextInputLayout tilAddEmail;
    @Bind(R.id.prg_bar_add)
    ProgressBar prgBarAdd;

    public AddContactFragment() {
        // Required empty public constructor
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_add_contact, null);
        ButterKnife.bind(this, view);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        AlertDialog dialog = builder.create();
        return dialog;
    }

    @Override
    public void showInput() {
        edtAddEmail.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideInput() {
        edtAddEmail.setVisibility(View.GONE);
    }

    @Override
    public void showProgress() {
        prgBarAdd.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        prgBarAdd.setVisibility(View.GONE);
    }

    @Override
    public void contactAdded() {
        Toast.makeText(getActivity(), R.string.addContact_message_contactAdded, Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    public void contactNotAdded() {
        edtAddEmail.setText("");
        tilAddEmail.setError(getString(R.string.addContact_error_message));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
