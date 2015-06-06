package unahhennessy.com.suspend.service;
/**
 * Created by unahe_000 on 21/05/2015.
 */

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.util.Log;

import java.util.ArrayList;

import unahhennessy.com.suspend.factors.FactorsInThisApp;
import unahhennessy.com.suspend.other.TelephonyUtil;

public class AppTrackingService extends Service
{
     private Context mContext;
     private boolean mIsContinue;
     private String mMusic = "";
     private String mNavigation = "";
     private ArrayList<String> mPackageList = new ArrayList();
     private SharedPreferences pref;
     private static final String TAG = "AppTrackingService";

          public IBinder onBind(Intent paramIntent)
          {
              this.log("entered onBind() within AppTrackingService.java");
            return null;
          }

          public void onCreate()
          {
              this.log("entered onCreate() within AppTrackingService.java");
            super.onCreate();
            this.mContext = this;
            this.pref = getSharedPreferences(FactorsInThisApp.mSUSPEND_PREF, 0);
            this.mMusic = this.pref.getString("music_pkg", "");
            this.mNavigation = this.pref.getString("navigation_pkg", "");
            boolean mIsContinue = false;


          }

    protected void launchApp(String paramString)
    { this.log("entered launchApp() within AppTrackingService.java");
        Intent localIntent = AppTrackingService.this.getPackageManager().getLaunchIntentForPackage(paramString);
        localIntent.addFlags(67108864);
        localIntent.addFlags(2097152);
        try
        {
            AppTrackingService.this.startActivity(localIntent);
            return;
        }
        catch (Exception localException)
        {
            localException.printStackTrace();
        }
    }


            public void run()
            {
                this.log("entered run() within AppTrackingService.java");
              ActivityManager localActivityManager = (ActivityManager)AppTrackingService.this.getSystemService(ACTIVITY_SERVICE);
              for (;;)
              {
                if (!this.mIsContinue)
                {
                  return;

                }
                try
                {
                  if (AppTrackingService.this.pref.getBoolean("is_suspend_on", false))
                  {
                    if ((!AppTrackingService.this.pref.getBoolean("is_suspend_call_active", false)) && (!AppTrackingService.this.pref.getBoolean("is_suspend_call_out_active", false)))
                    {
                      ActivityManager.RunningTaskInfo mString = ((ActivityManager.RunningTaskInfo)localActivityManager.getRunningAppProcesses());
                        String mString2 = (AppTrackingService.this.mMusic);
                        String mString3 = (AppTrackingService.this.mNavigation);
                        String mString1 = mString.toString().toLowerCase();


                      if ((!mString.equals(AppTrackingService.this.mContext.getPackageName())) && (!mString1.equalsIgnoreCase(mString2) && (!mString1.equalsIgnoreCase(mString3))))
                      {
                        launchApp("com.suspend");
                      }
                    }
                    for (;;)
                        if (!TelephonyUtil.isCallActive(AppTrackingService.this.mContext))
                        {
                            SharedPreferences.Editor localEditor = AppTrackingService.this.pref.edit();
                            localEditor.putBoolean("is_suspend_call_out_active", false);
                            localEditor.commit();
                        }
                  }
                  this.mIsContinue = false;
                  AppTrackingService.this.stopSelf();
                }
                catch (Exception localException)
                {
                    localException.printStackTrace();

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
        Log.i(AppTrackingService.TAG, msg);

    }

 }

