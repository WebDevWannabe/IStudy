package com.app.jl.istudy.model;

import com.app.jl.istudy.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JL on 21/02/2017.
 */

public class ListData {

    private static String[] friendName = {"Steve Jobs"};
    private static int[] friendPicture = {R.drawable.steven_jobs_9354805_2_402};

    //needs to be static so that the friends tab fragment can access this
    public static List<Model> getListData() {
        List<Model> listData = new ArrayList<>();

        for(int x=0; x<9; x++) {

            for(int i=0; i<friendName.length && i<friendPicture.length; i++) {
                Model Model = new Model();
                Model.setFriendName(friendName[i]);
                Model.setFriendPicture(friendPicture[i]);
                listData.add(Model);
            }
        }
        return listData;
    }
}
