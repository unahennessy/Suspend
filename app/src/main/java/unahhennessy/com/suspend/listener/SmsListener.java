package unahhennessy.com.suspend.listener;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsMessage;

import unahhennessy.com.suspend.R;
import unahhennessy.com.suspend.constants.AppConstants;
import unahhennessy.com.suspend.util.ProjectUtil;

public class SmsListener
  extends BroadcastReceiver
{
  private final String DEFAULT_MSG = "Driving so can't text. Will text when I'm stopped!";
  private final String TAG = "Suspend Reply: ";
  private SharedPreferences preferences;
  
  private boolean checkForDuplicateSms(String paramString1, String paramString2)
  {
    long l1 = System.currentTimeMillis();
    String str1 = this.preferences.getString("last_sms_received_no", "");
    String str2 = this.preferences.getString("last_sms_received_msg", "");
    long l2 = this.preferences.getLong("last_sms_received_time", 0L);
    if (str1.trim().equalsIgnoreCase(paramString1))
    {
      if (str2.trim().equals(paramString2)) {
        return (int)((l1 - l2) / 1000L % 60L) < 10;
      }
      return false;
    }
    return false;
  }
  
  private void saveReceivedSmsData(String paramString1, String paramString2)
  {
    long l = System.currentTimeMillis();
    SharedPreferences.Editor localEditor = this.preferences.edit();
    localEditor.putString("last_sms_received_no", paramString1);
    localEditor.putString("last_sms_received_msg", paramString2);
    localEditor.putLong("last_sms_received_time", l);
    localEditor.commit();
  }
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    this.preferences = paramContext.getSharedPreferences(AppConstants.SUSPEND_PREF, 0);
    if ((this.preferences.getBoolean("is_suspend_on", false)) && (this.preferences.getBoolean("is_sms_enabled", false)) && (paramIntent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")))
    {
      Bundle localBundle = paramIntent.getExtras();
        SmsMessage[] smsMessages = new SmsMessage[]{null};


      if (localBundle != null) {
        for (;;)
        {
          int i;
          String str1;
          String str2;
          try
          {
            Object[] arrayOfObject = (Object[])localBundle.get("pdus");
            SmsMessage[] arrayOfSmsMessage = new SmsMessage[arrayOfObject.length];
            i = 0;
            if (i >= arrayOfSmsMessage.length) {
              return;
            }
            arrayOfSmsMessage[i] = SmsMessage.createFromPdu((byte[])arrayOfObject[i]);
            str1 = arrayOfSmsMessage[i].getOriginatingAddress();
            str2 = arrayOfSmsMessage[i].getMessageBody();
            if (str2.trim().toLowerCase().indexOf("error invalid number") != -1)
            {
              abortBroadcast();
              return;
            }
          }
          catch (Exception localException)
          {
            ProjectUtil.writeErrorLog(paramContext, localException.getMessage());
            return;
          }
          if (str2.trim().indexOf("Suspend Reply") != -1) {
            break;
          }
          String str3 = this.preferences.getString("custom_msg", paramContext.getResources().getString(R.string.default_message_to_reply));
          if ((str3 != null) && (str3.trim().length() == 0)) {
            str3 = paramContext.getResources().getString(R.string.default_message_to_reply);
          }
          String str4 = "Suspend! Reply: " + str3;
          if (this.preferences.getBoolean("is_sms_enabled", false))
          {
            if (!checkForDuplicateSms(str1, str2)) {
              ProjectUtil.sendSms(paramContext, str1, str4);
            }
            saveReceivedSmsData(str1, str2);
          }
          i++;
        }
      }
    }
  }
}
