package unahhennessy.com.suspend.other;
/**
 * Created by unahe_000 on 21/05/2015 ${PACKAGE_NAME} Suspend.
 *
 */

import android.content.Context;
import android.media.AudioManager;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.lang.reflect.Method;

public class TelephonyUtil
{

  private static final String TAG = "TelephonyUtil";
  public static void callReject(Context paramContext)
    throws Exception
  {
      log("entered callReject() within TelephonyUtil.java");

      try
    {
      TelephonyManager lTelephonyManager = (TelephonyManager)paramContext.getSystemService(Context.TELEPHONY_SERVICE);
      Method localMethod = Class.forName(lTelephonyManager.getClass().getName()).getDeclaredMethod("getITelephony");
      localMethod.setAccessible(true);

      return;
    }
    catch (Exception localException) {}
  }
  
  public static boolean isCallActive(Context paramContext)
  {
      log("entered isCallActive() within TelephonyUtil.java");
    return ((AudioManager)paramContext.getSystemService(Context.AUDIO_SERVICE)).getMode() == 2;
  }

    private static void log(String msg)
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
