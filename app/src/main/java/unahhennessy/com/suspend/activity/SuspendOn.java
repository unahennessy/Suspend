package unahhennessy.com.suspend.activity;
/**
 * Created by unahe_000 on 21/05/2015.
 */
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
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
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import unahhennessy.com.suspend.R;
import unahhennessy.com.suspend.R.id;
import unahhennessy.com.suspend.R.layout;
import unahhennessy.com.suspend.R.menu;
import unahhennessy.com.suspend.R.raw;
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
      Editor localEditor = pref.edit();
      localEditor.putBoolean("is_suspend_on", false);
      localEditor.commit();
        mAudioManager.setRingerMode(2);
      if ( PhoneListener.telephonymanager != null) {
        PhoneListener.removeListener();
      }
        this.stopService(new Intent(this, PhoneListener.class));
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
        this.removeSavedSms();
      Editor localEditor = pref.edit();
      localEditor.putBoolean("is_suspend_on", true);
      localEditor.commit();

        // need to check for bluetooth here and if driver has bluetooth then phone can be answered otherwise send a message

        mAudioManager.setRingerMode(0);

      for (;;)
      {

        ProjectUtil.notifyIcon(this);
        //startService(new Intent(this, AppTrackingService.class));
          this.startService(new Intent(this, PhoneListener.class));
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
    MediaPlayer.create(this, raw.beep).start();
  }

  private void removeSavedSms()
  {
    Editor localEditor = pref.edit();
    localEditor.remove("last_sms_received_no");
    localEditor.remove("last_sms_received_msg");
    localEditor.remove("last_sms_received_time");
    localEditor.commit();
  }

  private void vibrate()
  {
    ((Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE)).vibrate(600L);
  }

  public boolean dispatchKeyEvent(KeyEvent paramKeyEvent)
  {
    if (82 == paramKeyEvent.getKeyCode() || 21 == paramKeyEvent.getKeyCode() || 20 == paramKeyEvent.getKeyCode() || 22 == paramKeyEvent.getKeyCode() || 19 == paramKeyEvent.getKeyCode() || 23 == paramKeyEvent.getKeyCode()) {
      return false;
    }
      return false;
    }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (-1 == paramInt2 && paramIntent.getExtras().getBoolean("exit"))
    {
        this.SuspendOff();
        this.finish();
      System.exit(0);
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
      this.setContentView(layout.suspendon);
      pref = this.getSharedPreferences(AppConstants.SUSPEND_PREF, 0);
      mAudioManager = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
      this.SuspendOn();
      mMusic = (ImageView) this.findViewById(R.id.image_music);
      mNavigation = (ImageView) this.findViewById(R.id.image_navigation);
      mCross = (ImageView) this.findViewById(R.id.image_cross);
      mPopup = (ImageView) this.findViewById(R.id.image_popup);
      mSuspendOff = (ImageView) this.findViewById(R.id.image_suspend_off);
      mEmergency = (ImageView) this.findViewById(R.id.image_emergency);
      mPopupText = (TextView) this.findViewById(R.id.text_popup);
    if (pref.getBoolean("is_suspend_on_popup_shown", false))
    {
        mCross.setVisibility(View.INVISIBLE);
        mPopup.setVisibility(View.INVISIBLE);
        mPopupText.setVisibility(View.INVISIBLE);
    }
      mEmergency.setOnClickListener(new OnClickListener() {
          public void onClick(View paramAnonymousView) {
              showDialog(3);
          }
      });
      mCross.setOnClickListener(new OnClickListener() {
          public void onClick(View paramAnonymousView) {
              Editor localEditor = pref.edit();
              localEditor.putBoolean("is_suspend_on_popup_shown", true);
              localEditor.commit();
              mCross.setVisibility(View.INVISIBLE);
              mPopup.setVisibility(View.INVISIBLE);
              mPopupText.setVisibility(View.INVISIBLE);
          }
      });
      mSuspendOff.setOnClickListener(new OnClickListener() {
          public void onClick(View paramAnonymousView) {
              vibrate();
              SuspendOff();
              startActivity(new Intent(SuspendOn.this, SuspendOff.class));
              finish();
          }
      });
      mMusic.setOnClickListener(new OnClickListener() {
          public void onClick(View paramAnonymousView) {
              String str = pref.getString("music_pkg", "");
              if (str != null && str.length() > 0) {
                  ProjectUtil.launchApp(SuspendOn.this, str);
                  return;
              }
              showDialog(1);
          }
      });
      mNavigation.setOnClickListener(new OnClickListener() {
          public void onClick(View paramAnonymousView) {
              String str = pref.getString("navigation_pkg", "");
              if (str != null && str.length() > 0) {
                  ProjectUtil.launchApp(SuspendOn.this, str);
                  return;
              }
              showDialog(2);
          }
      });
  }

  protected Dialog onCreateDialog(int paramInt) {
      AlertDialog localAlertDialog1 = null;
      if (paramInt == 1) {
          View localView3 = LayoutInflater.from(this).inflate(layout.music_dialog, null);
          Button localButton5 = (Button) localView3.findViewById(id.button_cancel);
          Button localButton6 = (Button) localView3.findViewById(id.button_off);
          localButton5.setOnClickListener(new OnClickListener() {
              public void onClick(View paramAnonymousView) {
                  removeDialog(1);
              }
          });
          localButton6.setOnClickListener(new OnClickListener() {
              public void onClick(View paramAnonymousView) {
                  removeDialog(1);
                  vibrate();
                  SuspendOff();
                  startActivity(new Intent(SuspendOn.this, SuspendOff.class));
                  finish();
              }
          });
          localAlertDialog1 = new Builder(this).create();
          localAlertDialog1.setView(localView3);
      } else if (paramInt == 2) {


          View localView2 = LayoutInflater.from(this).inflate(layout.navigation_dialog, null);
          Button localButton3 = (Button) localView2.findViewById(id.button_cancel);
          Button localButton4 = (Button) localView2.findViewById(id.button_off);
          localButton3.setOnClickListener(new OnClickListener() {
              public void onClick(View paramAnonymousView) {
                  removeDialog(2);
              }
          });
          localButton4.setOnClickListener(new OnClickListener() {
              public void onClick(View paramAnonymousView) {
                  removeDialog(2);
                  vibrate();
                  SuspendOff();
                  startActivity(new Intent(SuspendOn.this, SuspendOff.class));
                  finish();
              }
          });
          AlertDialog localAlertDialog3 = new Builder(this).create();
          localAlertDialog3.setView(localView2);
          return localAlertDialog3;


      } else {
          View localView1 = LayoutInflater.from(this).inflate(layout.emergency_dialog, null);
          Button localButton1 = (Button) localView1.findViewById(id.button_No);
          Button localButton2 = (Button) localView1.findViewById(id.button_yes);
          localButton1.setOnClickListener(new OnClickListener() {
              public void onClick(View paramAnonymousView) {
                  removeDialog(3);
              }
          });
          localButton2.setOnClickListener(new OnClickListener() {
              public void onClick(View paramAnonymousView) {
                  SuspendOff();
                  Intent localIntent = new Intent("android.intent.action.CALL");
                  localIntent.addFlags(268435456);
                  localIntent.setData(Uri.parse("tel:112"));
                  startActivity(localIntent);
                  removeDialog(3);
                  System.exit(0);
              }
          });

      }
      AlertDialog localAlertDialog2 = new Builder(this).create();
      localAlertDialog2.setView(LayoutInflater.from(this).inflate(layout.emergency_dialog, null));

      return localAlertDialog2;
  }

  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    super.onCreateOptionsMenu(paramMenu);
      this.getMenuInflater().inflate(menu.on_options, paramMenu);
    return true;
  }

  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    switch (paramMenuItem.getItemId())
    {
    default:
      return false;
    case layout.help:
        this.startActivity(new Intent(this, Help.class));
      return true;
    }

  }
}
