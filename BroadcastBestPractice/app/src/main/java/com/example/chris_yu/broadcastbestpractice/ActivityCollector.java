package com.example.chris_yu.broadcastbestpractice;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chris-yu on 2017/3/18.
 */

public class ActivityCollector {
    public static List<Activity> activities=new ArrayList<>();
    public static void addActivity(Activity activity){
        activities.add(activity);
    }
    public static void removeActivity(Activity activity){
        activities.remove(activity);
    }

    public static void finishAll(){
        for (Activity activity : activities){
            if (!activity.isFinishing()){
                activity.finish();
            }
        }
    }
}
