package unahhennessy.com.suspend.taskmanager;

import android.graphics.drawable.Drawable;

public class PrintInfo
{
  String appname = "";
  String desc = "";
  Drawable icon;
  String pname = "";
  int versionCode = 0;
  String versionName = "";

  void Print()
  {
    System.out.println(this.appname + "\t" + this.pname + "\t" + this.versionName + "\t" + this.versionCode);
  }
}

