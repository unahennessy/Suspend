package unahhennessy.com.suspend.receiver;
/**
 * Created by unahe_000 on 21/05/2015 ${PACKAGE_NAME} Suspend.
 *
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class WhatsAppBroadcastReceiver extends BroadcastReceiver {
    // a work in progress -
@Override
public void onReceive(Context context, Intent i)
{
Toast.makeText(context,
"Received broadcast in WhatsAppReceiver; " +
" value received: " + i.getStringExtra("key"),
Toast.LENGTH_LONG).show();
}
}



