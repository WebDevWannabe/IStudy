package com.app.jl.istudy.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.jl.istudy.R;

/**
 * Created by JL on 16/02/2017.
 */

public class AboutTabFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_about_tab, container, false);
        //try to compare it with (R.layout.fragment_about_tab, null)

        //Profile pictures, then the info.. think twice code once..
    }
}
