package unahhennessy.com.suspend.taskmanager;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import java.util.Iterator;
import java.util.List;

public class IntentList
{
  private static List<ResolveInfo> infoList = null;

  public static Intent getIntent(String paramString, PackageManager paramPackageManager)
  {
    Iterator localIterator = getRunableList(paramPackageManager, false).iterator();
    ResolveInfo localResolveInfo;
    do
    {
      if (!localIterator.hasNext()) {
        return null;
      }
      localResolveInfo = (ResolveInfo)localIterator.next();
    } while (!paramString.equals(localResolveInfo.activityInfo.packageName));
    Intent localIntent = new Intent("android.intent.action.MAIN");
    localIntent.addCategory("android.intent.category.LAUNCHER");
    localIntent.setClassName(paramString, localResolveInfo.activityInfo.name);
    return localIntent;
  }

  public static List<ResolveInfo> getRunableList(PackageManager paramPackageManager, boolean paramBoolean)
  {
    try
    {
      if ((infoList == null) || (paramBoolean))
      {
        Intent localIntent = new Intent("android.intent.action.MAIN");
        localIntent.addCategory("android.intent.category.LAUNCHER");
        infoList = paramPackageManager.queryIntentActivities(localIntent, 0);
      }
      List localList = infoList;
      return localList;
    }
    finally {}
  }
}
