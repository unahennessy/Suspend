package unahhennessy.com.suspend.other;
/**
 * Created by unahe_000 on 1/06/2015.
 */
import android.app.Application;
import android.content.SharedPreferences;
import android.util.Log;

import unahhennessy.com.suspend.factors.FactorsInThisApp;

public class SuspendApplication  extends Application
{
    // this is the same information that is in unahhennessy.com.suspend.application
  private String outGoingNumber = "";
    private static final String TAG = "SuspendApplication.java";
  
  public String getOutGoingNumber()
  {
      this.log("entered getOutGoingNumber() within SuspendApplication.java");
      return this.outGoingNumber;
  }
  
  public SharedPreferences getSharedPreferences()
  { this.log("entered getSharedPreferences() within SuspendApplication.java");
    return getSharedPreferences(FactorsInThisApp.mSUSPEND_PREF, 0);
  }
  
  public void onCreate()
  { this.log("entered onCreate() within SuspendApplication.java");
    super.onCreate();
  }
  
  public void onLowMemory()
  {
      this.log("entered onLowMemory() within SuspendApplication.java");
      super.onLowMemory();
  }
  
  public void onTerminate()
  {
      this.log("entered onTerminate() within SuspendApplication.java");
      super.onTerminate();
  }
  
  public void setOutGoingNumber(String paramString)
  {
      this.log("entered setOutGoingNumber() within SuspendApplication.java");
      this.outGoingNumber = paramString;
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
