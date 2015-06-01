package unahhennessy.com.suspend.activity;
/**
 * Created by unahe_000 on 21/05/2015.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import unahhennessy.com.suspend.R;
import unahhennessy.com.suspend.constants.AppConstants;
import unahhennessy.com.suspend.util.ProjectUtil;
public class SuspendOff
  extends Activity {
  private final int ALERT = 2;
  private final int EMERGENCY = 1;
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
  private SharedPreferences pref;

  private void activate() {
    try {
      Intent localIntent = new Intent(this, SuspendOn.class);
      localIntent.addFlags(67108864);
      startActivity(localIntent);
      return;
    } catch (Exception localException) {
      ProjectUtil.writeErrorLog(this, localException.getMessage());
    }
  }

  private void handlePopup() {
    this.mCross.setVisibility(View.GONE);
    this.mPopup.setVisibility(View.GONE);
    this.mPopupText.setVisibility(View.GONE);
    SharedPreferences.Editor localEditor = this.pref.edit();
    localEditor.putBoolean("is_suspend_off_popup_shown", true);
    localEditor.commit();
  }

  private void setAppIcon() {
    this.mMusicApp = this.pref.getString("music_pkg", "");
    this.mNavigationApp = this.pref.getString("navigation_pkg", "");
    if ((this.mMusicApp == null) || (this.mMusicApp.trim().length() == 0)) {
      this.mMusic.setBackgroundResource(R.drawable.musicdisablesuspend);
    }
    if ((this.mNavigationApp == null) || (this.mNavigationApp.trim().length() == 0)) {
      this.mNavigation.setBackgroundResource(R.drawable.navigationdisablesuspend);
    }
    this.mMusic.setBackgroundResource(R.drawable.image_music_selector);
    this.mNavigation.setBackgroundResource(R.drawable.image_navigation_selector);
    return;
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent) {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if ((paramInt2 == -1) && (paramIntent.getExtras().getBoolean("emergency"))) {
      finish();
    }
  }

  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    setContentView(R.layout.suspendoff);

    this.mPkgManager = getPackageManager();
    this.pref = getSharedPreferences(AppConstants.SUSPEND_PREF, 0);
    this.mMusic = ((ImageView) findViewById(R.id.image_music));
    this.mNavigation = ((ImageView) findViewById(R.id.image_navigation));
    this.mEmergency = ((ImageView) findViewById(R.id.image_emergency));
    this.mSetting = ((ImageView) findViewById(R.id.image_setting));
    this.mCross = ((ImageView) findViewById(R.id.image_cross));
    this.mPopup = ((ImageView) findViewById(R.id.image_popup));
    this.mSuspendOn = ((ImageView) findViewById(R.id.image_suspend_on));
    this.mPopupText = ((TextView) findViewById(R.id.text_popup));
    if (this.pref.getBoolean("is_suspend_on", false)) {
      activate();
      finish();
    }
    if (!this.pref.getBoolean("is_suspend_off_popup_shown", false)) {
      new CountDownTimer(4000L, 500L) {
        public void onFinish() {
          SuspendOff.this.handlePopup();
        }

        public void onTick(long paramAnonymousLong) {
        }
      }.start();
    }
    for (; ; ) {
      this.mEmergency.setOnClickListener(new View.OnClickListener() {
        public void onClick(View paramAnonymousView) {
          SuspendOff.this.onCreateDialog(1);
        }
      });
      this.mSetting.setOnClickListener(new View.OnClickListener() {
        public void onClick(View paramAnonymousView) {
          SuspendOff.this.startActivity(new Intent(SuspendOff.this, Settings.class));
        }
      });
      this.mCross.setOnClickListener(new View.OnClickListener() {
        public void onClick(View paramAnonymousView) {
          SuspendOff.this.handlePopup();
        }
      });
      this.mSuspendOn.setOnClickListener(new View.OnClickListener() {
        public void onClick(View paramAnonymousView) {
          SuspendOff.this.isSMS = SuspendOff.this.pref.getBoolean("is_sms_enabled", false);
          SuspendOff.this.isMMS = SuspendOff.this.pref.getBoolean("is_mms_enabled", false);
          SuspendOff.this.isWHATSAPP = SuspendOff.this.pref.getBoolean("is_whatsapp_enabled", false);
          SuspendOff.this.isPhone = SuspendOff.this.pref.getBoolean("is_phone_enabled", false);
          if ((!SuspendOff.this.isSMS) && (!SuspendOff.this.isMMS) && (!SuspendOff.this.isPhone)) {
            SuspendOff.this.onCreateDialog(2);
            return;
          }
          SuspendOff.this.activate();
          SuspendOff.this.finish();
        }
      });

      this.mMusic.setOnClickListener(new View.OnClickListener() {
        public void onClick(View paramAnonymousView) {
          if ((SuspendOff.this.mMusicApp != null) && (SuspendOff.this.mMusicApp.length() > 0)) {
            ProjectUtil.launchApp(SuspendOff.this, SuspendOff.this.mMusicApp);
          }
        }
      });
      this.mNavigation.setOnClickListener(new View.OnClickListener() {
        public void onClick(View paramAnonymousView) {
          if ((SuspendOff.this.mNavigationApp != null) && (SuspendOff.this.mNavigationApp.length() > 0)) {
            ProjectUtil.launchApp(SuspendOff.this, SuspendOff.this.mNavigationApp);
          }
        }
      });
      this.isSMS = this.pref.getBoolean("is_sms_enabled", false);
      this.isMMS = this.pref.getBoolean("is_mms_enabled", false);
      this.isWHATSAPP = this.pref.getBoolean("is_whatsapp_enabled", false);
      this.isPhone = this.pref.getBoolean("is_phone_enabled", false);
      if ((!this.isSMS) && (!this.isMMS) && (!this.isWHATSAPP) && (!this.isPhone)) {
        onCreateDialog(2);
      }
      this.mCross.setVisibility(View.INVISIBLE);
      this.mPopup.setVisibility(View.INVISIBLE);
      this.mPopupText.setVisibility(View.INVISIBLE);
      return;

    }
  }

  protected Fragment fragmentCreateDialog(int paramInt)

  {
    AlertDialog myalert = new AlertDialog.Builder(this).create();
    if (paramInt == 1)
    { myalert.findViewById(R.id.option1_layout);

      Button button2 = (Button)findViewById(R.id.button_No);
      Button button3 = (Button)findViewById(R.id.button_yes);
      button2.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {


          SuspendOff.this.removeDialog(1);
        }
      });
     button3.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          Intent localIntent = new Intent("android.intent.action.CALL");
          localIntent.addFlags(268435456);
          localIntent.setData(Uri.parse("tel:112"));
          SuspendOff.this.startActivity(localIntent);
          SuspendOff.this.removeDialog(1);
          System.exit(0);
        }
      });
      myalert.setView(myalert.findViewById(R.id.option1_layout));
    }
    myalert.setView(myalert.findViewById(R.id.option1_layout));

    return  fragmentCreateDialog(1);
  }

  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    super.onCreateOptionsMenu(paramMenu);
    getMenuInflater().inflate(R.menu.off_options, paramMenu);
    return true;
  }

  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    switch (paramMenuItem.getItemId())
    {
    default:
      return false;
    case R.id.help:
      startActivity(new Intent(this, Help.class));
      return true;
    case R.id.about:
      startActivity(new Intent(this, About.class));
      return true;
    }


  }

  protected void onResume()
  {
    super.onResume();
    setAppIcon();
  }
}
