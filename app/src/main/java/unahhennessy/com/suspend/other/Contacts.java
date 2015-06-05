package unahhennessy.com.suspend.other;
/**
 * Created by unahe_000 on 23/05/2015.
 */
public class Contacts
{
      // I haven't used this class yet but it is ready for when I do need contact getters and setters
      private int mId = 0;
      private String mName= "";
      private String mNumber = "";

      public int getId()
      {
        return this.mId;
      }

      public String getName()
      {
        return this.mName;
      }

      public String getNumber()
      {
        return this.mNumber;
      }

      public void setId(int paramLong)
      {
        this.mId = paramLong;
      }

      public void setName(String paramString)
      {
        this.mName = paramString;
      }

      public void setNumber(String paramString)
      {
        this.mNumber = paramString;
      }
}
