package unahhennessy.com.suspend.other;
/**
 * Created by unahe_000 on 21/05/2015 ${PACKAGE_NAME} Suspend.
 *
 */
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class SuspendDbHelper
{
  private static final String mDATABASE_NAME = "SUSPEND_DB";
  private static final int mDATABASE_VERSION = 1;
  private static final String mINSERT_LOGS = "insert into SUSPEND_LOGS(log,flag) values (?,?)";
  private static final String mLOGS_TABLE_CREATE = "create table SUSPEND_LOGS (id integer primary key autoincrement, log text not null, flag text not null)";
  private static final String mLOG_TABLE_NAME = "SUSPEND_LOGS";
  private long mToBeReturned = 0;
  private Context mContext;
  private SQLiteDatabase mDB;
  private static final String TAG = "SuspendDbHelper";
  

  public SuspendDbHelper(Context paramContext)
  {
      this.log("entered SuspendDbHelper() within SuspendDbHelper.java");
    this.mContext = paramContext;
    this.mDB = new OpenHelper(this.mContext).getWritableDatabase();
  }
  public void deleteAllLogs()
  {
    this.mDB.delete(mLOG_TABLE_NAME, null, null);
  }

  public List<String> fetchAllLogs()
  {
      this.log("entered fetchAllLogs() within SuspendDbHelper.java");
    ArrayList mArrayList = new ArrayList();
    Cursor mCursor = this.mDB.query(mLOG_TABLE_NAME, new String[] { "id", "log", "flag" }, null, null, null, null, "id asc");
    if (mCursor.moveToFirst()) {
      do
      {
        mArrayList.add("[" + mCursor.getString(2) + "]: " + mCursor.getString(1));
      } while (mCursor.moveToNext());
    }
    if ((mCursor != null) && (!mCursor.isClosed())) {
      mCursor.close();
    }
    return mArrayList;
  }
   public long insertLog(String paramString1, String paramString2)
  {
      this.log("entered insertLog() within SuspendDbHelper.java");
    Cursor mCursor = this.mDB.query(mLOG_TABLE_NAME, new String[] { "id" }, null, null, null, null, "id asc");
    long l;
    if ((mCursor != null) && (mCursor.getCount() >= 200) && (mCursor.moveToFirst())) {
      l = mCursor.getInt(0);
    }
    try
    {
      String string = paramString1 + "\n";
      SQLiteStatement localSQLiteStatement = this.mDB.compileStatement("insert into SUSPEND_LOGS(log,flag) values (?,?)");
      localSQLiteStatement.bindString(1, string);
      localSQLiteStatement.bindString(2, paramString2);
      mToBeReturned = localSQLiteStatement.executeInsert();
      return localSQLiteStatement.executeInsert();

    }
    catch (Exception localException)
    {

    }
    return mToBeReturned;
  }


  private static class OpenHelper  extends SQLiteOpenHelper
  {

    OpenHelper(Context paramContext)
    {

      super(paramContext,mDATABASE_NAME, null, mDATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase paramSQLiteDatabase)
    {
      paramSQLiteDatabase.execSQL("create table SUSPEND_LOGS (id integer primary key autoincrement, log text not null, flag text not null)");
    }

    public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
    {
     onCreate(paramSQLiteDatabase);
    }
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
