package com.example.sikandersaleem.autoreplier;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.telephony.gsm.SmsManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {

    String ns = Context.NOTIFICATION_SERVICE;
    private static final int HELLO_ID = 1;
    boolean ring = false;
    boolean callReceived = false;

    String message,inmsg;
    Button strt,set;
    String onn="Service is currently ON";
    String offf="Service is currently OFF";
    String c;
    String on="Start";
    String off="Stop";
    String nmbr,setmsg;
    AudioManager audiomanage;
    TextView tv;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        Bundle bundle = getIntent().getExtras();

        inmsg = bundle.getString("message");
        nmbr=bundle.getString("nmber");
        setmsg=bundle.getString("msg");

        audiomanage = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        strt = (Button) findViewById(R.id.button2);
        set = (Button) findViewById(R.id.button4);
        strt.setOnClickListener(this);
        set.setOnClickListener(this);
        tv = (TextView) findViewById(R.id.textView5);

    }

    protected  void onDestroy()
    {

        finish();
        super.onDestroy();

    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitByBackKey();

            //moveTaskToBack(false);

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    protected void exitByBackKey() {

        AlertDialog alertbox = new AlertDialog.Builder(this)
                .setMessage("Do you want to exit application?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    // do something when the button is clicked
                    public void onClick(DialogInterface arg0, int arg1) {

                        onDestroy();
                        //close();


                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {

                    // do something when the button is clicked
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                })
                .show();

    }

    public void onClick(View v) {

        if (v.getId()==R.id.button2) {
            c = strt.getText().toString();
            if (inmsg.equals("")) {
                Toast.makeText(getApplicationContext(), "Message is not set", Toast.LENGTH_SHORT).show();
            } else {
                if (on.equals(c)) {
                    strt.setTextColor(Color.WHITE);
                    strt.setText("Stop");

                    tv.setText(onn);
                  //  notifier(onn);
                    TelephonyManager TelephonyMgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
                    TelephonyMgr.listen(new TeleListener(),
                            PhoneStateListener.LISTEN_CALL_STATE);

                    audiomanage.setRingerMode(AudioManager.RINGER_MODE_SILENT);

                } else {
                    strt.setTextColor(Color.BLACK);
                    strt.setText("Start");

                    audiomanage.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                    tv.setText(offf);
                    onStop();
                }
            }
        }
        else if(v.getId()==R.id.button4)
        {

            Intent ai=new Intent(MainActivity.this,Main2Activity.class);
            startActivity(ai);
            finish();
        }
    }

    public void onStop() {

        super.onStop();
    }


    class TeleListener extends PhoneStateListener {

        public void onCallStateChanged(int state, String incomingNumber) {

            super.onCallStateChanged(state, incomingNumber);

            switch (state) {

                case TelephonyManager.CALL_STATE_IDLE:

                    if (ring == true && callReceived == false) {

                        Toast.makeText(getApplicationContext(),
                                "Missed call from : " + incomingNumber,
                                Toast.LENGTH_SHORT).show();
                            sendsms(incomingNumber);
                    }
                    break;

                case TelephonyManager.CALL_STATE_OFFHOOK:

                    callReceived = true;

                    break;

                case TelephonyManager.CALL_STATE_RINGING:
                    ring = true;

                    Toast.makeText(getApplicationContext(), incomingNumber,
                            Toast.LENGTH_LONG).show();

                    break;

                default:
                    break;
            }
        }

        void sendsms(String num) {
            if (num.equals("0" + nmbr) || num.equals("+92" + nmbr)) {
            try {
                    SmsManager manager = SmsManager.getDefault();
                    manager.sendTextMessage(num, null, setmsg, null, null);
                    Toast.makeText(getBaseContext(), "sending...", Toast.LENGTH_SHORT).show();
                    Thread.sleep(20);
                    Toast.makeText(getBaseContext(), "sent", Toast.LENGTH_SHORT).show();

                    }
                   catch(InterruptedException e){
                    Toast.makeText(getBaseContext(), "Error", Toast.LENGTH_LONG).show();
                }
            }
            else
                try {
                    SmsManager manager = SmsManager.getDefault();
                    manager.sendTextMessage(num, null, inmsg, null, null);
                    Toast.makeText(getBaseContext(), "sending...", Toast.LENGTH_SHORT).show();
                    Thread.sleep(20);
                    Toast.makeText(getBaseContext(), "sent", Toast.LENGTH_SHORT).show();

                }
                catch(InterruptedException e){
                    Toast.makeText(getBaseContext(), "Error", Toast.LENGTH_LONG).show();
                }
            }
        }

    }




