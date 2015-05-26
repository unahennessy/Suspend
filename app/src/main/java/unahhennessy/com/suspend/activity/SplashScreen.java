package unahhennessy.com.suspend.activity;
/**
 * Created by unahe_000 on 21/05/2015.
 */
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.CountDownTimer;

import java.util.List;

import unahhennessy.com.suspend.R;
import unahhennessy.com.suspend.constants.AppConstants;
import unahhennessy.com.suspend.util.ProjectUtil;

public class SplashScreen
  extends Activity
{
  private CountDownTimer mCountDownTimer;
  private SharedPreferences pref;

  public void getInstalledApps()
  {
    PackageManager localPackageManager = getApplicationContext().getPackageManager();
    List localList = localPackageManager.getInstalledPackages(0);
    for (int i = 0;; i++)
    {
      if (i >= localList.size()) {
        return;
      }
      PackageInfo localPackageInfo = (PackageInfo)localList.get(i);
      System.out.println("##############    Start ####################");
      System.out.println("Description: " + (String)localPackageInfo.applicationInfo.loadDescription(localPackageManager));
      System.out.println("App's Name: " + localPackageInfo.applicationInfo.loadLabel(localPackageManager).toString());
      System.out.println("App's Package Name: " + localPackageInfo.packageName);
      System.out.println("App's Version Name: " + localPackageInfo.versionName);
      System.out.println("App's Version Codee: " + localPackageInfo.versionCode);
      System.out.println("##############    End ####################");
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.splashscreen);
    ProjectUtil.writeInfoLog(this, "Splash Screen");
    this.pref = getSharedPreferences(AppConstants.SUSPEND_PREF, 0);
    if (!this.pref.getBoolean(AppConstants.IS_SETUP_COMPLETE, false)) {}
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
            ProjectUtil.writeErrorLog(this, localException1.getMessage());
            continue;

           }

        this.mCountDownTimer = new CountDownTimer(2000L, 1000L) {
            public void onFinish() {
                if (SplashScreen.this.pref.getBoolean(AppConstants.IS_SETUP_COMPLETE, false))
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
