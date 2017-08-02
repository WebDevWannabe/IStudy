package com.app.jl.istudy.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.jl.istudy.R;
import com.app.jl.istudy.model.Model;

import java.util.List;

/**
 * Created by JL on 21/02/2017.
 */

public class FriendsTabAdapter extends RecyclerView.Adapter<FriendsTabAdapter.Holder> {

    private List<Model> listData;
    private LayoutInflater layoutInflater;

    public FriendsTabAdapter(List<Model> listData, Context c) {
        this.listData = listData;
        this.layoutInflater = layoutInflater.from(c);
    }

    @Override
    public FriendsTabAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.friends_tab_recycler_view_temp, parent, false);
        return new FriendsTabAdapter.Holder(view);
    }

    @Override
    public void onBindViewHolder(FriendsTabAdapter.Holder holder, int position) {
        Model Model = listData.get(position);
        holder.textViewFriendName.setText(Model.getFriendName());
        holder.circleImageViewFriendPicture.setImageResource(Model.getFriendPicture());
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    class Holder extends RecyclerView.ViewHolder{

        private de.hdodenhof.circleimageview.CircleImageView circleImageViewFriendPicture;
        private TextView textViewFriendName;
        private View containerView;

        public Holder(View itemView) {
            super(itemView);

            circleImageViewFriendPicture = (de.hdodenhof.circleimageview.CircleImageView)itemView.findViewById(R.id.circleImageViewFriendPicture);
            textViewFriendName = (TextView)itemView.findViewById(R.id.textViewFriendName);
            containerView = itemView.findViewById(R.id.containerView);
        }
    }
}
