package com.app.jl.istudy.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.jl.istudy.R;
import com.app.jl.istudy.adapter.FriendsTabAdapter;
import com.app.jl.istudy.model.ListData;

/**
 * Created by JL on 16/02/2017.
 */

public class FriendsTabFragment extends Fragment {

    private RecyclerView recyclerView;
    private FriendsTabAdapter friendsTabAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friends_tab, container, false);

        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

        friendsTabAdapter = new FriendsTabAdapter(ListData.getListData(), getActivity().getApplicationContext());
        recyclerView.setAdapter(friendsTabAdapter);

        return view;
    }
}
