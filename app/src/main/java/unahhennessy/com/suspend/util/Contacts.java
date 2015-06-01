package unahhennessy.com.suspend.util;
/**
 * Created by unahe_000 on 23/05/2015.
 */
public class Contacts
{
  private String contactType = "";
  private int id = 0;
  private String name = "";
  private String number = "";

  public String getContactType()
  {
    return this.contactType;
  }

  public int getId()
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

  public void setId(int paramLong)
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
