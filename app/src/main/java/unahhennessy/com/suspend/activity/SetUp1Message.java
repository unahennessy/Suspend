package unahhennessy.com.suspend.activity;
/**
 * Created by unahe_000 on 21/05/2015.
 */
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import unahhennessy.com.suspend.R;
import unahhennessy.com.suspend.factors.FactorsInThisApp;

public class SetUp1Message extends Activity
{
  private Button mCancel;
  private EditText mEdittext;
  private Button mSave;
  private SharedPreferences pref;
  private static final String TAG = "SetUp1Message Activity";

  protected void onCreate(Bundle paramBundle)
  {
      this.log("entered onCreate() within SetUp1Message.java");
    super.onCreate(paramBundle);
    setContentView(R.layout.setuponeeditmsg);
    this.pref = getSharedPreferences(FactorsInThisApp.mSUSPEND_PREF, 0);
    this.mEdittext = ((EditText)findViewById(R.id.edit_message));
    String str = this.pref.getString("custom_msg", "");
    if (str.length() > 0) {
      this.mEdittext.setText(str);
    }
    this.mCancel = ((Button)findViewById(R.id.button_cancel));
    this.mSave = ((Button)findViewById(R.id.button_save));
    this.mCancel.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        SetUp1Message.this.finish();
      }
    });
    this.mSave.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        SharedPreferences.Editor localEditor = SetUp1Message.this.pref.edit();
        localEditor.putString("custom_msg", SetUp1Message.this.mEdittext.getText().toString().trim());
        localEditor.commit();
        SetUp1Message.this.finish();
      }
    });
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

