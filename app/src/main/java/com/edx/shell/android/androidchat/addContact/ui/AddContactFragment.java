package com.edx.shell.android.androidchat.addContact.ui;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.preference.DialogPreference;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.edx.shell.android.androidchat.R;
import com.edx.shell.android.androidchat.addContact.AddContactPresenter;
import com.edx.shell.android.androidchat.addContact.AddContactPresenterImpl;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AddContactFragment extends DialogFragment implements AddContactView, DialogInterface.OnShowListener {

    // Servicios
    private AddContactPresenter presenter;


    // Componentes
    @Bind(R.id.edt_add_email)
    EditText edtAddEmail;
    @Bind(R.id.til_add_email)
    TextInputLayout tilAddEmail;
    @Bind(R.id.prg_bar_add)
    ProgressBar prgBarAdd;

    public AddContactFragment() {
        presenter = new AddContactPresenterImpl(this);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setTitle(getString(R.string.addContact_message_title))
                .setPositiveButton(R.string.addContact_message_add, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setNegativeButton(R.string.addContact_message_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_add_contact, null);
        ButterKnife.bind(this, view);
        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.setOnShowListener(this);
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
        dismiss();
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

    @Override
    public void onShow(DialogInterface dialogInterface) {
        final AlertDialog dialog = (AlertDialog) getDialog();
        if (dialog != null) {
            Button positiveButton = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
            Button negativeButton = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);

            positiveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    presenter.addContact(edtAddEmail.getText().toString());
                }
            });

            negativeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });
        }
        presenter.onShow();
    }

    @Override
    public void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }
}
