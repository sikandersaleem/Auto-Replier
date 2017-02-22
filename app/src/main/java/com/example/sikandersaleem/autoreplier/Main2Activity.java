package com.example.sikandersaleem.autoreplier;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

Switch blk;
    EditText et,mssg;
    Button set,setmsg;
    String blknmbr,msssg;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        blk=(Switch)findViewById(R.id.switch1);
        set=(Button)findViewById(R.id.button);
        setmsg=(Button)findViewById(R.id.button3);
        et=(EditText)findViewById(R.id.editText);
        mssg=(EditText)findViewById(R.id.editText2);
        blk.setClickable(true);

        set.setEnabled(false);
        blk.setChecked(false);
        et.setEnabled(false);
        mssg.setEnabled(false);

        set.setOnClickListener(this);
        setmsg.setOnClickListener(this);

        blk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if (isChecked) {
                    et.setEnabled(true);
                    set.setEnabled(true);
                   mssg.setEnabled(true);
                } else {
                    et.setEnabled(false);
                    set.setEnabled(false);
                    mssg.setEnabled(false);
                }
            }
        });
    }

    public void onClick(View view) {
        if (view.getId()==R.id.button)
        {
           blknmbr=et.getText().toString();
            msssg=mssg.getText().toString();
            Toast.makeText(getApplicationContext(),"number saved",Toast.LENGTH_SHORT).show();
        }
        else if (view.getId()==R.id.button3)
        {
            et.setText("");
            mssg.setText("");
            Intent ii= new Intent(Main2Activity.this, Main22Activity.class);
            ii.putExtra("nmbr", blknmbr);
            ii.putExtra("message",msssg);
            startActivity(ii);
            finish();
        }
    }
}
