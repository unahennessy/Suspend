package unahhennessy.com.suspend.other;

import android.util.Log;

/**
 * Created by unahe_000 on 23/05/2015.
 */
public class Contacts
{
      // I haven't used this class yet but it is ready for when I do need contact getters and setters
    private int mId = 0;
    private String mName= "";
    private String mNumber = "";
    private static final String TAG = "Contacts";
      public int getId()
      {
        return this.mId;
      }

      public String getName()
      {
          this.log("entered getName() within Contacts.java");

          return this.mName;
      }

      public String getNumber()
      {
          this.log("entered getNumber() within Contacts.java");
          return this.mNumber;
      }

      public void setId(int paramLong)
      {  this.log("entered setId() within Contacts.java");

          this.mId = paramLong;
      }

      public void setName(String paramString)
      {  this.log("entered setName() within Contacts.java");

          this.mName = paramString;
      }

      public void setNumber(String paramString)
      {  this.log("entered setNumber() within Contacts.java");

          this.mNumber = paramString;
      }

    private void log(String msg)
    {
        try {
            Thread.sleep(500);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i(Contacts.TAG, msg);

    }




}
