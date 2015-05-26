package unahhennessy.com.suspend.activity;
/**
 * Created by unahe_000 on 21/05/2015.
 */
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.Vector;
import unahhennessy.com.suspend.R;
import unahhennessy.com.suspend.adapter.SetUp3ThreeScreenListAdapter;
import unahhennessy.com.suspend.constants.AppConstants;

public class Settings extends Activity
{
  private SetUp3ThreeScreenListAdapter mAdapter;
  private Button mAddContact;
  private CheckBox mCheckMMS;
  private CheckBox mCheckWHATSAPP;
  private CheckBox mCheckPhone;
  private CheckBox mCheckSMS;
  private Button mDone;
  private TextView mEdittxt;
  private int[] mID;
  private boolean mIsMMSChecked;
  private boolean mIsWHATSAPPChecked;
  private boolean mIsPhoneChecked;
  private boolean mIsSMSChecked;
  private ListView mListView;
  private ImageView mMusic;
  private Vector mName;
  private ImageView mNavigation;
  private Vector mNumber;
  private TextView mOption1;
  private TextView mOption2;
  private TextView mOption3;
  private TextView mOption4;
  private RelativeLayout mRelativeLayoutOption1;
  private RelativeLayout mRelativeLayoutOption2;
  private RelativeLayout mRelativeLayoutOption3;
  private RelativeLayout mRelativeLayoutOption4;
  private Vector mType;
  private SharedPreferences pref;

  private String formatNumber(String paramString)
  {
    String string1 = "";
    int i = paramString.indexOf("+");
    int j = paramString.length();
    if (i == 0)
    {
      paramString = paramString.substring(1, j);
      j--;
    }
    if (j <= 6)
    {
      if (i == 0) {
        return "+" + paramString;
      }
      return paramString;
    }
    int k = j - 4;
    String string2 = paramString;
    for (;;)
    {
      if (j <= 3)
      {
        if (i != 0) {
          break;
        }
        return "+" + string2 + string1;
      }
      string1 = "-" + string2.substring(k, j) + string1;
      string2 = string2.substring(0, k);
      j = string2.length();
      if (j > 3) {
        k = j - 3;
      } else {
        k = j;
      }
    }
    return string2 + string1;
  }

   private void handleLayoutVisibility()
  {
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
    SharedPreferences.Editor localEditor = this.pref.edit();
    localEditor.putBoolean("is_sms_enabled", this.mIsSMSChecked);
    localEditor.putBoolean("is_mms_enabled", this.mIsMMSChecked);
    localEditor.putBoolean("is_whatsapp_enabled", this.mIsWHATSAPPChecked);
    localEditor.putBoolean("is_phone_enabled", this.mIsPhoneChecked);
    localEditor.commit();
  }

  private void setMessage()
  {
    String string = this.pref.getString("custom_msg", getResources().getString(R.string.default_message_to_reply));

    if (string.trim().length() > 0)
    {
      this.mEdittxt.setText(string);
      return;
    }
    this.mEdittxt.setText(getResources().getString(R.string.default_message_to_reply));
  }


  private String unFormatNumber(String paramString)
  {
    int i = paramString.indexOf("-");
    String string1 = "";
    String string2 = paramString;
    int j = paramString.length();
    if ((paramString.indexOf("+") == 0) && (j == 7)) {
      return paramString;
    }
    if (j <= 6) {
      return paramString;
    }
    while (i > 0)
    {
      string1 = string1 + string2.substring(0, i);
      string2 = string2.substring(i + 1, j);
      j = string2.length();
      i = string2.indexOf("-");
    }
    return string1 + string2.substring(j - 4, j);
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt2 == -1)
    {
      //DO SOMETHING
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.settings);
    this.pref = getSharedPreferences(AppConstants.SUSPEND_PREF, 0);
    this.mRelativeLayoutOption1 = (RelativeLayout)findViewById(R.id.option1_layout);
    this.mRelativeLayoutOption2 = ((RelativeLayout)findViewById(R.id.option2_layout));
    this.mRelativeLayoutOption3 = ((RelativeLayout)findViewById(R.id.option3_layout));
    this.mRelativeLayoutOption4 = ((RelativeLayout)findViewById(R.id.option4_layout));
    this.mListView = ((ListView)findViewById(R.id.listMode));
    this.mDone = ((Button)findViewById(R.id.button_done));
    this.mEdittxt = ((TextView)findViewById(R.id.edit_message));
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

    handleLayoutVisibility();
    initializeCheckedValue();
    setMessage();
    this.mListView.setCacheColorHint(0);
    this.mDone.setOnClickListener(new View.OnClickListener() {
      public void onClick(View paramAnonymousView) {
        Settings.this.saveCheckedValue();
        Settings.this.finish();
      }
    });

    this.mCheckSMS.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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
    this.mEdittxt.setOnClickListener(new View.OnClickListener() {
      public void onClick(View paramAnonymousView) {
        Settings.this.startActivity(new Intent(Settings.this, SettingEditMessage.class));
      }
    });
    this.mOption1.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (Settings.this.mRelativeLayoutOption1.getVisibility() == View.GONE)
        {
          Settings.this.handleLayoutVisibility();
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
          Settings.this.handleLayoutVisibility();
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
          Settings.this.handleLayoutVisibility();
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
          Settings.this.handleLayoutVisibility();
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
    super.onResume();
    setMessage();

    this.mAdapter = new SetUp3ThreeScreenListAdapter(this, this.mName, this.mNumber);
    this.mListView.setAdapter(this.mAdapter);

  }
}
