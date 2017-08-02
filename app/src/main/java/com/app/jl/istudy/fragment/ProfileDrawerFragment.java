package com.app.jl.istudy.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.jl.istudy.R;

/**
 * Created by JL on 16/02/2017.
 */

public class ProfileDrawerFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private static int number_of_tabs = 3;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //inflate view then setup tablayout and viewpager
        // try to change null to the name of the viewgroup in parameter
        View view = inflater.inflate(R.layout.fragment_profile_drawer, null);
        tabLayout = (TabLayout)view.findViewById(R.id.tabLayout);
        viewPager = (ViewPager)view.findViewById(R.id.viewPager);

        //set an adapter for the viewpager then use the setupWithViewPager in tablayout
        viewPager.setAdapter(new MyAdapter(getChildFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }

    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        //return fragment with respect to its position
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new AboutTabFragment();
                case 1:
                    return new FriendsTabFragment();
                case 2:
                    return new NotesTabFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return number_of_tabs;
        }

        //this method returns the title of the tab according to its position

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "About";
                case 1:
                    return "Friends";
                case 2:
                    return "Notes";
            }
            return null;
        }
    }
}
