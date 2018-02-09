package com.example.usuario.calllogproviderclase;


import android.app.Activity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.telecom.Call;

/**
 * Created by usuario on 9/02/18.
 */

public class CallLogPresenter implements LoaderManager.LoaderCallbacks<Cursor> ,CallLogContrat.Presenter{

    private CallLogContrat.View view;
    private final static int CALLLOG = 0;

    public CallLogPresenter(CallLogContrat.View view) {
        this.view = view;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        CursorLoader cursorLoader = null;

        switch (id)
       {
           case CALLLOG:
               String stOrder= CallLog.Calls.DATE+" DESC";
               String[] projection = {CallLog.Calls._ID,CallLog.Calls.NUMBER,CallLog.Calls.DATE,CallLog.Calls.DURATION,CallLog.Calls.TYPE};//numero - fecha - duracion llamada - tipo de llamada
               cursorLoader= new CursorLoader(view.getContext(), CallLog.Calls.CONTENT_URI,
                        projection,
                       null,
                       null,
                       stOrder);
               break;
       }
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        view.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        view.swapCursor(null);
    }

    @Override
    public void getCallLogs() {
        ((Activity)view.getContext()).getLoaderManager().restartLoader(CALLLOG,null,  this);
    }
}
