package unahhennessy.com.suspend.activity;
/**
 * Created by unahe_000 on 21/05/2015.
 */
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import unahhennessy.com.suspend.R;
import unahhennessy.com.suspend.constants.AppConstants;
import unahhennessy.com.suspend.listener.PhoneListener;
import unahhennessy.com.suspend.util.ProjectUtil;


public class SuspendOn  extends Activity
{
  private final int EMERGENCY_DIALOG = 3;
  private final int MUSIC_DIALOG = 1;
  private final int NAVIGATION_DIALOG = 2;
  private AudioManager mAudioManager;
  private ImageView mCross;
  private ImageView mSuspendOff;
  private ImageView mEmergency;
  private ImageView mMusic;
  private ImageView mNavigation;
  private ImageView mPopup;
  private TextView mPopupText;
  private SharedPreferences pref;

  private void SuspendOff()
  {
    try
    {
      SharedPreferences.Editor localEditor = this.pref.edit();
      localEditor.putBoolean("is_suspend_on", false);
      localEditor.commit();
      this.mAudioManager.setRingerMode(2);
      if ( PhoneListener.telephonymanager != null) {
        PhoneListener.removeListener();
      }
      stopService(new Intent(this, PhoneListener.class));
      //stopService(new Intent(this, AppTrackingService.class));

      ProjectUtil.notifyIcon(this);
      return;
    }
    catch (Exception localException)
    {
      ProjectUtil.writeErrorLog(this, localException.getMessage());
    }
  }

  private void SuspendOn()
  {
    try
    {
      removeSavedSms();
      SharedPreferences.Editor localEditor = this.pref.edit();
      localEditor.putBoolean("is_suspend_on", true);
      localEditor.commit();

        // need to check for bluetooth here and if driver has bluetooth then phone can be answered otherwise send a message

      this.mAudioManager.setRingerMode(0);

      for (;;)
      {

        ProjectUtil.notifyIcon(this);
        //startService(new Intent(this, AppTrackingService.class));
        startService(new Intent(this, PhoneListener.class));
        return;
      }

    }
    catch (Exception localException)
    {
      ProjectUtil.writeErrorLog(this, localException.getMessage());
    }
  }

  private void playSound()
  {
    MediaPlayer.create(this, R.raw.beep).start();
  }

  private void removeSavedSms()
  {
    SharedPreferences.Editor localEditor = this.pref.edit();
    localEditor.remove("last_sms_received_no");
    localEditor.remove("last_sms_received_msg");
    localEditor.remove("last_sms_received_time");
    localEditor.commit();
  }

  private void vibrate()
  {
    ((Vibrator)getSystemService(VIBRATOR_SERVICE)).vibrate(600L);
  }

  public boolean dispatchKeyEvent(KeyEvent paramKeyEvent)
  {
    if ((82 == paramKeyEvent.getKeyCode()) || (21 == paramKeyEvent.getKeyCode()) || (20 == paramKeyEvent.getKeyCode()) || (22 == paramKeyEvent.getKeyCode()) || (19 == paramKeyEvent.getKeyCode()) || (23 == paramKeyEvent.getKeyCode())) {
      return false;
    }
      return false;
    }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if ((-1 == paramInt2) && (paramIntent.getExtras().getBoolean("exit")))
    {
      SuspendOff();
      finish();
      System.exit(0);
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.suspendon);
    this.pref = getSharedPreferences(AppConstants.SUSPEND_PREF, 0);
    this.mAudioManager = ((AudioManager)getSystemService(AUDIO_SERVICE));
    this.mMusic = ((ImageView)findViewById(R.id.image_music));
    this.mNavigation = ((ImageView)findViewById(R.id.image_navigation));
    this.mCross = ((ImageView)findViewById(R.id.image_cross));
    this.mPopup = ((ImageView)findViewById(R.id.image_popup));
    this.mSuspendOff = ((ImageView)findViewById(R.id.image_suspend_off));
    this.mEmergency = ((ImageView)findViewById(R.id.image_emergency));
    this.mPopupText = ((TextView)findViewById(R.id.text_popup));
    if (this.pref.getBoolean("is_suspend_on_popup_shown", false))
    {
      this.mCross.setVisibility(View.INVISIBLE);
      this.mPopup.setVisibility(View.INVISIBLE);
      this.mPopupText.setVisibility(View.INVISIBLE);
    }
    this.mEmergency.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        SuspendOn.this.showDialog(3);
      }
    });
    this.mCross.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        SharedPreferences.Editor localEditor = SuspendOn.this.pref.edit();
        localEditor.putBoolean("is_suspend_on_popup_shown", true);
        localEditor.commit();
        SuspendOn.this.mCross.setVisibility(View.INVISIBLE);
        SuspendOn.this.mPopup.setVisibility(View.INVISIBLE);
        SuspendOn.this.mPopupText.setVisibility(View.INVISIBLE);
      }
    });
    this.mSuspendOff.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        SuspendOn.this.vibrate();
        SuspendOn.this.SuspendOff();
        SuspendOn.this.startActivity(new Intent(SuspendOn.this, SuspendOff.class));
        SuspendOn.this.finish();
      }
    });
    this.mMusic.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        String str = SuspendOn.this.pref.getString("music_pkg", "");
        if ((str != null) && (str.length() > 0))
        {
          ProjectUtil.launchApp(SuspendOn.this, str);
          return;
        }
        SuspendOn.this.showDialog(1);
      }
    });
    this.mNavigation.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        String str = SuspendOn.this.pref.getString("navigation_pkg", "");
        if ((str != null) && (str.length() > 0))
        {
          ProjectUtil.launchApp(SuspendOn.this, str);
          return;
        }
        SuspendOn.this.showDialog(2);
      }
    });
  }

  protected Dialog onCreateDialog(int paramInt) {
      AlertDialog localAlertDialog1 = null;
      if (paramInt == 1) {
          View localView3 = LayoutInflater.from(this).inflate(R.layout.music_dialog, null);
          Button localButton5 = (Button) localView3.findViewById(R.id.button_cancel);
          Button localButton6 = (Button) localView3.findViewById(R.id.button_off);
          localButton5.setOnClickListener(new View.OnClickListener() {
              public void onClick(View paramAnonymousView) {
                  SuspendOn.this.removeDialog(1);
              }
          });
          localButton6.setOnClickListener(new View.OnClickListener() {
              public void onClick(View paramAnonymousView) {
                  SuspendOn.this.removeDialog(1);
                  SuspendOn.this.vibrate();
                  SuspendOn.this.SuspendOff();
                  SuspendOn.this.startActivity(new Intent(SuspendOn.this, SuspendOff.class));
                  SuspendOn.this.finish();
              }
          });
          localAlertDialog1 = new AlertDialog.Builder(this).create();
          localAlertDialog1.setView(localView3);
      } else if (paramInt == 2) {


          View localView2 = LayoutInflater.from(this).inflate(R.layout.navigation_dialog, null);
          Button localButton3 = (Button) localView2.findViewById(R.id.button_cancel);
          Button localButton4 = (Button) localView2.findViewById(R.id.button_off);
          localButton3.setOnClickListener(new View.OnClickListener() {
              public void onClick(View paramAnonymousView) {
                  SuspendOn.this.removeDialog(2);
              }
          });
          localButton4.setOnClickListener(new View.OnClickListener() {
              public void onClick(View paramAnonymousView) {
                  SuspendOn.this.removeDialog(2);
                  SuspendOn.this.vibrate();
                  SuspendOn.this.SuspendOff();
                  SuspendOn.this.startActivity(new Intent(SuspendOn.this, SuspendOff.class));
                  SuspendOn.this.finish();
              }
          });
          AlertDialog localAlertDialog3 = new AlertDialog.Builder(this).create();
          localAlertDialog3.setView(localView2);
          return localAlertDialog3;


      } else {
          View localView1 = LayoutInflater.from(this).inflate(R.layout.emergency_dialog, null);
          Button localButton1 = (Button) localView1.findViewById(R.id.button_No);
          Button localButton2 = (Button) localView1.findViewById(R.id.button_yes);
          localButton1.setOnClickListener(new View.OnClickListener() {
              public void onClick(View paramAnonymousView) {
                  SuspendOn.this.removeDialog(3);
              }
          });
          localButton2.setOnClickListener(new View.OnClickListener() {
              public void onClick(View paramAnonymousView) {
                  SuspendOn.this.SuspendOff();
                  Intent localIntent = new Intent("android.intent.action.CALL");
                  localIntent.addFlags(268435456);
                  localIntent.setData(Uri.parse("tel:112"));
                  SuspendOn.this.startActivity(localIntent);
                  SuspendOn.this.removeDialog(3);
                  System.exit(0);
              }
          });

      }
      AlertDialog localAlertDialog2 = new AlertDialog.Builder(this).create();
      localAlertDialog2.setView(LayoutInflater.from(this).inflate(R.layout.emergency_dialog, null));

      return localAlertDialog2;
  }

  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    super.onCreateOptionsMenu(paramMenu);
    getMenuInflater().inflate(R.menu.on_options, paramMenu);
    return true;
  }

  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    switch (paramMenuItem.getItemId())
    {
    default:
      return false;
    case R.layout.help:
      startActivity(new Intent(this, Help.class));
      return true;
    }

  }
}
