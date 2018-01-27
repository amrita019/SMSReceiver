package com.example.amrita.smsreceiver2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class MainActivity extends AppCompatActivity {

    smsboardcastreceiver object=new smsboardcastreceiver();
    String message=object.smsBody;
    String sender=object.smsSender;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EventBus.getDefault().register(this);
        textView= (TextView) findViewById(R.id.textvieww);
        textView.setText("The text will be displayed here");
       // textView.setText(sender);
        textView.append("\n"+message);
    }

    @Subscribe
    public void onEvent(CustomMessageEvent event){
        textView.setText(event.getCustomMessage());

    }
}
