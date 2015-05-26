package unahhennessy.com.suspend.activity;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import unahhennessy.com.suspend.R;
import unahhennessy.com.suspend.constants.AppConstants;

public class Agree_Setup extends Activity
{
  private Button mAccept;
  private Button mCancel;
  private TextView mAgree_Setup;
  private SharedPreferences pref;

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.agree_setup);
    this.pref = getSharedPreferences(AppConstants.SUSPEND_PREF, 0);
    this.mAgree_Setup = ((TextView)findViewById(R.id.bottom_text));
    this.mCancel = ((Button)findViewById(R.id.button_cancel));
    this.mAccept = ((Button)findViewById(R.id.button_accept));
    this.mAgree_Setup.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.google.ie"));
        Agree_Setup.this.startActivity(localIntent);
      }
    });
    this.mCancel.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        Agree_Setup.this.finish();
      }
    });
    this.mAccept.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        SharedPreferences.Editor localEditor = Agree_Setup.this.pref.edit();
        localEditor.putBoolean("agree_setup", true);
        localEditor.commit();
        Agree_Setup.this.startActivity(new Intent(Agree_Setup.this, WelcomeScreen.class));
        Agree_Setup.this.finish();
      }
    });
  }
}
