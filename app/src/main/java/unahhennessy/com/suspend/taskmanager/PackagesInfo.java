package unahhennessy.com.suspend.taskmanager;
/**
 * Created by unahe_000 on 21/05/2015 ${PACKAGE_NAME} Suspend.
 *
 */

import android.content.Context;
import android.content.pm.ApplicationInfo;
import java.util.Iterator;
import java.util.List;

public class PackagesInfo
{
  private List<ApplicationInfo> appList;

  public PackagesInfo(Context paramContext)
  {
    //this gets all the apps on the drivers phone including deleted apps using flag 8192which is  GET_UNINSTALLED_PACKAGES
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
