package unahhennessy.com.suspend.activity;
/**
 * Created by unahe_000 on 21/05/2015.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import unahhennessy.com.suspend.R;
import unahhennessy.com.suspend.R.id;
import unahhennessy.com.suspend.factors.FactorsInThisApp;
import unahhennessy.com.suspend.listener.PhoneListener;
import unahhennessy.com.suspend.other.NotificationStopOtherApps;


public class SuspendOn  extends Activity
{
  private AudioManager mAudioManager;
  private ImageView mCross;
  private Context mContext;
  private ImageView mSuspendOff;
  private ImageView mEmergency;
  private ImageView mMusic;
  private ImageView mNavigation;
  private ImageView mPopup;
  private TextView mPopupText;
  private int mParamInt;
  private SharedPreferences pref;
  private static final String TAG = "SuspendOn Activity";

  private void SuspendOff()
  {
    try
    {
        this.log("entered SuspendOff() within SuspendOn.java");
      SharedPreferences.Editor localEditor = this.pref.edit();
      localEditor.putBoolean("is_suspend_on", false);
      localEditor.commit();
        this.mAudioManager.setRingerMode(2);
      if ( PhoneListener.telephonymanager != null) {
        PhoneListener.removeListener();
      }
        stopService(new Intent(this, PhoneListener.class));
      //stopService(new Intent(this, AppTrackingService.class));

      NotificationStopOtherApps.notifyIcon(this);
      return;
    }
    catch (Exception localException)
    {
      NotificationStopOtherApps.writeErrorLog(this, localException.getMessage());
    }
  }



    private void SuspendOn()
  {
    try
    {
        this.log("entered SuspendOnf() within SuspendOn.java");
        removeSavedSms();
      SharedPreferences.Editor localEditor = this.pref.edit();
      localEditor.putBoolean("is_suspend_on", true);
      localEditor.commit();

        // need to check for bluetooth here and if driver has bluetooth then phone can be answered otherwise send a message

        this.mAudioManager.setRingerMode(0);

      for (;;)
      {

        NotificationStopOtherApps.notifyIcon(this);
        //startService(new Intent(this, AppTrackingService.class));
          startService(new Intent(this, PhoneListener.class));
        return;
      }

    }
    catch (Exception localException)
    {
      NotificationStopOtherApps.writeErrorLog(this, localException.getMessage());
    }
  }

  private void playSound()
  {
      this.log("entered playSound() within SuspendOn.java");
    MediaPlayer.create(this, R.raw.beep).start();
  }

  private void removeSavedSms()
  {
      this.log("entered removeSavedSms() within SuspendOn.java");
    SharedPreferences.Editor localEditor = this.pref.edit();
    localEditor.remove("last_sms_received_no");
    localEditor.remove("last_sms_received_msg");
    localEditor.remove("last_sms_received_time");
    localEditor.commit();
  }

  private void vibrate()
  {
      this.log("entered vibrate() within SuspendOn.java");
     Vibrator mVibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
      // Get instance of Vibrator from current Context
    // Vibrate for 500 milliseconds
      mVibrator.vibrate(500L);
  }

  public boolean dispatchKeyEvent(KeyEvent paramKeyEvent)
  {
      this.log("entereddispatchKeyEvent() within SuspendOn.java");
  /* these constants are used to represent key events, 19 equals KEYCODE_DPAD_UP,
  **20 equals KEYCODE_DPAD_DOWN, 21 equals KEYCODE_DPAD_LEFT,
  **22 equals KEYCODE_DPAD_RIGHT, 23 equals KEYCODE_DPAD_CENTRE, 82 equals KEYCODE_MENU
  */
      if (19 == paramKeyEvent.getKeyCode() ||
                20 == paramKeyEvent.getKeyCode() ||
                21 == paramKeyEvent.getKeyCode() ||
                22 == paramKeyEvent.getKeyCode() ||
                23 == paramKeyEvent.getKeyCode() ||
                82 == paramKeyEvent.getKeyCode())
        {
          return false;
        }
      return false;
    }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
      this.log("entered onActivityResult within SuspendOn.java");
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (-1 == paramInt2 && paramIntent.getExtras().getBoolean("exit"))
    {
        SuspendOff();
        finish();
      System.exit(0);
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
      this.log("entered onCreate() within SuspendOn.java");
    super.onCreate(paramBundle);
      setContentView(R.layout.suspendon);
      this.pref = getSharedPreferences(FactorsInThisApp.mSUSPEND_PREF, 0);
      this.mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
      this.mMusic = (ImageView) findViewById(id.image_music);
      this.mNavigation = (ImageView) findViewById(id.image_navigation);
      this.mCross = (ImageView) findViewById(id.image_cross);
      this.mPopup = (ImageView) findViewById(id.image_popup);
      this.mSuspendOff = (ImageView) findViewById(R.id.image_suspend_on_clickable);
      this.mEmergency = (ImageView) findViewById(id.image_emergency);
      this.mPopupText = (TextView) findViewById(id.text_popup);
      SuspendOn();
    if (this.pref.getBoolean("is_suspend_on_popup_shown", false))
    {
        this.mCross.setVisibility(View.INVISIBLE);
        this.mPopup.setVisibility(View.INVISIBLE);
        this.mPopupText.setVisibility(View.INVISIBLE);
    }

     // set mParamInt to 3 if mEmergency clicked
      this.mEmergency.setOnClickListener(new View.OnClickListener() {

          public void onClick(View paramAnonymousView) {
              SuspendOn.this.log("entered mEmergency.setOnClickListener() within SuspendOn.java");
              SuspendOn.this.mParamInt = 3;

          }
      });
      this.mCross.setOnClickListener(new View.OnClickListener() {
          public void onClick(View paramAnonymousView) {
              SuspendOn.this.log("entered mCross.setOnClickListener() within SuspendOn.java");
              SharedPreferences.Editor localEditor = SuspendOn.this.pref.edit();
              localEditor.putBoolean("is_suspend_on_popup_shown", true);
              localEditor.commit();
              SuspendOn.this.mCross.setVisibility(View.INVISIBLE);
              SuspendOn.this.mPopup.setVisibility(View.INVISIBLE);
              SuspendOn.this.mPopupText.setVisibility(View.INVISIBLE);
          }
      });
      mSuspendOff.setOnClickListener(new View.OnClickListener() {
          public void onClick(View paramAnonymousView) {
              SuspendOn.this.log("entered mSuspendOff.setOnClickListener() within SuspendOn.java");
              //SuspendOn.this.vibrate();
              SuspendOn.this.getSystemService(VIBRATOR_SERVICE);


              SuspendOn.this.SuspendOff();
              SuspendOn.this.startActivity(new Intent(SuspendOn.this, SuspendOff.class));
              SuspendOn.this.finish();
          }
      });
      this.mMusic.setOnClickListener(new View.OnClickListener() {
          public void onClick(View paramAnonymousView) {
              SuspendOn.this.log("entered mMusic.setOnClickListener() within SuspendOn.java");
              String str = SuspendOn.this.pref.getString("music_pkg", "");
              if (str != null && str.length() > 0) {
                  NotificationStopOtherApps.launchApp(SuspendOn.this, str);
                  return;
              }
              SuspendOn.this.mParamInt = 1;
          }
      });
      this.mNavigation.setOnClickListener(new View.OnClickListener() {
          public void onClick(View paramAnonymousView) {
              SuspendOn.this.log("entered mNavigation.setOnClickListener() within SuspendOn.java");
              String str = SuspendOn.this.pref.getString("navigation_pkg", "");
              if (str != null && str.length() > 0) {
                  NotificationStopOtherApps.launchApp(SuspendOn.this, str);
                  return;
              }
              SuspendOn.this.mParamInt = 2;
          }
      });
  }

    public DialogFragment  DialogFragment(int mParamInt)
    {
        DialogFragment dialogFragment = new DialogFragment();
        this.log("entered DialogFragment() within SuspendOn.java");
        Bundle args = new Bundle();
        args.putInt("mParamInt", mParamInt);
        dialogFragment.setArguments(args);

        return dialogFragment;
    }
    public View onCreateView(Bundle args)

    {
        if (this.mParamInt == 1)
        {
            this.log("entered mParamInt==1 within SuspendOn.java");
            DialogFragment dialogFragmentMusic = new DialogFragment();
            View localView3 = LayoutInflater.from(this).inflate(R.layout.music_dialog, null);
            Button localButton5 = (Button) localView3.findViewById(R.id.button_cancel);
            Button localButton6 = (Button) localView3.findViewById(R.id.button_off);
            localButton5.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View paramAnonymousView)
                {
                    SuspendOn.this.finish();
                }
            });
            localButton6.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View paramAnonymousView) {
                    SuspendOn.this.vibrate();
                    SuspendOn.this.SuspendOff();
                    SuspendOn.this.startActivity(new Intent(SuspendOn.this, SuspendOff.class));
                    SuspendOn.this.finish();
                }
            });
            dialogFragmentMusic = new DialogFragment();
            localView3 = LayoutInflater.from(this).inflate(R.layout.music_dialog, null);
            return localView3;
        }
        else if (this.mParamInt == 2)
        {
            this.log("entered mParamInt==2 within SuspendOn.java");
            DialogFragment dialogFragmentNavigation = new DialogFragment();
            View localView2 = LayoutInflater.from(this).inflate(R.layout.navigation_dialog, null);
            Button localButton3 = (Button) localView2.findViewById(R.id.button_cancel);
            Button localButton4 = (Button) localView2.findViewById(R.id.button_off);
            localButton3.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View paramAnonymousView) {
                    SuspendOn.this.finish();
                }
            });
            localButton4.setOnClickListener(new View.OnClickListener() {
                public void onClick(View paramAnonymousView) {
                    SuspendOn.this.vibrate();
                    SuspendOn.this.SuspendOff();
                    SuspendOn.this.startActivity(new Intent(SuspendOn.this, SuspendOff.class));
                    SuspendOn.this.finish();
                }
            });
           dialogFragmentNavigation = new DialogFragment();
            localView2 = LayoutInflater.from(this).inflate(R.layout.navigation_dialog, null);
            return localView2;


        }
        else
        {
            this.log("entered mParamInt==3 within SuspendOn.java");
            DialogFragment dialogFragmentEmergency = new DialogFragment();
            View localView1 = LayoutInflater.from(this).inflate(R.layout.emergency_dialog, null);
            Button localButton1 = (Button) localView1.findViewById(R.id.button_No);
            Button localButton2 = (Button) localView1.findViewById(R.id.button_yes);
            localButton1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View paramAnonymousView) {
                    SuspendOn.this.finish();
                }
            });
            localButton2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View paramAnonymousView) {
                    SuspendOn.this.SuspendOff();
                    Intent localIntent = new Intent("android.intent.action.CALL");
                    localIntent.addFlags(268435456);
                    localIntent.setData(Uri.parse("tel:112"));
                    SuspendOn.this.startActivity(localIntent);
                    SuspendOn.this.finish();
                    System.exit(0);
                }
            });

            dialogFragmentEmergency = new DialogFragment();
            localView1 = LayoutInflater.from(this).inflate(R.layout.emergency_dialog, null);
            return localView1;

          }
    }

  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
      this.log("entered onCreateOptionsMenu within SuspendOn.java");
    super.onCreateOptionsMenu(paramMenu);
      getMenuInflater().inflate(R.menu.on_options, paramMenu);
    return true;
  }

  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
      this.log("entered onOptionsItemSelected within SuspendOn.java");
    switch (paramMenuItem.getItemId())
    {
    default:
      return false;
    case R.layout.help:
        startActivity(new Intent(this, Help.class));
      return true;
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
      Log.i(SuspendOn.TAG, msg);

  }

}
