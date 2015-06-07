package unahhennessy.com.suspend.listener;
/**
 * Created by unahe_000 on 1/06/2015.
 */
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import unahhennessy.com.suspend.factors.FactorsInThisApp;
import unahhennessy.com.suspend.other.NotificationStopOtherApps;
import unahhennessy.com.suspend.R;
/**
 *Listener for whatsapp
 */
public class WhatsAppListener  extends BroadcastReceiver
{
   
  private SharedPreferences preferences;
  private static final String TAG = "WhatsAppListener";
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    // get SUSPEND_PREF & check suspend_on is on and if whatsapp is enabled
      this.log("entered onReceive() within WhatsAppListener.java");
    this.preferences = paramContext.getSharedPreferences(FactorsInThisApp.mSUSPEND_PREF, 0);
    Bundle localBundle = null;
    if ((this.preferences.getBoolean("is_suspend_on", false)) && (this.preferences.getBoolean("is_whatsapp_enabled", false)) && (paramIntent.getAction().equals("android.provider.Telephony.WAP_PUSH_RECEIVED")))
    {
      localBundle = paramIntent.getExtras();
      if (localBundle == null) {}
    }
    try
    {
      System.out.println("WhatsApp ");
      if (paramIntent.getType().trim().equalsIgnoreCase("application/vnd.wap.whatsapp-message"))
      {
        String mString1 = new String(localBundle.getByteArray("data"));
        int i = mString1.indexOf("/TYPE");
        if ((i > 0) && (i - 15 > 0))
        {
          String mString2 = mString1.substring(i - 15, i);
          int j = mString2.indexOf("+");
          if (j > 0)
          {
            String mString3 = mString2.substring(j);
            System.out.println("Mobile Number: " + mString3);
            String mString4 = this.preferences.getString("custom_msg", paramContext.getResources().getString(R.string.default_message_to_reply));
            if ((mString4 != null) && (mString4.trim().length() == 0)) {
              mString4 = paramContext.getResources().getString(R.string.default_message_to_reply);
            }
            String mString5 = "Suspend Reply: " + mString4;
            if (this.preferences.getBoolean("is_whatsapp_enabled", false)) {
              NotificationStopOtherApps.sendSms(paramContext, mString3, mString5);
            }
          }
        }
      }
      return;
    }
    catch (Exception localException)
    {
      NotificationStopOtherApps.writeErrorLog(paramContext, localException.getMessage());
    }
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
