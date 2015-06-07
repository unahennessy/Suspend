package unahhennessy.com.suspend.listener;
/**
 * Created by unahe_000 on 21/05/2015 ${PACKAGE_NAME} Suspend.
 *
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import unahhennessy.com.suspend.R;
import unahhennessy.com.suspend.factors.FactorsInThisApp;
import unahhennessy.com.suspend.other.NotificationStopOtherApps;

public class SmsListener  extends BroadcastReceiver
{ // the smslistener i am using to send texts back to people who text the driver
  private final String DEFAULT_MSG = "Driving so can't text. Will text when I'm stopped!";
 
  private static final String TAG = "SMSListener";
  private SharedPreferences preferences;
  
  private boolean checkForDuplicateSms(String paramString1, String paramString2)
  {   
      this.log("entered checkForDuplicateSms() within SmsListener.java");
    long l1 = System.currentTimeMillis();
    String mString1 = this.preferences.getString("last_sms_received_no", "");
    String mString2 = this.preferences.getString("last_sms_received_msg", "");
    long l2 = this.preferences.getLong("last_sms_received_time", 0L);
    if (mString1.trim().equalsIgnoreCase(paramString1))
    {
      if (mString2.trim().equals(paramString2)) {
        return (int)((l1 - l2) / 1000L % 60L) < 10;
      }
      return false;
    }
    return false;
  }
  
  private void saveReceivedSmsData(String paramString1, String paramString2)
  {
      this.log("entered saveReceivedSmsData() within SmsListener.java");
    long l = System.currentTimeMillis();
    SharedPreferences.Editor localEditor = this.preferences.edit();
    localEditor.putString("last_sms_received_no", paramString1);
    localEditor.putString("last_sms_received_msg", paramString2);
    localEditor.putLong("last_sms_received_time", l);
    localEditor.commit();
  }
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
      this.log("entered onReceive() within SmsListener.java");
    this.preferences = paramContext.getSharedPreferences(FactorsInThisApp.mSUSPEND_PREF, 0);
    if ((this.preferences.getBoolean("is_suspend_on", false)) && (this.preferences.getBoolean("is_sms_enabled", false)) && (paramIntent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")))
    {
      Bundle localBundle = paramIntent.getExtras();
        SmsMessage[] smsMessages = new SmsMessage[]{null};


      if (localBundle != null) {
        for (;;)
        {
          int i;
          String mString1;
          String mString2;
          try
          {
            Object[] arrayOfObject = (Object[])localBundle.get("pdus");
            SmsMessage[] arrayOfSmsMessage = new SmsMessage[arrayOfObject.length];
            i = 0;
            if (i >= arrayOfSmsMessage.length) {
              return;
            }
            arrayOfSmsMessage[i] = SmsMessage.createFromPdu((byte[])arrayOfObject[i]);
            mString1 = arrayOfSmsMessage[i].getOriginatingAddress();
            mString2 = arrayOfSmsMessage[i].getMessageBody();
            if (mString2.trim().toLowerCase().indexOf("error invalid number") != -1)
            {
              abortBroadcast();
              return;
            }
          }
          catch (Exception localException)
          {
            NotificationStopOtherApps.writeErrorLog(paramContext, localException.getMessage());
            return;
          }
          if (mString2.trim().indexOf("Suspend Reply") != -1) {
            break;
          }
          String str3 = this.preferences.getString("custom_msg", paramContext.getResources().getString(R.string.default_message_to_reply));
          if ((str3 != null) && (str3.trim().length() == 0)) {
            str3 = paramContext.getResources().getString(R.string.default_message_to_reply);
          }
          String str4 = "Suspend! Reply: " + str3;
          if (this.preferences.getBoolean("is_sms_enabled", false))
          {
            if (!checkForDuplicateSms(mString1, mString2)) {
              NotificationStopOtherApps.sendSms(paramContext, mString1, str4);
            }
            saveReceivedSmsData(mString1, mString2);
          }
          i++;
        }
      }
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
