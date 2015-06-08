package unahhennessy.com.suspend.adapter;
/**
 * Created by unahe_000 on 01/06/2015.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

import unahhennessy.com.suspend.R;
import unahhennessy.com.suspend.factors.FactorsInThisApp;

public class MusicListAdapter extends BaseAdapter
{
  private String mCheckedValue = "";
  private Context mContext;
  private SharedPreferences.Editor mEdit;
  private ArrayList<PackageInfo> mPackageInfoList;
  private PackageManager mPkgManager;
  private String mPkgName;
  private SharedPreferences pref;
  private static final String TAG = "MusicListAdapter";
  
  public MusicListAdapter(Context paramContext, ArrayList<PackageInfo> paramArrayList)
  {
    // this adapter finds all the music apps on a persons phone and lists them for them to pick one out to be used while they are driving
    //
    this.log("entered MusicListAdapter() within MusicListAdapter.java");
    this.mContext = paramContext;
    this.mPackageInfoList = paramArrayList;
    this.mPkgManager = this.mContext.getPackageManager();
    this.pref = this.mContext.getSharedPreferences(FactorsInThisApp.mSUSPEND_PREF, 0);
    this.mEdit = this.pref.edit();
    if (FactorsInThisApp.mMUSIC_NAVIGATION == 1) {
      this.mCheckedValue = this.pref.getString("music_app", "");
    }
    while (FactorsInThisApp.mMUSIC_NAVIGATION != 2) {
      return;
    }
    this.mCheckedValue = this.pref.getString("navigation_app", "");
  }
  
  public int getCount()
  {
    this.log("entered getCount() within MusicListAdapter.java");
    if ((this.mPackageInfoList != null) && (this.mPackageInfoList.size() > 0)) {
      return this.mPackageInfoList.size();
    }
    return 0;
  }
  
  public Object getItem(int paramInt)
  {
      this.log("entered getItem() within MusicListAdapter.java");
    if ((this.mPackageInfoList != null) && (this.mPackageInfoList.size() > 0)) {
      return ((PackageInfo)this.mPackageInfoList.get(paramInt)).applicationInfo.loadLabel(this.mPkgManager);
    }
    return null;
  }
  
  public long getItemId(int paramInt)
  {
      this.log("entered getItemId() within MusicListAdapter.java");
    return 0L;
  }
  
  public View getView(final int paramInt, View paramView, ViewGroup paramViewGroup)
  {
      this.log("entered getView() within MusicListAdapter.java");
    View mView = ((LayoutInflater)this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.music_list_items, null);
    ViewHolder mViewHolder = new ViewHolder();
    mViewHolder.imageIcon = ((ImageView)mView.findViewById(R.id.image_icon));
    mViewHolder.appName = ((TextView)mView.findViewWithTag(R.string.app_name));
    mViewHolder.checkBox = ((CheckBox)mView.findViewById(R.id.item_check));
    mView.setTag(mViewHolder);
    mViewHolder.imageIcon.setImageDrawable(((PackageInfo)this.mPackageInfoList.get(paramInt)).applicationInfo.loadIcon(this.mPkgManager));
    mViewHolder.appName.setText(getItem(paramInt).toString());
    mViewHolder.checkBox.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if ((MusicListAdapter.this.mCheckedValue.trim().length() > 0) && (MusicListAdapter.this.mCheckedValue.equalsIgnoreCase(MusicListAdapter.this.getItem(paramInt).toString())))
        {
          MusicListAdapter.this.mCheckedValue = "";
          if (FactorsInThisApp.mMUSIC_NAVIGATION == 1)
          {
            MusicListAdapter.this.mEdit.remove("music_pkg");
            MusicListAdapter.this.mEdit.remove("music_app");
          }
          for (;;)
          {
            MusicListAdapter.this.mEdit.commit();
            MusicListAdapter.this.notifyDataSetChanged();
            return;
            }
        }
        MusicListAdapter.this.mCheckedValue = MusicListAdapter.this.getItem(paramInt).toString().trim();
        if (FactorsInThisApp.mMUSIC_NAVIGATION == 1)
        {
          MusicListAdapter.this.mEdit.putString("music_pkg", (MusicListAdapter.this.mPackageInfoList.get(paramInt)).packageName);
          MusicListAdapter.this.mEdit.putString("music_app", MusicListAdapter.this.getItem(paramInt).toString());
        }
        for (;;)
        {
          MusicListAdapter.this.mEdit.commit();

          if (FactorsInThisApp.mMUSIC_NAVIGATION == 2)
          {
            String str = ((PackageInfo)MusicListAdapter.this.mPackageInfoList.get(paramInt)).packageName;
            if (str.equalsIgnoreCase("com.google.android.street")) {
              str = "com.google.android.apps.maps";
            }
            MusicListAdapter.this.mEdit.putString("navigation_pkg", str);
            MusicListAdapter.this.mEdit.putString("navigation_app", MusicListAdapter.this.getItem(paramInt).toString());
          }
        }
      }
    });
    if ((this.mCheckedValue.trim().length() > 0) && (this.mCheckedValue.equalsIgnoreCase(getItem(paramInt).toString())))
    {
      mViewHolder.checkBox.setChecked(true);
      return mView;
    }
    mViewHolder.checkBox.setChecked(false);
    return mView;
  }
  
  public void setMPkgName(String paramString)
  {
      this.log("entered setMPkgName() within MusicListAdapter.java");

      this.mPkgName = paramString;
  }
  
  class ViewHolder
  {
    TextView appName;
    CheckBox checkBox;
    ImageView imageIcon;
    
    ViewHolder() {}
  }
  private void log(String msg)
  {
    try {
      Thread.sleep(500);
    }
    catch (InterruptedException e) {
      e.printStackTrace();
    }
    Log.i(MusicListAdapter.TAG, msg);

  }
  
  
  
  
  
}