package unahhennessy.com.suspend.taskmanager;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import java.util.Iterator;
import java.util.List;

public class PackagesInfo
{
  private List<ApplicationInfo> appList;

  public PackagesInfo(Context paramContext)
  {
    this.appList = paramContext.getApplicationContext().getPackageManager().getInstalledApplications(8192);
  }

  public ApplicationInfo getInfo(String paramString)
  {
    if (paramString == null) {
      return null;
    }
    Iterator localIterator = this.appList.iterator();
    ApplicationInfo localApplicationInfo;
    do
    {
      if (!localIterator.hasNext()) {
        return null;
      }
      localApplicationInfo = (ApplicationInfo)localIterator.next();
    } while (!paramString.equals(localApplicationInfo.processName));
    return localApplicationInfo;
  }
}
