package unahhennessy.com.suspend.other;
/**
 * Created by unahe_000 on 23/05/2015.
 */
public class Logs
{
  // getters and setters for class Logs
  private String mFlag = "";
  private long mId = -1L;
  private String mLog = "";

  public String getFlag()
  {
    return this.mFlag;
  }

  public long getId()
  {
    return this.mId;
  }

  public String getLog()
  {
    return this.mLog;
  }

  public void setFlag(String paramString)
  {
    this.mFlag = paramString;
  }

  public void setId(long paramLong)
  {
    this.mId = paramLong;
  }

  public void setLog(String paramString)
  {
    this.mLog = paramString;
  }
}
