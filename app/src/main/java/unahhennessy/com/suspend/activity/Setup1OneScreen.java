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
import android.widget.TextView;
import unahhennessy.com.suspend.factors.FactorsInThisApp;
import unahhennessy.com.suspend.R;

public class Setup1OneScreen   extends Activity
{
  private TextView mEditMsg;
  private Button mSave;
  private SharedPreferences pref;

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.setuponescreen);

    this.pref = getSharedPreferences(FactorsInThisApp.mSUSPEND_PREF, 0);
    this.mEditMsg = ((TextView)findViewById(R.id.edit_message));
    this.mSave = ((Button)findViewById(R.id.button_save));
    this.mEditMsg.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        Setup1OneScreen.this.startActivity(new Intent(Setup1OneScreen.this, SetUp1OneEditMessage.class));
      }
    });
    this.mSave.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        Setup1OneScreen.this.startActivity(new Intent(Setup1OneScreen.this, SetUp2TwoScreen.class));
        Setup1OneScreen.this.finish();
      }
    });
  }

  protected void onResume()
  {
    super.onResume();
    String str = this.pref.getString("custom_msg", getResources().getString(R.string.default_message_to_reply));
    if (str.trim().length() > 0)
    {
      this.mEditMsg.setText(str);
      return;
    }
    this.mEditMsg.setText(getResources().getString(R.string.default_message_to_reply));
  }
}
