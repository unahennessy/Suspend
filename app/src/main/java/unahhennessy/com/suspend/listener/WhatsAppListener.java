package unahhennessy.com.suspend.listener;
/**
 * Created by unahe_000 on 1/06/2015.
 */
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import unahhennessy.com.suspend.factors.FactorsInThisApp;
import unahhennessy.com.suspend.other.NotificationStopOtherApps;
import unahhennessy.com.suspend.R;
/**
 *Listener for whatsapp
 */
public class WhatsAppListener  extends BroadcastReceiver
{
  private final String TAG = "SuspendReply: ";
  private SharedPreferences preferences;
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    // get SUSPEND_PREF & check suspend_on is on and if whatsapp is enabled
    this.preferences = paramContext.getSharedPreferences(FactorsInThisApp.mSUSPEND_PREF, 0);
    Bundle localBundle = null;
    if ((this.preferences.getBoolean("is_suspend_on", false)) && (this.preferences.getBoolean("is_whatsapp_enabled", false)) && (paramIntent.getAction().equals("android.provider.Telephony.WAP_PUSH_RECEIVED")))
    {
      localBundle = paramIntent.getExtras();
      if (localBundle == null) {}
    }
    try
    {
      System.out.println("*********** WhatsApp ***********");
      if (paramIntent.getType().trim().equalsIgnoreCase("application/vnd.wap.whatsapp-message"))
      {
        String string1 = new String(localBundle.getByteArray("data"));
        int i = string1.indexOf("/TYPE");
        if ((i > 0) && (i - 15 > 0))
        {
          String string2 = string1.substring(i - 15, i);
          int j = string2.indexOf("+");
          if (j > 0)
          {
            String string3 = string2.substring(j);
            System.out.println("Mobile Number: " + string3);
            String string4 = this.preferences.getString("custom_msg", paramContext.getResources().getString(R.string.default_message_to_reply));
            if ((string4 != null) && (string4.trim().length() == 0)) {
              string4 = paramContext.getResources().getString(R.string.default_message_to_reply);
            }
            String string5 = "Suspend Reply: " + string4;
            if (this.preferences.getBoolean("is_whatsapp_enabled", false)) {
              NotificationStopOtherApps.sendSms(paramContext, string3, string5);
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
}
