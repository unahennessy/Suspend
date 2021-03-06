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
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;
import unahhennessy.com.suspend.R;
import unahhennessy.com.suspend.adapter.MusicListAdapter;
import unahhennessy.com.suspend.factors.FactorsInThisApp;
import unahhennessy.com.suspend.other.NotificationStopOtherApps;
import unahhennessy.com.suspend.other.GetInstalledApps;

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
  private static final String TAG = "Music Activity";

  private void updateUI()
      {
          this.log("entered updateUI() within Music.java");
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
          FactorsInThisApp.mSUSPEND_DB.insertLog(NotificationStopOtherApps.currentTime() + " Music app list to launch - " + this.mAdapterValue, "Info");
          return;
        }
        catch (Exception localException) {}
      }

  private void validateApp() {
      this.log("entered validateApp() within Music.java");
    this.mAllowedApp = getResources().getStringArray(R.array.music_app);
    int i;
    if (Build.MODEL.trim().equalsIgnoreCase(FactorsInThisApp.mMUSIC_APP)) {
      i = 1;
      FactorsInThisApp.mSUSPEND_DB.insertLog(NotificationStopOtherApps.currentTime() + " Allowed music apps - " + this.mAllowedApp, "Info");
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
        break;
      }
    }

  }

  protected void onCreate(Bundle paramBundle)
  {
      this.log("entered onCreate() within Music.java");
    super.onCreate(paramBundle);
    setContentView(R.layout.music_list_items);
    FactorsInThisApp.mMUSIC_NAVIGATION = 1;
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
        NotificationStopOtherApps.writeErrorLog(this, localException.getMessage());
      }
    }
  }
  private void log(String msg)
  {
    try {
      Thread.sleep(500);
    }
    catch (InterruptedException e) {
      e.printStackTrace();
    }
    Log.i(TAG, msg);

  }
}
