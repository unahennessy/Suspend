package unahhennessy.com.suspend.activity;
/**
 * Created by unahe_000 on 21/05/2015.
 */
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.CountDownTimer;

import unahhennessy.com.suspend.R;
import unahhennessy.com.suspend.factors.FactorsInThisApp;
import unahhennessy.com.suspend.other.NotificationStopOtherApps;

public class SplashScreen
  extends Activity
{
  private CountDownTimer mCountDownTimer;
  private SharedPreferences pref;

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.splashscreen);
    NotificationStopOtherApps.writeInfoLog(this, "Splash Screen");
    this.pref = getSharedPreferences(FactorsInThisApp.mSUSPEND_PREF, 0);
    if (!this.pref.getBoolean(FactorsInThisApp.mIS_SETUP_COMPLETE, false)) {}
    for (;;) {
        Editor localEditor;
        try {
            localEditor = this.pref.edit();
            localEditor.remove("custom_msg");
            localEditor.putBoolean("is_sms_enabled", true);
            localEditor.putBoolean("is_mms_enabled", true);
            localEditor.putBoolean("is_whatsapp_enabled", true);
            localEditor.putBoolean("is_phone_enabled", false);

        } catch (Exception localException1) {
            int k;
            Intent localIntent;
            NotificationStopOtherApps.writeErrorLog(this, localException1.getMessage());
            continue;

           }

        this.mCountDownTimer = new CountDownTimer(2000L, 1000L) {
            public void onFinish() {
                if (SplashScreen.this.pref.getBoolean(FactorsInThisApp.mIS_SETUP_COMPLETE, false))
                {


                    boolean bool1 = SplashScreen.this.pref.getBoolean("is_sms_enabled", false);
                    boolean bool2 = SplashScreen.this.pref.getBoolean("is_mms_enabled", false);
                    boolean bool3 = SplashScreen.this.pref.getBoolean("is_whatapp_enabled", false);
                    boolean bool4 = SplashScreen.this.pref.getBoolean("is_phone_enabled", false);
                    if ((!bool1) && (!bool2) && (!bool3) && (!bool4)) {
                        SplashScreen.this.startActivity(new Intent(SplashScreen.this, SuspendOff.class));
                    }

                }
                for (; ;)
                {
                    SplashScreen.this.finish();

                    SplashScreen.this.startActivity(new Intent(SplashScreen.this, SuspendOn.class));
                    SplashScreen.this.startActivity(new Intent(SplashScreen.this, WelcomeScreen.class));
                    continue;


                }
            }

            public void onTick(long paramAnonymousLong) {
            }
        }.start();
        if (this.pref.getBoolean("is_suspend_on", false)) {
            this.mCountDownTimer.cancel();

            Intent localIntent = new Intent(this, SuspendOn.class);
            localIntent.addFlags(67108864);
            startActivity(localIntent);
            finish();
        }
        return;

    }
  }

  protected void onPause()
  {
    super.onPause();
    this.mCountDownTimer.cancel();
    finish();
  }
}
