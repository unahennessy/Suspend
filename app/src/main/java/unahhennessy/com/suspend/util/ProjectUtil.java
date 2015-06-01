package unahhennessy.com.suspend.util;
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
import android.net.ConnectivityManager;
import android.os.Vibrator;
import android.telephony.SmsManager;
import android.widget.RemoteViews;

import java.util.Calendar;
import java.util.Date;

import unahhennessy.com.suspend.R;
import unahhennessy.com.suspend.activity.SuspendOn;
import unahhennessy.com.suspend.constants.AppConstants;

public class ProjectUtil
{
  public static String currentTime()
  {
    return new Date(Calendar.getInstance().getTimeInMillis()).toString();
  }

  public static String getAppVersion(Context paramContext)
  {
    try
    {
      String string = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionName;
      return string;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      localNameNotFoundException.printStackTrace();
    }
    return "";
  }

  public static int getAppVersionCode(Context paramContext)
  {
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionCode;
      return i;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      localNameNotFoundException.printStackTrace();
    }
    return 0;
  }

  public static boolean isDataConnectionAvailable(Context paramContext)
  {
    ConnectivityManager localConnectivityManager;
    localConnectivityManager = (ConnectivityManager)paramContext.getSystemService(Context.TEXT_SERVICES_MANAGER_SERVICE);
    if (localConnectivityManager.getActiveNetworkInfo() == null) {
      return false;
    }
    return localConnectivityManager.getActiveNetworkInfo().isConnected();
  }

  public static boolean isLiveAccount(String paramString)
  {
    int i = paramString.indexOf("@");
    boolean bool1 = false;
    if (i != -1)
    {
      boolean bool2 = paramString.substring(i + 1).trim().equalsIgnoreCase("live.com");
        bool1 = bool2;
    }
    return bool1;
  }


  public static void launchApp(Context paramContext, String paramString)
  {
    Intent localIntent = paramContext.getPackageManager().getLaunchIntentForPackage(paramString);
    if (localIntent != null) {}
    try
    {
      paramContext.startActivity(localIntent);
      return;
    }
    catch (Exception localException1)
    {
      if (paramString.trim().equals("com.google.android.apps.maps")) {
        try
        {
          localIntent.setComponent(new ComponentName(paramString, "com.google.android.maps.MapsActivity"));
          paramContext.startActivity(localIntent);
          return;
        }
        catch (Exception localException2)
        {
          writeErrorLog(paramContext, localException2.getMessage());
          return;
        }
      }
      writeErrorLog(paramContext, localException1.getMessage());
    }
  }

  public static void notifyIcon(Context paramContext)
  {
    SharedPreferences localSharedPreferences = paramContext.getSharedPreferences(AppConstants.SUSPEND_PREF, 0);
    NotificationManager localNotificationManager = (NotificationManager)paramContext.getSystemService(Context.NOTIFICATION_SERVICE);
    if (localSharedPreferences.getBoolean("is_suspend_on", false))
    {
      Notification localNotification = new Notification(R.drawable.appnotificationsuspend, "", System.currentTimeMillis());
      localNotification.flags = (0x22 | localNotification.flags);
      localNotification.contentView = new RemoteViews(paramContext.getPackageName(), R.layout.custom_notification);
      PendingIntent localPendingIntent = PendingIntent.getActivity(paramContext, 0, new Intent(paramContext, SuspendOn.class), 0);
      localNotification.contentIntent = localPendingIntent;
      localNotification.setLatestEventInfo(paramContext, "Suspend", "Notifications", localPendingIntent);
      localNotificationManager.notify(1, localNotification);
      return;
    }
    localNotificationManager.cancel(1);
  }

  public static void sendSms(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      PendingIntent localPendingIntent = PendingIntent.getActivity(paramContext, 0, new Intent(paramContext, ProjectUtil.class), 0);
      SmsManager.getDefault().sendTextMessage(paramString1, null, paramString2, localPendingIntent, null);
      AppConstants.SUSPEND_DB.insertLog(currentTime() + " Send SmsMessage", "Information");
      AppConstants.SUSPEND_DB.insertLog(currentTime() + " Number: " + paramString1 + ", Body: " + paramString2, "Info");
      AppConstants.SUSPEND_DB.insertLog(currentTime() + " Sent SmsMessage Successfully.", "Info");
      return;
    }
    catch (Exception localException)
    {
      AppConstants.SUSPEND_DB.insertLog(currentTime() + "Send SmsMessage", "Information");
      AppConstants.SUSPEND_DB.insertLog(currentTime() + " Number: " + paramString1 + " Body: " + paramString2, "Info");
      AppConstants.SUSPEND_DB.insertLog(currentTime() + " Error: " + localException.getMessage(), "Error");
    }
  }

  public static void silentMode(Context paramContext)
  {
    try
    {
      SharedPreferences localSharedPreferences = paramContext.getSharedPreferences(AppConstants.SUSPEND_PREF, 0);
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
  {
    ((Vibrator)paramContext.getSystemService(Context.VIBRATOR_SERVICE)).vibrate(600L);
  }

  public static void writeErrorLog(Context paramContext, String paramString)
  {
    if (AppConstants.SUSPEND_DB == null) {
      AppConstants.SUSPEND_DB = new SuspendDbHelper(paramContext);
    }
    AppConstants.SUSPEND_DB.insertLog(currentTime() + " " + paramString, "Error");
  }

  public static void writeInfoLog(Context paramContext, String paramString)
  {
    if (AppConstants.SUSPEND_DB == null) {
      AppConstants.SUSPEND_DB = new SuspendDbHelper(paramContext);
    }
    AppConstants.SUSPEND_DB.insertLog(currentTime() + " " + paramString, "Info");
  }
} //end of ProjectUtil.java
