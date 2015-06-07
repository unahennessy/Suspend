package unahhennessy.com.suspend.other;

import android.util.Log;

/**
 * Created by unahe_000 on 23/05/2015.
 */
public class Logs
{
  // getters and setters for class Logs
  private String mFlag = "";
  private long mId = -1L;
  private String mLog = "";
  private static final String TAG = "Logs.java";
  public String getFlag()
  {
      log("entered getFlag() within Logs.java");
    return this.mFlag;
  }

  public long getId()
  {
      log("entered getId() within Logs.java");
      return this.mId;
  }

  public String getLog()
  {
      log("entered getLog() within Logs.java");
      return this.mLog;
  }

  public void setFlag(String paramString)
  {
      log("entered setFlag() within Logs.java");
      this.mFlag = paramString;
  }

  public void setId(long paramLong)

  {log("entered setId() within Logs.java");
    this.mId = paramLong;
  }

  public void setLog(String paramString)
  {log("entered setLog() within Logs.java");
    this.mLog = paramString;
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
