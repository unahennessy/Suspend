package unahhennessy.com.suspend.activity;
/**
 * Created by unahe_000 on 21/05/2015.
 */
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;
import unahhennessy.com.suspend.R;
import unahhennessy.com.suspend.adapter.MusicListAdapter;
import unahhennessy.com.suspend.constants.AppConstants;
import unahhennessy.com.suspend.taskmanager.GetInstalledApps;
import unahhennessy.com.suspend.util.ProjectUtil;

public class Music extends Activity
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
      Music.this.updateUI();
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
        this.mHeader_text.setVisibility(View.VISIBLE);
        this.mCompLayout.setVisibility(View.INVISIBLE);
      }
      AppConstants.SUSPEND_DB.insertLog(ProjectUtil.currentTime() + " Music app list to launch - " + this.mAdapterValue, "Info");
      return;
    }
    catch (Exception localException) {}
  }

  private void validateApp() {
    this.mAllowedApp = getResources().getStringArray(R.array.music_app);
    int i;
    if (Build.MODEL.trim().equalsIgnoreCase(AppConstants.MUSIC_APP)) {
      i = 1;
      AppConstants.SUSPEND_DB.insertLog(ProjectUtil.currentTime() + " Allowed music apps - " + this.mAllowedApp, "Info");
      this.mAdapterValue = new ArrayList();
    }
    int k;
    for (int j = 0; ; j++) {
      if (j >= this.mAllowedApp.length) {
        this.mHandler.post(this.mRunnable);
        i = 0;
        return;


      }
      k = 0;
      if (k < this.mPackageList.size()) {
        break;// label130;
      }
    }


    /*label130:
    String string = ((PackageInfo)this.mPackageList.get(k)).applicationInfo.loadLabel(this.mPkgManager).toString();
    if ((string.equalsIgnoreCase(this.mAllowedApp[j])) && (!this.mAdapterValue.contains(this.mPackageList.get(k))))
    {
      if (!string.trim().equalsIgnoreCase("Music")) {
        break label236;
      }
      if (i == 0) {
        this.mAdapterValue.add((PackageInfo)this.mPackageList.get(k));
      }
    }
    for (;;)
    {
      k++;
      break;
      label236:
      this.mAdapterValue.add((PackageInfo)this.mPackageList.get(k));
    }*/
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.music_list_items);
    AppConstants.MUSIC_NAVIGATION = 1;
    this.mPkgManager = getPackageManager();
    this.mListView = ((ListView)findViewById(R.id.list_item));
    this.mHeader_text = ((TextView)findViewById(R.id.header_text));
    this.mUrlText = ((TextView)findViewById(R.id.url));
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
          Music.this.mPackageList = GetInstalledApps.getInstalledApps(false);
          Music.this.validateApp();
        }
      }).start();
      this.mUrlText.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.google.com"));
          Music.this.startActivity(localIntent);
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
