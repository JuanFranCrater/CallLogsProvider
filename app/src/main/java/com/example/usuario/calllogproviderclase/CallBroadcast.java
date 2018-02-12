package com.example.usuario.calllogproviderclase;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.telephony.TelephonyManager;

public class CallBroadcast extends BroadcastReceiver {

    private static final String TAG = "callbroadcast";
    private static final int CALLNOTIFICATION =1;

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        //1-Recoger datos
        if(bundle!=null)
        {
            String state = bundle.getString(TelephonyManager.EXTRA_STATE);
            if(state.equals(TelephonyManager.CALL_STATE_RINGING))
            {
                //1.1 Recoger el  número de teléfono
                String number = bundle.getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
                //2. ¿A quién le pasamos la imformación
                Intent newIntent = new Intent(context,CallInformation.class);
                newIntent.putExtra("number",number);
                newIntent.putExtra("idNotificacion",CALLNOTIFICATION);
                PendingIntent pendingIntent =PendingIntent.getActivity(context,CALLNOTIFICATION,newIntent,0);
                //3.Crear la notificación
                NotificationCompat.Builder builder = new NotificationCompat.Builder(context,"Inventory");
                builder.setContentTitle("CallBroadcast");
                builder.setSmallIcon(android.R.drawable.sym_call_incoming);
                builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(),android.R.drawable.sym_call_incoming));//Es necesario poner los dos iconos
                builder.setContentIntent(pendingIntent);
                NotificationManager nm= (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                nm.notify(CALLNOTIFICATION,builder.build());
            }
        }
    }
}
