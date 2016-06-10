package com.edx.shell.android.androidchat.contactList.ui.adapters;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.edx.shell.android.androidchat.R;
import com.edx.shell.android.androidchat.entities.User;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ViewHolder> {

    // Variables
    private List<User> contactList;
    private ImageLoading imageLoader;
    private OnItemClickListener listener;

    public ContactListAdapter(List<User> contactList, ImageLoading imageLoader, OnItemClickListener listener) {
        this.contactList = contactList;
        this.imageLoader = imageLoader;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_contact, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User user = contactList.get(position);
        holder.setClickListener(user, listener);

        String email = user.getEmail();
        boolean online = user.isOnline();
        String status = user.isOnline() ? "onilne" : "offline";
        int color = user.isOnline() ? Color.GREEN : Color.RED;

        holder.txtUser.setText(email);
        holder.txtStatus.setText(status);
        holder.txtStatus.setTextColor(color);

        imageLoader.load(holder.imgAvatar, "");
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // Variables
        private View view;

        // Componentes
        @Bind(R.id.img_avatar)
        CircleImageView imgAvatar;
        @Bind(R.id.txt_user)
        TextView txtUser;
        @Bind(R.id.txt_status)
        TextView txtStatus;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.view = itemView;
        }

        private void setClickListener(final User user, final OnItemClickListener listener) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(user);
                }
            });

            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    listener.onItemLongClick(user);
                    return false;
                }
            });
        }
    }
}
