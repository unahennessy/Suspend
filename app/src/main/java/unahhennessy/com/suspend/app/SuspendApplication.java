package unahhennessy.com.suspend.app;

import android.app.Application;
import android.content.SharedPreferences;
import unahhennessy.com.suspend.constants.AppConstants;

public class SuspendApplication  extends Application
{
  private String outGoingNumber = "";
  
  public String getOutGoingNumber()
  {
    return this.outGoingNumber;
  }
  
  public SharedPreferences getSharedPreferences()
  {
    return getSharedPreferences(AppConstants.SUSPEND_PREF, 0);
  }
  
  public void onCreate()
  {
    super.onCreate();
  }
  
  public void onLowMemory()
  {
    super.onLowMemory();
  }
  
  public void onTerminate()
  {
    super.onTerminate();
  }
  
  public void setOutGoingNumber(String paramString)
  {
    this.outGoingNumber = paramString;
  }
}
