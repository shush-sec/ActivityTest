package com.example.broadcastbestpractice;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shush on 2017/5/15.
 */

public class ActivityConllector {
    public static List<Activity> activitys = new ArrayList<>();

    public static void addActivity(Activity activity) {
        activitys.add(activity);
    }

    public static  void removeActivity(Activity activity){
        activitys.remove(activity);
    }

    public static void finishAll(){
        for (Activity activity : activitys){
            activity.finish();
        }
    }
}
