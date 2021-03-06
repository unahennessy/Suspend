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
import android.util.Log;

import unahhennessy.com.suspend.R;
import unahhennessy.com.suspend.factors.FactorsInThisApp;
import unahhennessy.com.suspend.other.NotificationStopOtherApps;

public class SplashScreen extends Activity
{
    // splash screen class
    //Indicating to the driver that the application is launching during long startup times
    //Providing information that is only needed once per visit
    //hence the countdowntimer
    //imported by PhoneStateReceiver
      private CountDownTimer mCountDownTimer;
      private SharedPreferences pref;
      private static final String TAG = "SplashScreen Activity";

      protected void onCreate(Bundle paramBundle)
      {
          this.log("entered onCreate() within SplashScreen.java");
          super.onCreate(paramBundle);
          setContentView(R.layout.splashscreen);

          NotificationStopOtherApps.writeInfoLog(this, "Splash Screen");

          this.pref = getSharedPreferences(FactorsInThisApp.mSUSPEND_PREF, 0);

          if (!this.pref.getBoolean(FactorsInThisApp.mIS_SETUP_COMPLETE, false)) {}
          for (;;)
          {
            Editor mEditor;
                try
                {
                    mEditor = this.pref.edit();
                    mEditor.remove("custom_msg");
                    mEditor.putBoolean("is_sms_enabled", true);
                    mEditor.putBoolean("is_mms_enabled", true);
                    mEditor.putBoolean("is_whatsapp_enabled", true);
                    mEditor.putBoolean("is_phone_enabled", false);

                } catch (Exception e)
                {

                    NotificationStopOtherApps.writeErrorLog(this, e.getMessage());
                    continue;

                }

            this.mCountDownTimer = new CountDownTimer(2000L, 1000L)
            {
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
                        }
                    }

                    public void onTick(long paramAnonymousLong) {
                    }

            }.start();

            if (this.pref.getBoolean("is_suspend_on", false))
            {
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
            this.log("entered onPause() within SplashScreen.java");
            super.onPause();
            this.mCountDownTimer.cancel();
            finish();
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
