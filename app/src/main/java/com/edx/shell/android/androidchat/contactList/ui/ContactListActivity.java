package com.edx.shell.android.androidchat.contactList.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.edx.shell.android.androidchat.R;
import com.edx.shell.android.androidchat.contactList.ContactListPresenter;
import com.edx.shell.android.androidchat.entities.User;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ContactListActivity extends AppCompatActivity implements ContactListView {

    // Servicios
    private ContactListPresenter presenter;

    // Componentes
    @Bind(R.id.tb_contact_list)
    Toolbar tbContactList;
    @Bind(R.id.rec_contacts)
    RecyclerView recContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        ButterKnife.bind(this);
        presenter.onCreate();

        tbContactList.setTitle(presenter.getCurrentUserEmail());
        setSupportActionBar(tbContactList);
    }

    @Override
    protected void onPause() {
        presenter.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @OnClick(R.id.fab)
    public void addContact() {

    }

    @Override
    public void onContactAdded(User user) {

    }

    @Override
    public void onContactChanged(User user) {

    }

    @Override
    public void onContactRemoved(User user) {

    }
}
