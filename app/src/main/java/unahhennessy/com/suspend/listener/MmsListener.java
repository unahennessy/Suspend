package unahhennessy.com.suspend.listener;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import unahhennessy.com.suspend.constants.AppConstants;
import unahhennessy.com.suspend.util.ProjectUtil;
import unahhennessy.com.suspend.R;

public class MmsListener
  extends BroadcastReceiver
{
  private final String DEFAULT_MSG = "Driving so cant reply. I will have a look later when I'm not driving.";
  private final String TAG = "SuspendReply: ";
  private SharedPreferences preferences;
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    this.preferences = paramContext.getSharedPreferences(AppConstants.SUSPEND_PREF, 0);
    Bundle localBundle = null;
    if ((this.preferences.getBoolean("is_suspend_on", false)) && (this.preferences.getBoolean("is_mms_enabled", false)) && (paramIntent.getAction().equals("android.provider.Telephony.WAP_PUSH_RECEIVED")))
    {
      localBundle = paramIntent.getExtras();
      if (localBundle == null) {}
    }
    try
    {
      System.out.println("*********** MMS ***********");
      if (paramIntent.getType().trim().equalsIgnoreCase("application/vnd.wap.mms-message"))
      {
        String str1 = new String(localBundle.getByteArray("data"));
        int i = str1.indexOf("/TYPE");
        if ((i > 0) && (i - 15 > 0))
        {
          String str2 = str1.substring(i - 15, i);
          int j = str2.indexOf("+");
          if (j > 0)
          {
            String str3 = str2.substring(j);
            System.out.println("Mobile Number: " + str3);
            String str4 = this.preferences.getString("custom_msg", paramContext.getResources().getString(R.string.default_message_to_reply));
            if ((str4 != null) && (str4.trim().length() == 0)) {
              str4 = paramContext.getResources().getString(R.string.default_message_to_reply);
            }
            String str5 = "Suspend Reply: " + str4;
            if (this.preferences.getBoolean("is_mms_enabled", false)) {
              ProjectUtil.sendSms(paramContext, str3, str5);
            }
          }
        }
      }
      return;
    }
    catch (Exception localException)
    {
      ProjectUtil.writeErrorLog(paramContext, localException.getMessage());
    }
  }
}
