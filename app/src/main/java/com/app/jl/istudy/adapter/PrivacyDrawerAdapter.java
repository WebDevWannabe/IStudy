package com.app.jl.istudy.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.app.jl.istudy.R;

/**
 * Created by JL on 23/02/2017.
 */

public class PrivacyDrawerAdapter extends ArrayAdapter<String> {

    private de.hdodenhof.circleimageview.CircleImageView circleImageViewPrivacyItem;
    private TextView textViewPrivacyItem;

    public PrivacyDrawerAdapter(Context context, String[] listPrivacyItems) {
        super(context, R.layout.privacy_drawer_list_view_temp, listPrivacyItems);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View view = layoutInflater.inflate(R.layout.privacy_drawer_list_view_temp, parent, false);

        String privacyItem = getItem(position);
        circleImageViewPrivacyItem = (de.hdodenhof.circleimageview.CircleImageView)view.findViewById(R.id.circleImageViewPrivacyItem);
        textViewPrivacyItem = (TextView)view.findViewById(R.id.textViewPrivacyItem);
        //get the int array of image in privacy drawer fragment
        circleImageViewPrivacyItem.setImageResource(R.drawable.steven_jobs_9354805_2_402);
        textViewPrivacyItem.setText(privacyItem);

        return view;
    }
}
