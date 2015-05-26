package unahhennessy.com.suspend.taskmanager;

import android.content.Context;
import android.content.pm.PackageInfo;

import java.util.ArrayList;
import java.util.List;

public class GetInstalledApps
{
  private static Context ctx;

  public GetInstalledApps(Context paramContext)
  {
    ctx = paramContext;
  }

  public static ArrayList<PackageInfo> getInstalledApps(boolean paramBoolean)
  {
    ArrayList localArrayList = new ArrayList();
    List localList = ctx.getApplicationContext().getPackageManager().getInstalledPackages(0);
    for (int i = 0;; i++)
    {
      if (i >= localList.size()) {
        return localArrayList;
      }
      localArrayList.add((PackageInfo)localList.get(i));
    }
  }

  public ArrayList<PackageInfo> getPackages()
  {
    return getInstalledApps(false);
  }
}
