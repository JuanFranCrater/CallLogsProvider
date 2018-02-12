package com.example.usuario.calllogproviderclase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CallInformation extends AppCompatActivity {

    TextView textView;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_information);
        btn=findViewById(R.id.btn);
        textView=findViewById(R.id.textView);
       sendIntent(btn);
        try {
            String number = getIntent().getExtras().getString("number");
            Toast.makeText(this, number, Toast.LENGTH_LONG).show();
            textView.setText(number);
        }catch (Exception e)
        {

        }
    }

    public void sendIntent(View view)
    {
        Intent intent =new Intent("com.example.usuario.callogproviderclase.intent");
        Bundle bundle = new Bundle();
        bundle.putString(TelephonyManager.EXTRA_STATE,"RINGING");
        bundle.putString(TelephonyManager.EXTRA_INCOMING_NUMBER,"666666666");
        intent.putExtras(bundle);
        sendBroadcast(intent);
    }
}
