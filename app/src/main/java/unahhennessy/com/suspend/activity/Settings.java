package unahhennessy.com.suspend.activity;
/**
 * Created by unahe_000 on 21/05/2015.
 */
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import unahhennessy.com.suspend.R;
import unahhennessy.com.suspend.adapter.SetUp3ThreeScreenListAdapter;
import unahhennessy.com.suspend.factors.FactorsInThisApp;

public class Settings extends Activity
{
  private SetUp3ThreeScreenListAdapter mAdapter;
  private CheckBox mCheckMMS;
  private CheckBox mCheckWHATSAPP;
  private CheckBox mCheckPhone;
  private CheckBox mCheckSMS;
  private Button mDone;
  private TextView mEditText;
  private boolean mIsMMSChecked;
  private boolean mIsWHATSAPPChecked;
  private boolean mIsPhoneChecked;
  private boolean mIsSMSChecked;
  private ListView mListView;
  private ImageView mMusic;
  private ImageView mNavigation;
  private TextView mOption1;
  private TextView mOption2;
  private TextView mOption3;
  private TextView mOption4;
  private RelativeLayout mRelativeLayoutOption1;
  private RelativeLayout mRelativeLayoutOption2;
  private RelativeLayout mRelativeLayoutOption3;
  private RelativeLayout mRelativeLayoutOption4;
  private SharedPreferences pref;
  private static final String TAG = "Settings Activity";

  private void handleOptionsLayoutVisibility()
  {
    this.log("entered handleOptionsLayoutVisibility() within Settings.java");
    this.mRelativeLayoutOption1.setVisibility(View.GONE);
    this.mRelativeLayoutOption2.setVisibility(View.GONE);
    this.mRelativeLayoutOption3.setVisibility(View.GONE);
    this.mRelativeLayoutOption4.setVisibility(View.GONE);
    this.mOption1.setTextColor(getResources().getColor(R.color.settingFontColour));
    this.mOption2.setTextColor(getResources().getColor(R.color.settingFontColour));
    this.mOption3.setTextColor(getResources().getColor(R.color.settingFontColour));
    this.mOption4.setTextColor(getResources().getColor(R.color.settingFontColour));
  }

  private void initializeCheckedValue()
  {
      this.log("entered initializeCheckedValue() within Settings.java");
    this.mIsSMSChecked = this.pref.getBoolean("is_sms_enabled", true);
    this.mIsMMSChecked = this.pref.getBoolean("is_mms_enabled", true);
    this.mIsWHATSAPPChecked = this.pref.getBoolean("is_whatsapp_enabled", true);
    this.mIsPhoneChecked = this.pref.getBoolean("is_phone_enabled", false);
    this.mCheckSMS.setChecked(this.mIsSMSChecked);
    this.mCheckMMS.setChecked(this.mIsMMSChecked);
    this.mCheckWHATSAPP.setChecked(this.mIsWHATSAPPChecked);
    this.mCheckPhone.setChecked(this.mIsPhoneChecked);
  }

  private void saveCheckedValue()
  {
      this.log("entered saveCheckedValue() within Settings.java");
    SharedPreferences.Editor localEditor = this.pref.edit();
    localEditor.putBoolean("is_sms_enabled", this.mIsSMSChecked);
    localEditor.putBoolean("is_mms_enabled", this.mIsMMSChecked);
    localEditor.putBoolean("is_whatsapp_enabled", this.mIsWHATSAPPChecked);
    localEditor.putBoolean("is_phone_enabled", this.mIsPhoneChecked);
    localEditor.commit();
  }

  private void setMessage()
  {
      this.log("entered setMessage() within Settings.java");
    String string = this.pref.getString("custom_msg", getResources().getString(R.string.default_message_to_reply));

    if (string.trim().length() > 0)
    {
      this.mEditText.setText(string);
      return;
    }
    this.mEditText.setText(getResources().getString(R.string.default_message_to_reply));
  }


  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
      this.log("entered onActivityResult() within Settings.java");
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt2 == -1)
    {
      //DO SOMETHING
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
      this.log("entered onCreate() within Settings.java");
    super.onCreate(paramBundle);
    setContentView(R.layout.settings);
    this.pref = getSharedPreferences(FactorsInThisApp.mSUSPEND_PREF, 0);

    this.mRelativeLayoutOption1 = (RelativeLayout)findViewById(R.id.option1_layout);
    this.mRelativeLayoutOption2 = ((RelativeLayout)findViewById(R.id.option2_layout));
    this.mRelativeLayoutOption3 = ((RelativeLayout)findViewById(R.id.option3_layout));
    this.mRelativeLayoutOption4 = ((RelativeLayout)findViewById(R.id.option4_layout));
    this.mListView = ((ListView)findViewById(R.id.listMode));
    this.mDone = ((Button)findViewById(R.id.button_done));
    this.mEditText = ((TextView)findViewById(R.id.edit_message));
    this.mOption1 = ((TextView)findViewById(R.id.option1));
    this.mOption2 = ((TextView)findViewById(R.id.option2));
    this.mOption3 = ((TextView)findViewById(R.id.option3));
    this.mOption4 = ((TextView)findViewById(R.id.option4));
    this.mCheckSMS = ((CheckBox)findViewById(R.id.checkbox_text));
    this.mCheckMMS = ((CheckBox)findViewById(R.id.checkbox_mms));
    this.mCheckWHATSAPP = ((CheckBox)findViewById(R.id.checkbox_whatsapp));
    this.mCheckPhone = ((CheckBox)findViewById(R.id.checkbox_phone));
    this.mMusic = ((ImageView)findViewById(R.id.image_music));
    this.mNavigation = ((ImageView)findViewById(R.id.image_navigation));

    handleOptionsLayoutVisibility();
    initializeCheckedValue();
    setMessage();
    this.mListView.setCacheColorHint(R.color.abc_background_cache_hint_selector_material_light);
    this.mDone.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        Settings.this.saveCheckedValue();
        Settings.this.finish();
      }
    });

    this.mCheckSMS.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
    {
      public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean) {
        Settings.this.mIsSMSChecked = paramAnonymousBoolean;
      }
    });
    this.mCheckMMS.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean) {
            Settings.this.mIsMMSChecked = paramAnonymousBoolean;
        }
    });
    this.mCheckWHATSAPP.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean) {
        Settings.this.mIsWHATSAPPChecked = paramAnonymousBoolean;
      }
    });
    this.mCheckPhone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean) {
            Settings.this.mIsPhoneChecked = paramAnonymousBoolean;
        }
    });
    this.mEditText.setOnClickListener(new View.OnClickListener() {
      public void onClick(View paramAnonymousView) {
        Settings.this.startActivity(new Intent(Settings.this, EditMessage.class));
      }
    });
    this.mOption1.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (Settings.this.mRelativeLayoutOption1.getVisibility() == View.GONE)
        {
          Settings.this.handleOptionsLayoutVisibility();
          Settings.this.mRelativeLayoutOption1.setVisibility(View.VISIBLE);
          Settings.this.mOption1.setTextColor(Settings.this.getResources().getColor(R.color.settingSelectedColour));
          return;
        }
        Settings.this.mRelativeLayoutOption1.setVisibility(View.GONE);
        Settings.this.mOption1.setTextColor(Settings.this.getResources().getColor(R.color.settingOption1Colour));
      }
    });
    this.mOption2.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (Settings.this.mRelativeLayoutOption2.getVisibility() == View.GONE)
        {
          Settings.this.handleOptionsLayoutVisibility();
          Settings.this.mRelativeLayoutOption2.setVisibility(View.VISIBLE);
          Settings.this.mOption2.setTextColor(Settings.this.getResources().getColor(R.color.settingSelectedColour));
          return;
        }
        Settings.this.mRelativeLayoutOption2.setVisibility(View.GONE);
        Settings.this.mOption2.setTextColor(Settings.this.getResources().getColor(R.color.settingOption1Colour));
      }
    });
    this.mOption3.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (Settings.this.mRelativeLayoutOption3.getVisibility() == View.GONE)
        {
          Settings.this.handleOptionsLayoutVisibility();
          Settings.this.mRelativeLayoutOption3.setVisibility(View.VISIBLE);
          Settings.this.mOption3.setTextColor(Settings.this.getResources().getColor(R.color.settingSelectedColour));
          return;
        }
        Settings.this.mRelativeLayoutOption3.setVisibility(View.GONE);
        Settings.this.mOption3.setTextColor(Settings.this.getResources().getColor(R.color.settingOption1Colour));
      }
    });
    this.mOption4.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (Settings.this.mRelativeLayoutOption4.getVisibility() == View.GONE)
        {
          Settings.this.handleOptionsLayoutVisibility();
          Settings.this.mRelativeLayoutOption4.setVisibility(View.VISIBLE);
          Settings.this.mOption4.setTextColor(Settings.this.getResources().getColor(R.color.settingSelectedColour));
          return;
        }
        Settings.this.mRelativeLayoutOption4.setVisibility(View.GONE);
        Settings.this.mOption4.setTextColor(Settings.this.getResources().getColor(R.color.settingOption1Colour));
      }
    });
    this.mMusic.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        Settings.this.startActivity(new Intent(Settings.this, Music.class));
      }
    });
    this.mNavigation.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        Settings.this.startActivity(new Intent(Settings.this, Navigation.class));
      }
    });
  }


  protected void onResume()
  {
      this.log("entered onResume() within Settings.java");
    super.onResume();
    setMessage();

    this.mListView.setAdapter(this.mAdapter);

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
