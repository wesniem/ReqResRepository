package com.wesniemarcelin.reqresproject.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wesniemarcelin.reqresproject.R;
import com.wesniemarcelin.reqresproject.model.User;

import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserListViewHolder> {
    private List<User> userList;

    public UserListAdapter(List<User> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public UserListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_list_item, viewGroup, false);
        return new UserListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserListViewHolder userListViewHolder, int i) {
        User user = userList.get(i);
        userListViewHolder.bind(user);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    static final class UserListViewHolder extends RecyclerView.ViewHolder {
        private ImageView userImageView;
        private TextView userFirstName;
        private TextView userLastName;

        public UserListViewHolder(@NonNull View itemView) {
            super(itemView);
            userImageView = itemView.findViewById(R.id.user_avatar_image);
            userFirstName = itemView.findViewById(R.id.user_first_name);
            userLastName = itemView.findViewById(R.id.user_last_name);
        }

        public void bind(User user) {
            Glide.with(itemView.getContext())
                    .load(user.getAvatar())
                    .into(userImageView);
            userFirstName.setText(user.getFirstName());
            userLastName.setText(user.getLastName());
        }
    }
}
