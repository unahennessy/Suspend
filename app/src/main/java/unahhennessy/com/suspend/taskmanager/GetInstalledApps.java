package unahhennessy.com.suspend.taskmanager;

import android.content.Context;
import android.content.pm.PackageInfo;

import java.util.ArrayList;
import java.util.List;

public class GetInstalledApps
{
  private static Context mContext;

  public GetInstalledApps(Context paramContext)
  {
    mContext = paramContext;
  }

  public static ArrayList<PackageInfo> getInstalledApps(boolean paramBoolean)
  {
    ArrayList localArrayList = new ArrayList();
    List localList = mContext.getApplicationContext().getPackageManager().getInstalledPackages(0);
    for (int i = 0;; i++)
    {
      if (i >= localList.size()) {
        return localArrayList;
      }
      localArrayList.add(localList.get(i));
    }
  }

  public ArrayList<PackageInfo> getPackages()
  {
    return getInstalledApps(false);
  }
}
