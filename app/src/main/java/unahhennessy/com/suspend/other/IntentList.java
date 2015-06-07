package unahhennessy.com.suspend.other;
/**
 * Created by unahe_000 on 21/05/2015 ${PACKAGE_NAME} Suspend.
 *
 */

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.util.Log;

import java.util.Iterator;
import java.util.List;

public class IntentList
    // get a list of all intents in Suspend
{
  private static List<ResolveInfo> mInfoList = null;
  private static final String TAG = "IntentList";

  public static Intent getIntent(String paramString, PackageManager paramPackageManager)
  {
      log("entered getIntent() within IntentList.java");
    Iterator mIterator = getRunnableList(paramPackageManager, false).iterator();
    ResolveInfo mResolveInfo;
    do
    {
      if (!mIterator.hasNext()) {
        return null;
      }
      mResolveInfo = (ResolveInfo)mIterator.next();
    } while (!paramString.equals(mResolveInfo.activityInfo.packageName));
    Intent mIntent = new Intent("android.intent.action.MAIN");
    mIntent.addCategory("android.intent.category.LAUNCHER");
    mIntent.setClassName(paramString, mResolveInfo.activityInfo.name);
    return mIntent;
  }

  public static List<ResolveInfo> getRunnableList(PackageManager paramPackageManager, boolean paramBoolean)
  {
      log("entered getRunnableList() within IntentList.java");
    try
    {
      if ((mInfoList == null) || (paramBoolean))
      {
        Intent mIntent = new Intent("android.intent.action.MAIN");
        mIntent.addCategory("android.intent.category.LAUNCHER");
        mInfoList = paramPackageManager.queryIntentActivities(mIntent, 0);
      }
      List mList = mInfoList;
      return mList;
    }
    finally {}
  }

  private static void log(String msg)
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
