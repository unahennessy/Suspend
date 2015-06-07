package unahhennessy.com.suspend.receiver;
/**
 * Created by unahe_000 on 21/05/2015 ${PACKAGE_NAME} Suspend.
 *
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class WhatsAppBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "WhatsAppBrdcstReceiver";
    // a work in progress -
@Override
public void onReceive(Context context, Intent i)
{
    this.log("entered onReceive() within WhatsAppBroadcastReceiver");
Toast.makeText(context,
"Received broadcast in WhatsAppReceiver; " +
" value received: " + i.getStringExtra("key"),
Toast.LENGTH_LONG).show();
}

    private void log(String msg)
    {
        try {
            Thread.sleep(500);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i(TAG, msg);

    }




}



