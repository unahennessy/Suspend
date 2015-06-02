package unahhennessy.com.suspend.activity;
/**
 * Created by unahe_000 on 21/05/2015.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import unahhennessy.com.suspend.R;
import unahhennessy.com.suspend.R.drawable;
import unahhennessy.com.suspend.R.id;
import unahhennessy.com.suspend.R.layout;
import unahhennessy.com.suspend.R.menu;
import unahhennessy.com.suspend.constants.AppConstants;
import unahhennessy.com.suspend.util.ProjectUtil;
public class SuspendOff   extends Activity {
   // declare and initialize variables

  private boolean isWHATSAPP;
  private boolean isMMS;
  private boolean isPhone;
  private boolean isSMS;
  private ImageView mCross;
  private ImageView mSuspendOn;
  private ImageView mEmergency;
  private ImageView mMusic;
  private String mMusicApp = "";
  private ImageView mNavigation;
  private String mNavigationApp = "";
  private PackageManager mPkgManager;
  private ImageView mPopup;
  private TextView mPopupText;
  private ImageView mSetting;
  private int mParamInt;
  private SharedPreferences pref;
  private static final String TAG = "SuspendOff";

  private void activate()
  {
      this.log("entered activate() within SuspendOff.java");
    try
    {
      Intent localIntent = new Intent(this, SuspendOn.class);
      localIntent.addFlags(67108864);
        this.startActivity(localIntent);
      return;
    } catch (Exception localException) {
      ProjectUtil.writeErrorLog(this, localException.getMessage());
    }
  }

  private void handlePopup() {
      this.log("entered handlePopup() within SuspendOff.java");
      mCross.setVisibility(View.GONE);
      mPopup.setVisibility(View.GONE);
      mPopupText.setVisibility(View.GONE);
    Editor localEditor = pref.edit();
    localEditor.putBoolean("is_suspend_off_popup_shown", true);
    localEditor.commit();
  }

  private void setAppIcon() {
      this.log("entered setAppIcon() within SuspendOff.java");
      mMusicApp = pref.getString("music_pkg", "");
      mNavigationApp = pref.getString("navigation_pkg", "");
    if (mMusicApp == null || mMusicApp.trim().length() == 0) {
        mMusic.setBackgroundResource(drawable.musicdisablesuspend);
    }
    if (mNavigationApp == null || mNavigationApp.trim().length() == 0) {
        mNavigation.setBackgroundResource(drawable.navigationdisablesuspend);
    }
      mMusic.setBackgroundResource(drawable.image_music_selector);
      mNavigation.setBackgroundResource(drawable.image_navigation_selector);
    return;
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent) {
      this.log("entered onActivityResult() within SuspendOff.java");
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt2 == -1 && paramIntent.getExtras().getBoolean("emergency")) {
        this.finish();
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
      this.log("entered onCreate() within SuspendOff.java");
      super.onCreate(paramBundle);
      this.setContentView(layout.suspendoff);

      mPkgManager = this.getPackageManager();
      pref = this.getSharedPreferences(AppConstants.SUSPEND_PREF, 0);
      mMusic = (ImageView) this.findViewById(id.image_music);
      mNavigation = (ImageView) this.findViewById(id.image_navigation);
      mEmergency = (ImageView) this.findViewById(id.image_emergency);
      mSetting = (ImageView) this.findViewById(id.image_setting);
      mCross = (ImageView) this.findViewById(id.image_cross);
      mPopup = (ImageView) this.findViewById(id.image_popup);
      mSuspendOn = (ImageView) this.findViewById(id.image_suspend_off_clickable);
      mPopupText = (TextView) this.findViewById(id.text_popup);
      if (pref.getBoolean("is_suspend_on", false))
      {
          this.activate();
          this.finish();
      }
      if (!pref.getBoolean("is_suspend_off_popup_shown", false))
      {
      new CountDownTimer(4000L, 500L)
        {
         public void onFinish()
         {
             SuspendOff.this.log("entered onFinish() within SuspendOff.java");
             handlePopup();
         }

        public void onTick(long paramAnonymousLong)
         {
             SuspendOff.this.log("entered onTick() within SuspendOff.java");
         }
        }.start();
      }
    for (; ; )
    {
        mEmergency.setOnClickListener(new OnClickListener() {
            public void onClick(View paramAnonymousView) {
                SuspendOff.this.log("entered mEmergency.setOnClickListener() within SuspendOff.java");
                SuspendOff.this.mParamInt = 1;
            }
        });
        mSetting.setOnClickListener(new OnClickListener() {
            public void onClick(View paramAnonymousView) {
                SuspendOff.this.log("entered mSetting.setOnClickListener() within SuspendOff.java");
                startActivity(new Intent(SuspendOff.this, Settings.class));
            }
        });
        mCross.setOnClickListener(new OnClickListener() {
            public void onClick(View paramAnonymousView) {
                SuspendOff.this.log("entered mCross.setOnClickListener() within SuspendOff.java");
                handlePopup();
            }
        });

        mSuspendOn.setOnClickListener(new OnClickListener() {
            public void onClick(View paramAnonymousView) {
                isSMS = pref.getBoolean("is_sms_enabled", false);
                isMMS = pref.getBoolean("is_mms_enabled", false);
                isWHATSAPP = pref.getBoolean("is_whatsapp_enabled", false);
                isPhone = pref.getBoolean("is_phone_enabled", false);
                if (!isSMS && !isMMS && !isWHATSAPP && !isPhone) {
                    SuspendOff.this.mParamInt = 2;
                    SuspendOff.this.log("entered onClick mParamInt set to 2 (1st) within SuspendOff.java");
                    return;
                }
                activate();
                finish();
            }
        });

        mMusic.setOnClickListener(new OnClickListener() {
            public void onClick(View paramAnonymousView) {
                if (mMusicApp != null && mMusicApp.length() > 0) {
                    ProjectUtil.launchApp(SuspendOff.this, mMusicApp);
                }
            }
        });
        mNavigation.setOnClickListener(new OnClickListener() {
            public void onClick(View paramAnonymousView) {
                if (mNavigationApp != null && mNavigationApp.length() > 0) {
                    ProjectUtil.launchApp(SuspendOff.this, mNavigationApp);
                }
            }
        });
        isSMS = pref.getBoolean("is_sms_enabled", false);
        isMMS = pref.getBoolean("is_mms_enabled", false);
        isWHATSAPP = pref.getBoolean("is_whatsapp_enabled", false);
        isPhone = pref.getBoolean("is_phone_enabled", false);
        if (!isSMS && !isMMS && !isWHATSAPP && !isPhone)
          {
              this.log("entered where mParamInt is set to 2 within SuspendOff.java");
              this.mParamInt = 2;
          }
        mCross.setVisibility(View.INVISIBLE);
        mPopup.setVisibility(View.INVISIBLE);
        mPopupText.setVisibility(View.INVISIBLE);
      }
  }

  protected Fragment fragmentCreateDialog(int mParamInt)

  {
      this.log("entered fragmentCreateDialog() within SuspendOff.java");
    AlertDialog myalert = new Builder(this).create();
      // mParamInt == 1 driver wants to may Emergency call
    if ( mParamInt == 1)
        { myalert.findViewById(id.option1_layout);

          Button button2 = (Button) this.findViewById(id.button_No);
          Button button3 = (Button) this.findViewById(id.button_yes);
          button2.setOnClickListener(new OnClickListener()
          {
            public void onClick(View paramAnonymousView)
            {
                SuspendOff.this.fragmentCreateDialog(1).isDetached();
            }
          });
         button3.setOnClickListener(new OnClickListener()
          {
            public void onClick(View paramAnonymousView)
            {
              Intent localIntent = new Intent("android.intent.action.CALL");
              localIntent.addFlags(268435456);
              localIntent.setData(Uri.parse("tel:112"));
                startActivity(localIntent);
                SuspendOff.this.fragmentCreateDialog(1).isDetached();
              System.exit(0);
            }
          });
          myalert.setView(myalert.findViewById(id.option1_layout));
        }

    myalert.setView(myalert.findViewById(id.option1_layout));
    return this.fragmentCreateDialog(1);
  }

  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
      this.log("entered onCreateOptionsMenu() within SuspendOff.java");
    super.onCreateOptionsMenu(paramMenu);
      this.getMenuInflater().inflate(menu.off_options, paramMenu);
    return true;
  }

  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
      this.log("entered onOptionsItemSelected() within SuspendOff.java");
    switch (paramMenuItem.getItemId())
    {
    default:
      return false;
    case id.help:
        this.startActivity(new Intent(this, Help.class));
      return true;
    case id.about:
        this.startActivity(new Intent(this, About.class));
      return true;
    }
  }

  protected void onResume()
  {
      this.log("entered onResume() within SuspendOff.java");
    super.onResume();
      this.setAppIcon();
  }
    private void log(String msg)
    {
        try {
            Thread.sleep(500);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i(SuspendOff.TAG, msg);

    }
}
