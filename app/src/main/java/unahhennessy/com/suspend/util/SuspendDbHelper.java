package unahhennessy.com.suspend.util;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;
import java.util.List;

public class SuspendDbHelper
{
  private static final String DATABASE_NAME = "SUSPENDDB";
  private static final int DATABASE_VERSION = 1;
  private static final String INSERT_LOGS = "insert into SUSPEND_LOGS(log,flag) values (?,?)";
  private static final String LOGS_TABLE_CREATE = "create table SUSPEND_LOGS (id integer primary key autoincrement, log text not null, flag text not null)";
  private static final String LOG_TABLE_NAME = "SUSPEND_LOGS";
  private long tobeReturned = 0;
  private Context context;
  private SQLiteDatabase db;

  public SuspendDbHelper(Context paramContext)
  {
    this.context = paramContext;
    this.db = new OpenHelper(this.context).getWritableDatabase();
  }
  public void deleteAllLogs()
  {
    this.db.delete("SUSPEND_LOGS", null, null);
  }

  public List<String> fetchAllLogs()
  {
    ArrayList localArrayList = new ArrayList();
    Cursor localCursor = this.db.query("SUSPEND_LOGS", new String[] { "id", "log", "flag" }, null, null, null, null, "id asc");
    if (localCursor.moveToFirst()) {
      do
      {
        localArrayList.add("[" + localCursor.getString(2) + "]: " + localCursor.getString(1));
      } while (localCursor.moveToNext());
    }
    if ((localCursor != null) && (!localCursor.isClosed())) {
      localCursor.close();
    }
    return localArrayList;
  }
   public long insertLog(String paramString1, String paramString2)
  {
    Cursor localCursor = this.db.query("SUSPEND_LOGS", new String[] { "id" }, null, null, null, null, "id asc");
    long l;
    if ((localCursor != null) && (localCursor.getCount() >= 400) && (localCursor.moveToFirst())) {
      l = localCursor.getInt(0);
    }
    try
    {
      String string = paramString1 + "\n";
      SQLiteStatement localSQLiteStatement = this.db.compileStatement("insert into SUSPEND_LOGS(log,flag) values (?,?)");
      localSQLiteStatement.bindString(1, string);
      localSQLiteStatement.bindString(2, paramString2);
      tobeReturned = localSQLiteStatement.executeInsert();
      return localSQLiteStatement.executeInsert();

    }
    catch (Exception localException)
    {

    }
    return tobeReturned;
  }


  private static class OpenHelper
    extends SQLiteOpenHelper
  {
    OpenHelper(Context paramContext)
    {
      super(paramContext,"SUSPENDDB", null, 1);
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
}
