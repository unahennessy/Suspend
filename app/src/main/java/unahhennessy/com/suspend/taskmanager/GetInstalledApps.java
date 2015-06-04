package unahhennessy.com.suspend.taskmanager;
/**
 * Created by unahe_000 on 21/05/2015 ${PACKAGE_NAME} Suspend.
 *
 */
import android.content.Context;
import android.content.pm.PackageInfo;

import java.util.ArrayList;
import java.util.List;

public class GetInstalledApps
{ // get all installed Apps on the phone so that I can disallow the non music and nav apps from working
  // i just put them all in an arraylist
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
