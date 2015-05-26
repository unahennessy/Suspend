package unahhennessy.com.suspend.util;
/**
 * Created by unahe_000 on 23/05/2015.
 */
public class Logs
{
  private String flag = "";
  private long id = -1L;
  private String log = "";

  public String getFlag()
  {
    return this.flag;
  }

  public long getId()
  {
    return this.id;
  }

  public String getLog()
  {
    return this.log;
  }

  public void setFlag(String paramString)
  {
    this.flag = paramString;
  }

  public void setId(long paramLong)
  {
    this.id = paramLong;
  }

  public void setLog(String paramString)
  {
    this.log = paramString;
  }
}
