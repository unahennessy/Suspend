package unahhennessy.com.suspend.other;

import android.content.Context;
import android.media.AudioManager;
import android.telephony.TelephonyManager;

import java.lang.reflect.Method;

public class TelephonyUtil
{
  public static void callReject(Context paramContext)
    throws Exception
  {
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
    return ((AudioManager)paramContext.getSystemService(Context.AUDIO_SERVICE)).getMode() == 2;
  }
}
