package unahhennessy.com.suspend.activity;
/**
 * Created by unahe_000 on 21/05/2015.
 */
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import unahhennessy.com.suspend.R;
import unahhennessy.com.suspend.factors.FactorsInThisApp;

public class SetUp2Screen extends Activity
{
  private Button mBack;
  private boolean mIsMMSChecked;
  private boolean mIsPhoneChecked;
  private boolean mIsSMSChecked;
  private boolean mIsWHATSAPPChecked;
  private CheckBox mMMSCheck;
  private CheckBox mPhoneCheck;
  private CheckBox mSMSCheck;
  private CheckBox mWHATSAPPCheck;
  private Button mSave;
  private SharedPreferences pref;
  private static final String TAG = "SetUp2Screen Activity";

   private void initializeCheckedValue()
  {
    this.log("entered initializeCheckedValue() within SetUp2Screen.java");
    this.mIsSMSChecked = this.pref.getBoolean("is_sms_enabled", true);
    this.mIsMMSChecked = this.pref.getBoolean("is_mms_enabled", true);
    this.mIsWHATSAPPChecked = this.pref.getBoolean("is_whatsapp_enabled", true);
    this.mIsPhoneChecked = this.pref.getBoolean("is_phone_enabled", false);
    this.mSMSCheck.setChecked(this.mIsSMSChecked);
    this.mMMSCheck.setChecked(this.mIsMMSChecked);
    this.mWHATSAPPCheck.setChecked(this.mIsWHATSAPPChecked);
    this.mPhoneCheck.setChecked(this.mIsPhoneChecked);
  }

  private void saveCheckedValue()
  {
    this.log("entered saveCheckedValue() within SetUp2Screen.java");
    this.mIsSMSChecked = this.mSMSCheck.isChecked();
    this.mIsMMSChecked = this.mMMSCheck.isChecked();
    this.mIsWHATSAPPChecked = this.mWHATSAPPCheck.isChecked();
    this.mIsPhoneChecked = this.mPhoneCheck.isChecked();
    SharedPreferences.Editor localEditor = this.pref.edit();
    localEditor.putBoolean("is_sms_enabled", this.mIsSMSChecked);
    localEditor.putBoolean("is_mms_enabled", this.mIsMMSChecked);
    localEditor.putBoolean("is_whatsapp_enabled", this.mIsWHATSAPPChecked);
    localEditor.putBoolean("is_phone_enabled", this.mIsPhoneChecked);
    localEditor.commit();
  }

  private void saveEdit()
  {
      this.log("entered saveEdit() within SetUp2Screen.java");
    SharedPreferences.Editor localEditor = this.pref.edit();

  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
      this.log("entered onActivityResult() within SetUp2Screen.java");
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
  }

  protected void onCreate(Bundle paramBundle)
  {
      this.log("entered onCreate() within SetUp2Screen.java");
    super.onCreate(paramBundle);
    setContentView(R.layout.setuptwoscreen);
    this.pref = getSharedPreferences(FactorsInThisApp.mSUSPEND_PREF, 0);
    this.mBack = ((Button)findViewById(R.id.button_back));
    this.mSave = ((Button)findViewById(R.id.button_save));
    this.mSMSCheck = ((CheckBox)findViewById(R.id.checkbox_text));
    this.mMMSCheck = ((CheckBox)findViewById(R.id.checkbox_mms));
    this.mWHATSAPPCheck = ((CheckBox)findViewById(R.id.checkbox_whatsapp));
    this.mPhoneCheck = ((CheckBox)findViewById(R.id.checkbox_phone));
    initializeCheckedValue();
    this.mSMSCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean) {
        SetUp2Screen.this.mIsSMSChecked = paramAnonymousBoolean;
      }
    });
    this.mMMSCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
    {
      public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
      {
        SetUp2Screen.this.mIsMMSChecked = paramAnonymousBoolean;
      }
    });
    this.mWHATSAPPCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
    {
      public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
      {
        SetUp2Screen.this.mIsWHATSAPPChecked = paramAnonymousBoolean;
      }
    });
   this.mPhoneCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
    {
      public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
      {
        SetUp2Screen.this.mIsPhoneChecked = paramAnonymousBoolean;
      }
    });
    this.mBack.setOnClickListener(new View.OnClickListener() {
      public void onClick(View paramAnonymousView) {
        SetUp2Screen.this.startActivity(new Intent(SetUp2Screen.this, SetUp2Screen.class));
        SetUp2Screen.this.finish();
      }
    });
    this.mSave.setOnClickListener(new View.OnClickListener() {
      public void onClick(View paramAnonymousView) {
        SetUp2Screen.this.saveCheckedValue();
        SetUp2Screen.this.startActivity(new Intent(SetUp2Screen.this,SetUp3Screen.class));
        SetUp2Screen.this.finish();
      }
    });

   }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
      this.log("entered onKeyDown() within SetUp2Screen.java");
    if (4 == paramInt)
    {
      startActivity(new Intent(this, SetUp1OneScreen.class));
      finish();
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }

  protected void onResume()
  {
      this.log("entered onResume() within SetUp2Screen.java");
    super.onResume();
    if (this.pref.getInt("is_first_time", 0) == 2)
    {
      SharedPreferences.Editor localEditor = this.pref.edit();
      localEditor.putInt("is_first_time", 0);
      localEditor.commit();
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
