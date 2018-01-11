package com.firebase.rananashit.assignment_4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    Button startService, stopService;
    TextView number_of_count;
    EditText counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService = findViewById(R.id.startService);
        stopService = findViewById(R.id.stopService);
        number_of_count = findViewById(R.id.data);
        counter = findViewById(R.id.counter);

        /*--------------------This will start service------------------*/
        startService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyIntentService.class);
                intent.putExtra("Data", counter.getText().toString());
                MainActivity.this.startService(intent);
            }
        });

        /*--------------------This will stop service------------------*/
        stopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(MainActivity.this, MyIntentService.class));
            }
        });

    }

    /*-----------------Registering EventBus---------------------*/
    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    /*-----------------Unregistering EventBus---------------------*/
    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    /*-----------------Method for receiving data from EventBus and setting in view(TextView)---------------------*/
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void data(Data data) {
        Log.d(TAG, "data: " + String.valueOf(data.getI()));
        number_of_count.setText(String.valueOf(data.getI()) + " %");
    }

}
