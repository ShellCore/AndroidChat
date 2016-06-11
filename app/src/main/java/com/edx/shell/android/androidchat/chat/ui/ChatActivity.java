package com.edx.shell.android.androidchat.chat.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.edx.shell.android.androidchat.R;
import com.edx.shell.android.androidchat.chat.ChatPresenter;
import com.edx.shell.android.androidchat.chat.ChatPresenterImpl;
import com.edx.shell.android.androidchat.chat.ui.adapters.ChatAdapter;
import com.edx.shell.android.androidchat.domain.AvatarHelper;
import com.edx.shell.android.androidchat.entities.ChatMessage;
import com.edx.shell.android.androidchat.lib.GlideImageLoader;
import com.edx.shell.android.androidchat.lib.ImageLoader;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class ChatActivity extends AppCompatActivity implements ChatView {

    // Constantes
    public static final String EMAIL_KEY = "email";
    public static final String ONLINE_KEY = "online";

    // Servicios
    private ChatPresenter presenter;
    private ChatAdapter adapter;

    // Componentes
    @Bind(R.id.img_avatar)
    CircleImageView imgAvatar;
    @Bind(R.id.txt_user)
    TextView txtUser;
    @Bind(R.id.txt_status)
    TextView txtStatus;
    @Bind(R.id.rec_chat)
    RecyclerView recChat;
    @Bind(R.id.edt_message)
    EditText edtMessage;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);

        setupAdapter();
        setupRecyclerView();

        presenter = new ChatPresenterImpl(this);
        presenter.onCreate();

        setupToolbar(getIntent());
    }

    private void setupAdapter() {
        adapter = new ChatAdapter(getApplicationContext(), new ArrayList<ChatMessage>());
    }

    private void setupRecyclerView() {
        recChat.setLayoutManager(new LinearLayoutManager(this));
        recChat.setAdapter(adapter);
    }

    private void setupToolbar(Intent intent) {
        String recipient = intent.getStringExtra(EMAIL_KEY);
        presenter.setChatRecipient(recipient);

        boolean online = intent.getBooleanExtra(ONLINE_KEY, false);
        String status = online ? "online" : "offline";
        int color = online ? Color.GREEN : Color.RED;

        txtUser.setText(recipient);
        txtStatus.setText(status);
        txtStatus.setTextColor(color);

        ImageLoader imageLoader = new GlideImageLoader(getApplicationContext());
        imageLoader.load(imgAvatar, AvatarHelper.getAvatarUrl(recipient));

        setSupportActionBar(toolbar);
    }

    @Override
    protected void onResume() {
        presenter.onResume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        presenter.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onMessageReceived(ChatMessage msg) {
        adapter.add(msg);
        recChat.scrollToPosition(adapter.getItemCount() - 1);
    }

    @OnClick(R.id.btn_send)
    public void sendMessage() {
        presenter.sendMessage(edtMessage.getText().toString());
        edtMessage.setText("");
    }
}
