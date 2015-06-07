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

public class EditMessage extends Activity
{
  private Button mCancel;
  private EditText mEdittext;
  private Button mSave;
  private SharedPreferences pref;
  private static final String TAG = "EditMessage Activity";

  protected void onCreate(Bundle paramBundle)
  {
      this.log("entered onCreate() within EditMessage.java");
    super.onCreate(paramBundle);
    setContentView(R.layout.settingeditmsg);
    this.pref = getSharedPreferences(FactorsInThisApp.mSUSPEND_PREF, 0);
    this.mEdittext = ((EditText)findViewById(R.id.edit_message));
    String str1 = this.pref.getString("custom_msg", getResources().getString(R.string.default_message_to_reply));
    if (str1.length() > 0)
    {
      String str3 = str1.trim();
      this.mEdittext.setText(str3);
    }
    for (;;)
    {
      this.mCancel = ((Button)findViewById(R.id.button_cancel));
      this.mSave = ((Button)findViewById(R.id.button_save));
      this.mCancel.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          EditMessage.this.finish();
        }
      });
      this.mSave.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          SharedPreferences.Editor localEditor = EditMessage.this.pref.edit();
          localEditor.putString("custom_msg", EditMessage.this.mEdittext.getText().toString().trim());
          localEditor.commit();
          EditMessage.this.finish();
        }
      });
      String str2 = getResources().getString(R.string.default_message_to_reply).trim();
      this.mEdittext.setText(str2);
      return;

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
