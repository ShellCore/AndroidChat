package com.edx.shell.android.androidchat.contactList.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.edx.shell.android.androidchat.R;
import com.edx.shell.android.androidchat.contactList.ContactListPresenter;
import com.edx.shell.android.androidchat.contactList.ui.adapters.ContactListAdapter;
import com.edx.shell.android.androidchat.contactList.ui.adapters.OnItemClickListener;
import com.edx.shell.android.androidchat.entities.User;
import com.edx.shell.android.androidchat.lib.GlideImageLoader;
import com.edx.shell.android.androidchat.lib.ImageLoader;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ContactListActivity extends AppCompatActivity implements ContactListView, OnItemClickListener {

    // Servicios
    private ContactListPresenter presenter;
    private ContactListAdapter adapter;

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

        setupAdapter();
        setupRecyclerView();
        //presenter.onCreate();
        setupToolbar();
    }

    private void setupAdapter() {
        ImageLoader loader = new GlideImageLoader(this.getApplicationContext());

        User user = new User();
        user.setOnline(false);
        user.setEmail("mogc@praxis.com.mx");


//        adapter = new ContactListAdapter(new ArrayList<User>(), loader, this);
        adapter = new ContactListAdapter(Arrays.asList(new User[] {user}), loader, this);
    }

    private void setupRecyclerView() {
        recContacts.setLayoutManager(new LinearLayoutManager(this));
        recContacts.setAdapter(adapter);
    }

    private void setupToolbar() {
        //tbContactList.setTitle(presenter.getCurrentUserEmail());
        setSupportActionBar(tbContactList);
    }
/*

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
*/

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

    @Override
    public void onItemClick(User user) {

    }

    @Override
    public void onItemLongClick(User user) {

    }
}
