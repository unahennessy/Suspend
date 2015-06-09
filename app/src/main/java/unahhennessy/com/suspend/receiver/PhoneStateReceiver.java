package unahhennessy.com.suspend.receiver;
/**
 * Created by unahe_000 on 21/05/2015 ${PACKAGE_NAME} Suspend.
 *
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.util.Log;

import unahhennessy.com.suspend.activity.SplashScreen;
import unahhennessy.com.suspend.other.SuspendApplication;
import unahhennessy.com.suspend.factors.FactorsInThisApp;

public class PhoneStateReceiver  extends BroadcastReceiver
{ // this is a phone state receiver class for Suspend
  //
  private SharedPreferences pref;
  private static final String TAG = "PhoneStateReceiver";
  
  public void onReceive(final Context paramContext, Intent paramIntent)
  {
      this.log("entered onReceive() within PhoneStateReceiver");
    this.pref = paramContext.getSharedPreferences(FactorsInThisApp.mSUSPEND_PREF, 0);
    if (paramIntent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
      if (this.pref.getBoolean("is_suspend_on", false)) {
        new CountDownTimer(3000L, 1000L)
        {
          public void onFinish()
          {
            Intent localIntent = new Intent(paramContext, SplashScreen.class);
            localIntent.addFlags(268435456);
            paramContext.startActivity(localIntent);
          }
          
          public void onTick(long paramAnonymousLong) {}
        }.start();
      }
    }
    while ((!this.pref.getBoolean("is_suspend_on", false)) || (!paramIntent.getAction().equals("android.intent.action.NEW_OUTGOING_CALL"))) {
      return;
    }
    String str = paramIntent.getStringExtra("android.intent.extra.PHONE_NUMBER");
    ((SuspendApplication)paramContext.getApplicationContext()).setOutGoingNumber(str);
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
