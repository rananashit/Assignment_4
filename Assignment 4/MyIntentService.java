package com.firebase.rananashit.assignment_4;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;

public class MyIntentService extends IntentService {

    private static final String TAG = "MyIntentService";
    private boolean running = true;

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        //Getting data from intent
        int data = Integer.parseInt(intent.getExtras().get("Data").toString());
        int i = 0;
        try {
            while (running && (i < data)) {

                //Posting data to MainActivity
                EventBus.getDefault().post(new Data(i));
                Log.d(TAG, "onHandleIntent: " + i);

                //Delay of 1 second
                Thread.sleep(1000);
                i++;

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        // This will change running to flase for stopping our thread in onStartCommand Method
        running = false;
        super.onDestroy();
    }
}
