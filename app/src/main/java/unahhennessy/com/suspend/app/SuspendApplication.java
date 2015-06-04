package unahhennessy.com.suspend.app;
/**
 * Created by unahe_000 on 1/06/2015.
 */
import android.app.Application;
import android.content.SharedPreferences;
import unahhennessy.com.suspend.constants.AppConstants;

public class SuspendApplication  extends Application
{
    // this is the same information that is in unahhennessy.com.suspend.application
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
