package com.example.usuario.calllogproviderclase;

import android.content.Context;
import android.database.Cursor;

/**
 * Created by usuario on 9/02/18.
 */

public interface CallLogContrat {
    interface View{
        void swapCursor(Cursor cursor);

        Context getContext();
    }
    interface Presenter{
        void getCallLogs() ;
    }
}
