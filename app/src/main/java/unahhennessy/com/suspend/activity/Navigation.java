package unahhennessy.com.suspend.activity;
/**
 * Created by unahe_000 on 21/05/2015.
 */
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import unahhennessy.com.suspend.adapter.MusicListAdapter;
import unahhennessy.com.suspend.constants.AppConstants;
import unahhennessy.com.suspend.taskmanager.GetInstalledApps;
import unahhennessy.com.suspend.util.SuspendDbHelper;
import unahhennessy.com.suspend.util.ProjectUtil;
import unahhennessy.com.suspend.R;
import java.util.ArrayList;
public class Navigation
  extends Activity
{
  private MusicListAdapter mAdapter;
  private ArrayList<PackageInfo> mAdapterValue;
  private String[] mAllowedApp = new String[50];
  private RelativeLayout mCompLayout;
  private Handler mHandler = new Handler();
  private TextView mHeader_text;
  private GetInstalledApps mInstalledApp;
  private ListView mListView;
  private ArrayList<PackageInfo> mPackageList;
  private PackageManager mPkgManager;
  private ProgressDialog mProgressDialog;
  private final Runnable mRunnable = new Runnable()
  {
    public void run()
    {
      Navigation.this.updateUI();
    }
  };
  private TextView mUrlText;
  private TextView memptyView;

  private void updateUI()
  {
    try
    {
      this.mProgressDialog.dismiss();
      this.mAdapter = new MusicListAdapter(this, this.mAdapterValue);
      this.mListView.setAdapter(this.mAdapter);
      if ((this.mAdapterValue == null) || (this.mAdapterValue.size() == 0))
      {
        this.mHeader_text.setVisibility(8);
        this.mCompLayout.setVisibility(0);
      }
      AppConstants.SUSPEND_DB.insertLog(ProjectUtil.currentTime() + " Navigation app list to launch - " + this.mAdapterValue, "Info");
      return;
    }
    catch (Exception localException) {}
  }

  private void validateApp()
  {
    this.mAllowedApp = getResources().getStringArray(R.array.navigation_app);
    AppConstants.SUSPEND_DB.insertLog(ProjectUtil.currentTime() + " Allowed navigation apps - " + this.mAllowedApp, "Info");
    this.mAdapterValue = new ArrayList();
    int i = 0;
    if (i >= this.mAllowedApp.length)
    {
      this.mHandler.post(this.mRunnable);
      return;
    }
    for (int j = 0;; j++)
    {
      if (j >= this.mPackageList.size())
      {
        i++;
        break;
      }
      if ((((PackageInfo)this.mPackageList.get(j)).applicationInfo.loadLabel(this.mPkgManager).toString().equalsIgnoreCase(this.mAllowedApp[i])) && (!this.mAdapterValue.contains(this.mPackageList.get(j)))) {
        this.mAdapterValue.add((PackageInfo)this.mPackageList.get(j));
      }
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.navigation);
    AppConstants.MUSIC_NAVIGATION = 2;
    this.mPkgManager = getPackageManager();
    this.mListView = ((ListView)findViewById(R.id.navigation_list));
    this.mHeader_text = ((TextView)findViewById(R.id.header_text));
    this.mUrlText = ((TextView)findViewById(R.id.navigation_url));
    this.mCompLayout = ((RelativeLayout)findViewById(R.id.comp_layout));
    this.mCompLayout.setVisibility(View.VISIBLE);
    this.mInstalledApp = new GetInstalledApps(this);
    this.memptyView = new TextView(this);
    this.memptyView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
    this.memptyView.setText("No App Found.");
    this.memptyView.setTextSize(16.0F);
    this.memptyView.setTextColor(getResources().getColor(R.color.white));
    this.memptyView.setVisibility(View.VISIBLE);
    this.memptyView.setGravity(1);
    ((ViewGroup)this.mListView.getParent()).addView(this.memptyView);
    this.mProgressDialog = ProgressDialog.show(this, null, "Please wait...", true, true);
    try
    {
      new Thread(new Runnable()
      {
        public void run()
        {
          Navigation.this.mPackageList = GetInstalledApps.getInstalledApps(false);
          Navigation.this.validateApp();
        }
      }).start();
      this.mUrlText.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.txtblocker.com/Att/andCompatibleNavigationList.aspx"));
          Navigation.this.startActivity(localIntent);
        }
      });
      return;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        this.mHandler.post(this.mRunnable);
        ProjectUtil.writeErrorLog(this, localException.getMessage());
      }
    }
  }
}
