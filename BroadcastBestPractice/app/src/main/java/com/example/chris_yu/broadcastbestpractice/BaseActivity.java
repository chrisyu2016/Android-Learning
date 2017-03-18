package com.example.chris_yu.broadcastbestpractice;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Chris-yu on 2017/3/18.
 */

public class BaseActivity extends AppCompatActivity{

    private ForceOfflineReceiver receiver;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("shou 2222");
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction("com.example.chris_yu.broadcastbestpractice.FORCE_OFFLINE");
        receiver=new ForceOfflineReceiver();
        registerReceiver(receiver,intentFilter);
        System.out.println("shou 3333");
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(receiver!=null){
            unregisterReceiver(receiver);
            receiver=null;
        }
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

     class ForceOfflineReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(final Context context, Intent intent) {
            System.out.println("shou 1111");
            AlertDialog.Builder builder=new AlertDialog.Builder(context);
            builder.setTitle("Warning");
            builder.setMessage("you are forced to be offline, please try to login again.");
            builder.setCancelable(false);

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ActivityCollector.finishAll();
                    Intent intent=new Intent(context,LoginActivity.class);
                    context.startActivity(intent);
                }
            });
            builder.show();

        }
    }
}
