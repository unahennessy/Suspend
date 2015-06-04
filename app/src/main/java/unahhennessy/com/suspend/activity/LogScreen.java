package unahhennessy.com.suspend.activity;
/**
 * Created by unahe_000 on 21/05/2015 ${PACKAGE_NAME} Suspend.
 *
 */
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import unahhennessy.com.suspend.R;
import unahhennessy.com.suspend.adapter.LogScreenAdapter;
import unahhennessy.com.suspend.constants.AppConstants;
import unahhennessy.com.suspend.emailclient.GMailSender;
import unahhennessy.com.suspend.util.ProjectUtil;
import unahhennessy.com.suspend.util.SuspendDbHelper;

public class LogScreen   extends Activity
{

//this is a log activity for errors I want errors emailed to myself so I can trace the faults and fix them.
  Runnable dismissDialogWithFailure = new Runnable()
  {
    public void run()
    {
      try
      {
        if (LogScreen.this.mProgressDialog != null) {
          LogScreen.this.mProgressDialog.dismiss();
        }
        Toast.makeText(LogScreen.this, "Logs sending failed.", Toast.LENGTH_LONG).show();
        return;
      }
      catch (Exception localException)
      {
          localException.printStackTrace();
      }
    }
  };
  Runnable dismissDialogWithSuccess = new Runnable()
  {
    public void run()
    {
      try
      {
        if (LogScreen.this.mProgressDialog != null) {
          LogScreen.this.mProgressDialog.dismiss();
        }
        Toast.makeText(LogScreen.this, "Logs sent successfully.", Toast.LENGTH_LONG).show();
        AppConstants.SUSPEND_DB.deleteAllLogs();
        LogScreen.this.logs.clear();
        LogScreen.this.mAdapter.notifyDataSetChanged();
        return;
      }
      catch (Exception localException)
      {
          localException.printStackTrace();
      }
    }
  };
  private List<String> logs;
  private LogScreenAdapter mAdapter;
  private Handler mHandler = new Handler();
  private ListView mList;
  private ProgressDialog mProgressDialog = null;
  private TextView memptyView;

  private void sendLog()
  {
    try
    {
      GMailSender localGMailSender = new GMailSender("unahennessy@gmail.com", "unahennessy");
      String str = "DeviceOS: " + Build.VERSION.RELEASE + ", DeviceModel: " + Build.MODEL + ", DeviceBrand: " + Build.BRAND + ", DeviceHost: " + Build.HOST + ", Application Version: " + ProjectUtil.getAppVersion(this);
      localGMailSender.sendMail("Android Suspend Logs " + ProjectUtil.currentTime(), this.logs.toString() + "\n\n" + str, "unahennessy@gmail.com", "suspend@gmail.com");
      this.mHandler.post(this.dismissDialogWithSuccess);
      return;
    }
    catch (Exception localException)
    {
      this.mHandler.post(this.dismissDialogWithFailure);
      ProjectUtil.writeErrorLog(this, localException.getMessage());
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
        super.onCreate(paramBundle);
        setContentView(R.layout.logscreen);
        this.mList = ((ListView)findViewById(R.id.log_list));
        this.mList.setCacheColorHint(0);
        this.memptyView = new TextView(this);
        this.memptyView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        this.memptyView.setText("No Log Found.");
        this.memptyView.setTextColor(getResources().getColor(R.color.black));
        this.memptyView.setTextSize(18.0F);
        this.memptyView.setTextColor(getResources().getColor(R.color.white));
        this.memptyView.setVisibility(View.VISIBLE);
        this.memptyView.setGravity(1);
        ((ViewGroup)this.mList.getParent()).addView(this.memptyView);
        if (AppConstants.SUSPEND_DB == null)
        {
          AppConstants.SUSPEND_DB = new SuspendDbHelper(this);
        }
        this.logs = AppConstants.SUSPEND_DB.fetchAllLogs();
        this.mAdapter = new LogScreenAdapter(this, this.logs);
        this.mList.setAdapter(this.mAdapter);
        this.mList.setEmptyView(this.memptyView);
  }

      public boolean onCreateOptionsMenu(Menu paramMenu)
      {
        super.onCreateOptionsMenu(paramMenu);
        getMenuInflater().inflate(R.menu.log_menu, paramMenu);
        return true;
      }

      public boolean onOptionsItemSelected(MenuItem paramMenuItem)
      {
        switch (paramMenuItem.getItemId())
        {  default:  return false;
            case 1: {
        this.mProgressDialog = ProgressDialog.show(this, "", "Please wait, Log Sending..");
        new Thread(new Runnable()
        {
          public void run()
          {
            LogScreen.this.sendLog();
          }
        }).start();
        return true;}
      }}
}
