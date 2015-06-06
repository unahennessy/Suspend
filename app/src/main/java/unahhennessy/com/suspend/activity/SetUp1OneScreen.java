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
import android.widget.TextView;
import unahhennessy.com.suspend.factors.FactorsInThisApp;
import unahhennessy.com.suspend.R;

public class SetUp1OneScreen extends Activity
{
      private TextView mEditMsg;
      private Button mSave;
      private SharedPreferences pref;
      private static final String TAG = "SetUp1OneScreen Activity";


      protected void onCreate(Bundle paramBundle)
      {  this.log("entered onCreate() within SetUp1OneScreen.java");
        super.onCreate(paramBundle);
        setContentView(R.layout.setuponescreen);

        this.pref = getSharedPreferences(FactorsInThisApp.mSUSPEND_PREF, 0);
        this.mEditMsg = ((TextView)findViewById(R.id.edit_message));
        this.mSave = ((Button)findViewById(R.id.button_save));
        this.mEditMsg.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            SetUp1OneScreen.this.startActivity(new Intent(SetUp1OneScreen.this, SetUp1Message.class));
          }
        });
        this.mSave.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            SetUp1OneScreen.this.startActivity(new Intent(SetUp1OneScreen.this, SetUp2Screen.class));
            SetUp1OneScreen.this.finish();
          }
        });
      }

      protected void onResume()
      {
          this.log("entered onResume() within SetUp1OneScreen.java");
        super.onResume();
        String str = this.pref.getString("custom_msg", getResources().getString(R.string.default_message_to_reply));
        if (str.trim().length() > 0)
        {
          this.mEditMsg.setText(str);
          return;
        }
        this.mEditMsg.setText(getResources().getString(R.string.default_message_to_reply));
      }
      private void log(String msg)
      {
        try {
          Thread.sleep(500);
        }
        catch (InterruptedException e) {
          e.printStackTrace();
        }
        Log.i(SetUp1OneScreen.TAG, msg);

      }
}
