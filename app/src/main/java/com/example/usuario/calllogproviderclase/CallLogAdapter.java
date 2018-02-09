package com.example.usuario.calllogproviderclase;

import android.content.Context;
import android.database.Cursor;
import android.provider.CallLog;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by usuario on 9/02/18.
 */

class CallLogAdapter extends CursorAdapter{

    private class CallLogHolder{

    private TextView txvNumber,txvDate,txvDuration,txvType;

    }

    public CallLogAdapter(Context context) {
        super(context, null, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view=LayoutInflater.from(context).inflate(R.layout.item_llamada,parent,false);
        CallLogHolder callLogHolder= new CallLogHolder();
        callLogHolder.txvNumber=view.findViewById(R.id.txvNumber);
        callLogHolder.txvDate= view.findViewById(R.id.txvDate);
        callLogHolder.txvDuration=view.findViewById(R.id.txvDuration);
        callLogHolder.txvType=view.findViewById(R.id.txtType);
        view.setTag(callLogHolder);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
       CallLogHolder callLogHolder= (CallLogHolder) view.getTag();
       callLogHolder.txvNumber.setText(cursor.getString(1));

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Long.parseLong(cursor.getString(2)));

        int mYear = calendar.get(Calendar.YEAR);
        int mMonth = calendar.get(Calendar.MONTH);
        int mDay = calendar.get(Calendar.DAY_OF_MONTH);
        int hora =calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        int seconds= calendar.get(Calendar.SECOND);
       callLogHolder.txvDate.setText(mDay+"/"+ mMonth +"/"+mYear+"    "+hora+":"+minute+":"+seconds);
       callLogHolder.txvDuration.setText(cursor.getString(3)+" seconds");
       callLogHolder.txvType.setText(getCallTypeText(Integer.parseInt(cursor.getString(4))));
    }
    public String getCallTypeText(int callType) {
        switch (callType) {
            case CallLog.Calls.INCOMING_TYPE:
                return "Llamada entrante";

            case CallLog.Calls.OUTGOING_TYPE:
                return "Llamada realizada";

            case CallLog.Calls.MISSED_TYPE:
                return "Llamada perdida";



            default:
                return "No se que tipo de llamada es esta";
        }
    }
}
