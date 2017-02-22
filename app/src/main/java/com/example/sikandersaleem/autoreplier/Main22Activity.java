package com.example.sikandersaleem.autoreplier;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Main22Activity extends Activity implements View.OnClickListener {

    Button save;
    String message,dmsg,msssg;
    EditText msg;
    TextView text1;
    String inmbr;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main22);

        Bundle bundle = getIntent().getExtras();
        inmbr = bundle.getString("nmbr");
        msssg=bundle.getString("message");

        msg = (EditText) findViewById(R.id.editText);
        save =(Button) findViewById(R.id.button1);
        text1=(TextView)findViewById(R.id.textView3);
        save.setOnClickListener(this);
    }
    public void add1(View v)
    {
        dmsg="I'm sleeping.";
        msg.setText("");
        msg.setText(dmsg);
    }
    public void add2(View v)
    {
        dmsg="i'm in class.";
        msg.setText("");
        msg.setText(dmsg);
    }
    public void add3(View v)
    {
        dmsg="i'm on funeral.";
        msg.setText("");
        msg.setText(dmsg);
    }
    public void add4(View v)
    {
        dmsg="i'm in meeting.";
        msg.setText("");
        msg.setText(dmsg);
    }
    public void add5(View v)
    {
        dmsg="i'm busy, text you soon.";
        msg.setText("");
        msg.setText(dmsg);
    }
    public void onClick(View view) {

        message=msg.getText().toString();

        if (message.equals("")){
            Toast.makeText(getApplicationContext(),
                    "please enter a message ",
                    Toast.LENGTH_LONG).show();
        }
        else {
            text1.setText(message);
            msg.setText("");
            Intent ii= new Intent(Main22Activity.this,MainActivity.class);
            ii.putExtra("message", message);
            ii.putExtra("nmber",inmbr);
            ii.putExtra("msg",msssg);
            startActivity(ii);
            finish();
        }

    }
}
