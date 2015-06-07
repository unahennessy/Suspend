package unahhennessy.com.suspend.listener;
/**
 * Created by unahe_000 on 21/05/2015 ${PACKAGE_NAME} Suspend.
 *
 */

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.util.Vector;

import unahhennessy.com.suspend.R;
import unahhennessy.com.suspend.factors.FactorsInThisApp;
import unahhennessy.com.suspend.other.NotificationStopOtherApps;
import unahhennessy.com.suspend.other.SuspendApplication;

public class PhoneListener extends Service
{ // a listener for calls received
  public static StateListener phoneStateListener = null;
  public static TelephonyManager telephonymanager = null;
  private Vector<String> mNum;
  private SharedPreferences.Editor mEdit;
  String mMsgText = "Suspend Reply:";
  private SharedPreferences pref;
  private static final String TAG = "PhoneListener";

  private boolean isNumberValid(String paramString)
  {
      this.log("entered isNumberValid() within PhoneListener.java");
    int i = this.mNum.size();
    if (paramString != null) {}
    for (;;)
    {
      int j = 0;
      try
      {
        if (paramString.trim().length() <= 0) {
            break;
        }
         
      }
      catch (Exception localException)
          { 
            String string;
            boolean bool;
            string = this.mNum.get(j).toString();
            if ((string == null) || (string.trim().length() >= 9))
            {
              if (!paramString.contains(string.trim()))
              {
                bool = string.trim().contains(paramString);
                if (!bool) {}
              }
              else
              {
                return true;
              }
            }
            break; 
          }

      while (j >= i)
      {
         
        j++;
        return false;

      }
    }
      return true;
  }
  
  public static void removeListener()
  {
    try
    {

      telephonymanager.listen(phoneStateListener, 0);
      return;
    }
    catch (Exception localException) {}
  }
  
  public IBinder onBind(Intent paramIntent)
  {
      this.log("entered onBind() within PhoneListener.java");
    return null;
  }
  
  public void onCreate()
  {
      this.log("entered onCreate() within PhoneListener.java");
    super.onCreate();
    for (;;)
    {
      int i =0;
      int j = 0;
      try
      {
        this.pref = getSharedPreferences(FactorsInThisApp.mSUSPEND_PREF, 0);
        i = this.pref.getInt("contact_count", 0);
        this.mEdit = this.pref.edit();
        String mString1 = this.pref.getString("custom_msg", getResources().getString(R.string.default_message_to_reply));
        if (mString1.trim().length() == 0) {
          mString1 = getResources().getString(R.string.default_message_to_reply);
        }
        this.mMsgText += mString1.trim();
          
        if (i > 0)
        {
          j = 1;
          break;
        }
        phoneStateListener = new StateListener();
        telephonymanager = (TelephonyManager)getSystemService(TELEPHONY_SERVICE);
        telephonymanager.listen(phoneStateListener, 32);
        return;
      }
      catch (Exception localException)
      {
        String mString2;
        int k;
        Object mObject;
        String mString3;
          mString2 = this.pref.getString("contact_number" + j, "");
          k = mString2.trim().length();
          if ((mString2 != null) && (k > 0)) {
              if (k > 10)
              {
                  mObject = mString2.trim().substring(k - 10);

              }
              else
              {
                  mString3 = mString2.trim();
                  mObject = mString3;
                  continue;
              }
        return;
      }


      }
      for (;;)
      {
        if (j <= i)
        {
          break;
        }
          j++;
        break;

      }
    }
  }
  
  public void onDestroy()
  {  this.log("entered onDestroy() within PhoneListener.java");
    super.onDestroy();
    telephonymanager.listen(null, 0);
    stopSelf();
    System.out.println("Inside OnDestroy within PhoneListener");
  }

    class StateListener extends PhoneStateListener
    {
        StateListener() {}

        public void onCallStateChanged(int paramInt, String paramString)
        {
            super.onCallStateChanged(paramInt, paramString);
            String mString = ((SuspendApplication)PhoneListener.this.getApplicationContext()).getOutGoingNumber();
            System.out.println("Mobile Nimber: " + mString);
            if ((paramString == null) || (paramString.trim().length() == 0)) {
                paramString = mString;
            }
            System.out.println("Phone Number: " + paramString);
            switch (paramInt)
            {
                default:
                case 1:
                case 2:
                    do
                    {    if (PhoneListener.this.pref.getBoolean("is_suspend_on", false))
                    {
                        if (PhoneListener.this.pref.getBoolean("is_phone_enabled", false))
                        {
                            if (PhoneListener.this.pref.getBoolean("is_suspend_call_active", false))
                            {  }
                        }
                    }
                        PhoneListener.this.mEdit.putBoolean("is_suspend_call_active", true);
                        PhoneListener.this.mEdit.commit();
                        NotificationStopOtherApps.silentMode(PhoneListener.this.getApplicationContext());

                    } while ((!PhoneListener.this.pref.getBoolean("is_suspend_on", false)) || (!PhoneListener.this.isNumberValid(paramString)));

                    PhoneListener.this.mEdit.putBoolean("is_suspend_call_active", true);
                    PhoneListener.this.mEdit.commit();
                    return;
            }  }



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
