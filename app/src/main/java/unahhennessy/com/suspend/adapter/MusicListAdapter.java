package unahhennessy.com.suspend.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import unahhennessy.com.suspend.R;
import unahhennessy.com.suspend.constants.AppConstants;

public class MusicListAdapter
  extends BaseAdapter
{
  private String mCheckedValue = "";
  private Context mContext;
  private SharedPreferences.Editor mEdit;
  private ArrayList<PackageInfo> mPackageInfoList;
  private PackageManager mPkgManager;
  private String mPkgName;
  private SharedPreferences pref;
  
  public MusicListAdapter(Context paramContext, ArrayList<PackageInfo> paramArrayList)
  {
    this.mContext = paramContext;
    this.mPackageInfoList = paramArrayList;
    this.mPkgManager = this.mContext.getPackageManager();
    this.pref = this.mContext.getSharedPreferences(AppConstants.SUSPEND_PREF, 0);
    this.mEdit = this.pref.edit();
    if (AppConstants.MUSIC_NAVIGATION == 1) {
      this.mCheckedValue = this.pref.getString("music_app", "");
    }
    while (AppConstants.MUSIC_NAVIGATION != 2) {
      return;
    }
    this.mCheckedValue = this.pref.getString("navigation_app", "");
  }
  
  public int getCount()
  {
    if ((this.mPackageInfoList != null) && (this.mPackageInfoList.size() > 0)) {
      return this.mPackageInfoList.size();
    }
    return 0;
  }
  
  public Object getItem(int paramInt)
  {
    if ((this.mPackageInfoList != null) && (this.mPackageInfoList.size() > 0)) {
      return ((PackageInfo)this.mPackageInfoList.get(paramInt)).applicationInfo.loadLabel(this.mPkgManager);
    }
    return null;
  }
  
  public long getItemId(int paramInt)
  {
    return 0L;
  }
  
  public View getView(final int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    View localView = ((LayoutInflater)this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.music_list_items, null);
    ViewHolder localViewHolder = new ViewHolder();
    localViewHolder.imageIcon = ((ImageView)localView.findViewById(R.id.image_icon));
    localViewHolder.appName = ((TextView)localView.findViewWithTag(R.string.app_name));
    localViewHolder.checkBox = ((CheckBox)localView.findViewById(R.id.item_check));
    localView.setTag(localViewHolder);
    localViewHolder.imageIcon.setImageDrawable(((PackageInfo)this.mPackageInfoList.get(paramInt)).applicationInfo.loadIcon(this.mPkgManager));
    localViewHolder.appName.setText(getItem(paramInt).toString());
    localViewHolder.checkBox.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if ((MusicListAdapter.this.mCheckedValue.trim().length() > 0) && (MusicListAdapter.this.mCheckedValue.equalsIgnoreCase(MusicListAdapter.this.getItem(paramInt).toString())))
        {
          MusicListAdapter.this.mCheckedValue = "";
          if (AppConstants.MUSIC_NAVIGATION == 1)
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
        if (AppConstants.MUSIC_NAVIGATION == 1)
        {
          MusicListAdapter.this.mEdit.putString("music_pkg", ((PackageInfo)MusicListAdapter.this.mPackageInfoList.get(paramInt)).packageName);
          MusicListAdapter.this.mEdit.putString("music_app", MusicListAdapter.this.getItem(paramInt).toString());
        }
        for (;;)
        {
          MusicListAdapter.this.mEdit.commit();

          if (AppConstants.MUSIC_NAVIGATION == 2)
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
      localViewHolder.checkBox.setChecked(true);
      return localView;
    }
    localViewHolder.checkBox.setChecked(false);
    return localView;
  }
  
  public void setMPkgName(String paramString)
  {
    this.mPkgName = paramString;
  }
  
  class ViewHolder
  {
    TextView appName;
    CheckBox checkBox;
    ImageView imageIcon;
    
    ViewHolder() {}
  }
}