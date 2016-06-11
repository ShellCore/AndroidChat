package com.edx.shell.android.androidchat.contactList.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.edx.shell.android.androidchat.R;
import com.edx.shell.android.androidchat.contactList.ContactListPresenter;
import com.edx.shell.android.androidchat.contactList.ContactListPresenterImpl;
import com.edx.shell.android.androidchat.contactList.ui.adapters.ContactListAdapter;
import com.edx.shell.android.androidchat.contactList.ui.adapters.OnItemClickListener;
import com.edx.shell.android.androidchat.entities.User;
import com.edx.shell.android.androidchat.lib.GlideImageLoader;
import com.edx.shell.android.androidchat.lib.ImageLoader;
import com.edx.shell.android.androidchat.login.ui.LoginActivity;

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
        presenter = new ContactListPresenterImpl(this);
        presenter.onCreate();
        setupToolbar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_contactlist, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                presenter.signOff();
                Intent intent = new Intent(this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                        | Intent.FLAG_ACTIVITY_NEW_TASK
                        | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupAdapter() {
        ImageLoader loader = new GlideImageLoader(this.getApplicationContext());
        adapter = new ContactListAdapter(new ArrayList<User>(), loader, this);
    }

    private void setupRecyclerView() {
        recContacts.setLayoutManager(new LinearLayoutManager(this));
        recContacts.setAdapter(adapter);
    }

    private void setupToolbar() {
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
        adapter.add(user);
    }

    @Override
    public void onContactChanged(User user) {
        adapter.update(user);
    }

    @Override
    public void onContactRemoved(User user) {
        adapter.remove(user);
    }

    @Override
    public void onItemClick(User user) {
        Toast.makeText(this, user.getEmail(), Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    public void onItemLongClick(User user) {
        presenter.removeContact(user.getEmail());
    }
}
