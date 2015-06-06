package unahhennessy.com.suspend.other;
/**
 * Created by unahe_000 on 23/05/2015.
 */

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.RemoteViews;

import java.util.Calendar;
import java.util.Date;

import unahhennessy.com.suspend.R;
import unahhennessy.com.suspend.activity.NoteListScrn;
import unahhennessy.com.suspend.activity.SuspendOn;
import unahhennessy.com.suspend.factors.FactorsInThisApp;

public class NotificationStopOtherApps
{   NotificationManager mNotificationManager;
    private static String mString;
    private String mParamString;
    private Context mParamContext;
    private static int mNumberMessages = 0;
    private static Notification mNotification;
    private static final String TAG = "NotifyStopApps";
  public static String currentTime()
  {
    return new Date(Calendar.getInstance().getTimeInMillis()).toString();
  }


  public static String getAppVersion(Context paramContext)
  {  log("entered getAppVersion() within NotificationStopOtherApps.java");

      mString = "";
    try
    {
       mString = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionName;
      return mString;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      localNameNotFoundException.printStackTrace();
    }
    return mString;

  }


  public static void launchApp(Context mParamContext, String mParamString)
  { log("entered launchApp() within NotificationStopOtherApps.java");
    Intent localIntent = mParamContext.getPackageManager().getLaunchIntentForPackage(mParamString);
    if (localIntent != null) {
    try
    {
      mParamContext.startActivity(localIntent);
    }
    catch (Exception localException1)
    {
      if (mParamString.trim().equals("com.google.android.apps.maps")) {
        try
        {
          localIntent.setComponent(new ComponentName(mParamString, "com.google.android.maps.MapsActivity"));
          mParamContext.startActivity(localIntent);

        }
        catch (Exception localException2)
        {
          writeErrorLog(mParamContext, localException2.getMessage());

        }
      }
      writeErrorLog(mParamContext, localException1.getMessage());
    }
  }}
    NotificationCompat.Builder NotificationCompat(Context mParamContext)

    {
        // Creates an explicit intent for an Activity in your app
        Intent resultIntentList = new Intent(mParamContext, NoteListScrn.class);

        NotificationCompat.Builder mBuilder;
        mBuilder = ((NotificationCompat.Builder)(mParamContext.getSystemService(Context.NOTIFICATION_SERVICE))).setSmallIcon(R.drawable.appnotificationsuspend)
                .setContentTitle("Notifications")
                .setContentText("Texts received");
        return mBuilder;
    }
  public static void notifyIcon(Context mParamContext)
  {
      log("entered notifyIcon() within NotificationStopOtherApps.java");
        SharedPreferences mParamContextSharedPreferencesPreferences = mParamContext.getSharedPreferences(FactorsInThisApp.mSUSPEND_PREF, 0);
        NotificationManager mNotificationManager = (NotificationManager)mParamContext.getSystemService(Context.NOTIFICATION_SERVICE);

          int notifyID = 1;

          NotificationCompat.Builder mNotifyBuilder = ((NotificationCompat.Builder)mParamContext.getSystemService(Context.NOTIFICATION_SERVICE))
                  .setContentTitle("New Message")
                  .setContentText("You've received new messages.")
                  .setSmallIcon(R.drawable.appnotificationsuspend);

          mNumberMessages = 0;

            // Start of a loop that processes data and then notifies the user


          mNotifyBuilder.setContentText("No of messages received")
                  .setNumber(++mNumberMessages);
          // Because the ID remains unchanged, the existing notification is
          // updated.
          mNotificationManager.notify(
                  notifyID,
                  mNotifyBuilder.build());

        if (mParamContextSharedPreferencesPreferences.getBoolean("is_suspend_on", false))
        {
           String mString = (R.drawable.appnotificationsuspend + "" +  System.currentTimeMillis() );

          mNotification.flags = (0x22 | mNotification.flags);
         mNotification.contentView = new RemoteViews(mParamContext.getPackageName(), R.layout.custom_notification);
          PendingIntent mPendingIntent = PendingIntent.getActivity(mParamContext, 0, new Intent(mParamContext, SuspendOn.class), 0);
          mNotification.contentIntent = mPendingIntent;
          mNotificationManager.notify(1, mNotification);

        }
        mNotificationManager.cancel(1);
      }

      public static void sendSms(Context paramContext, String paramString1, String paramString2)
      { log("entered sendSms() within NotificationStopOtherApps.java");
        try
        {
          PendingIntent localPendingIntent = PendingIntent.getActivity(paramContext, 0, new Intent(paramContext, NotificationStopOtherApps.class), 0);
          SmsManager.getDefault().sendTextMessage(paramString1, null, paramString2, localPendingIntent, null);
          FactorsInThisApp.mSUSPEND_DB.insertLog(currentTime() + " Send SmsMessage", "Information");
          FactorsInThisApp.mSUSPEND_DB.insertLog(currentTime() + " Number: " + paramString1 + ", Body: " + paramString2, "Info");
          FactorsInThisApp.mSUSPEND_DB.insertLog(currentTime() + " Sent SmsMessage Successfully.", "Info");

        }
        catch (Exception localException)
        {
          FactorsInThisApp.mSUSPEND_DB.insertLog(currentTime() + "Send SmsMessage", "Information");
          FactorsInThisApp.mSUSPEND_DB.insertLog(currentTime() + " Number: " + paramString1 + " Body: " + paramString2, "Info");
          FactorsInThisApp.mSUSPEND_DB.insertLog(currentTime() + " Error: " + localException.getMessage(), "Error");
        }
      }

      public static void silentMode(Context paramContext)
      { log("entered silentMode() within NotificationStopOtherApps.java");
        try
        {
          SharedPreferences localSharedPreferences = paramContext.getSharedPreferences(FactorsInThisApp.mSUSPEND_PREF, 0);
          AudioManager localAudioManager = (AudioManager)paramContext.getSystemService(Context.AUDIO_SERVICE);
          if (localSharedPreferences.getBoolean("is_suspend_on", false))
          {
            if (localSharedPreferences.getBoolean("is_suspend_call_active", false))
            {
                //check for bluetooth and if there is bluetooth then allow calls
              localAudioManager.setRingerMode(2);

            }
            localAudioManager.setRingerMode(0);

          }
          localAudioManager.setRingerMode(2);

        }
        catch (Exception localException) {}
      }

      public static void startVibration(Context paramContext)
      { log("entered startVibration() within NotificationStopOtherApps.java");
        ((Vibrator)paramContext.getSystemService(Context.VIBRATOR_SERVICE)).vibrate(600L);
      }

      public static void writeErrorLog(Context paramContext, String paramString)
      { log("entered writeErrorLog() within NotificationStopOtherApps.java");
        if (FactorsInThisApp.mSUSPEND_DB == null) {
          FactorsInThisApp.mSUSPEND_DB = new SuspendDbHelper(paramContext);
        }
        FactorsInThisApp.mSUSPEND_DB.insertLog(currentTime() + " " + paramString, "Error");
      }

      public static void writeInfoLog(Context paramContext, String paramString)
      { log("entered writeInfoLog() within NotificationStopOtherApps.java");
        if (FactorsInThisApp.mSUSPEND_DB == null) {
          FactorsInThisApp.mSUSPEND_DB = new SuspendDbHelper(paramContext);
        }
        FactorsInThisApp.mSUSPEND_DB.insertLog(currentTime() + " " + paramString, "Info");
      }
    private static void log(String msg)
    {
        try {
            Thread.sleep(500);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i(NotificationStopOtherApps.TAG, msg);

    }
    } //end of NotificationStopOtherApps.java
