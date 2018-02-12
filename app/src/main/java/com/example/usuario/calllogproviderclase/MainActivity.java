package com.example.usuario.calllogproviderclase;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class MainActivity extends ListActivity implements CallLogContrat.View {

    private CallLogPresenter presenter;
    private CallLogAdapter adapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adapter=new CallLogAdapter(this);
        listView=findViewById(android.R.id.list);
        listView.setAdapter(adapter);
        presenter= new CallLogPresenter(this);
        presenter.getCallLogs();
    }


    @Override
    public void swapCursor(Cursor cursor) {
        adapter.swapCursor(cursor);
    }

    @Override
    public Context getContext() {
        return this;
    }
}
