package com.example.amrita.smsreceiver2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.sip.SipAudioCall;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;

public class smsboardcastreceiver extends BroadcastReceiver{

    public static final String TAG = "SmsBroadcastReceiver";


    CustomMessageEvent Cevent= new CustomMessageEvent();
    public String smsSender = "";
    public String smsBody = "";

//    public final String serviceProviderNumber;
//    public final String serviceProviderSmsCondition;

    //   public Listener listener;
//
//    public smsboardcastreceiver(String serviceProviderNumber, String serviceProviderSmsCondition) {
//        this.serviceProviderNumber = serviceProviderNumber;
//        this.serviceProviderSmsCondition = serviceProviderSmsCondition;
//    }



    public void onReceive(Context context, Intent intent) {
        Log.d("Receiver","onReceive() called");
        if (intent.getAction().equals(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)) {

            Bundle smsBundle = intent.getExtras();
            if (smsBundle != null) {
                Object[] pdus = (Object[]) smsBundle.get("pdus");
                if (pdus == null) {
                    // Display some error to the user
                    Log.e(TAG, "SmsBundle had no pdus key");
                    return;
                }
                SmsMessage[] messages = new SmsMessage[pdus.length];
                for (int i = 0; i < messages.length; i++) {
                    messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                    smsBody += messages[i].getMessageBody();
                }
                smsSender = messages[0].getOriginatingAddress();
                Log.d("Receiver",smsSender);
                Log.d("Message",smsBody);


                Cevent.setCustomMessage(smsBody);
                EventBus.getDefault().post(Cevent);


            }




//            if (smsSender.equals(serviceProviderNumber) && smsBody.startsWith(serviceProviderSmsCondition)) {
//                if (listener != null) {
//
//                    listener.onTextReceived(smsBody);
//                }
//            }
//        }
//    }
//
//    void setListener(Listener listener) {
//        this.listener = listener;
//    }
//
//    interface Listener {
//        void onTextReceived(String text);
//    }
        }

    }
}
