package unahhennessy.com.suspend.util;
/**
 * Created by unahe_000 on 23/05/2015.
 */
public class ContactDetails
{
  private String contactType = "";
  private long id = -1L;
  private String name = "";
  private String number = "";

  public String getContactType()
  {
    return this.contactType;
  }

  public long getId()
  {
    return this.id;
  }

  public String getName()
  {
    return this.name;
  }

  public String getNumber()
  {
    return this.number;
  }

  public void setContactType(String paramString)
  {
    this.contactType = paramString;
  }

  public void setId(long paramLong)
  {
    this.id = paramLong;
  }

  public void setName(String paramString)
  {
    this.name = paramString;
  }

  public void setNumber(String paramString)
  {
    this.number = paramString;
  }
}
