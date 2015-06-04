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
import unahhennessy.com.suspend.activity.SplashScreen;
import unahhennessy.com.suspend.app.SuspendApplication;
import unahhennessy.com.suspend.constants.AppConstants;

public class PhoneStateReceiver  extends BroadcastReceiver
{ // this is a phone state receiver class for Suspend
  // i want this used only when there is no bluetooth
  private SharedPreferences pref;
  
  public void onReceive(final Context paramContext, Intent paramIntent)
  {
    this.pref = paramContext.getSharedPreferences(AppConstants.SUSPEND_PREF, 0);
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
}
