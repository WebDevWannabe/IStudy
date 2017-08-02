package com.app.jl.istudy.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.app.jl.istudy.R;
import com.app.jl.istudy.adapter.PrivacyDrawerAdapter;

/**
 * Created by JL on 21/02/2017.
 */

public class PrivacyDrawerFragment extends Fragment {

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    private String[] listPrivacyItems = {"Change Email Address", "Change Password", "Deactivate Account"};
    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_privacy_drawer, container, false);

        fragmentManager = getActivity().getSupportFragmentManager();

        listView = (ListView) view.findViewById(R.id.listView);

        ListAdapter arrayAdapter = new PrivacyDrawerAdapter(getActivity(), listPrivacyItems);

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String listPrivacyItem = String.valueOf(parent.getItemAtPosition(position));
                if(listPrivacyItem.equals("Change Password")) {
                    selectedItem(new PrivacyDrawerChangePasswordFragment());
                }
            }
        });

        return view;
    }

    private void selectedItem(Fragment fragment) {
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.containerView, fragment, "privacyDrawerFragment")
                .addToBackStack(null);
        fragmentTransaction.commit();
    }
}