package unahhennessy.com.suspend.activity;
/**
 * Created by unahe_000 on 21/05/2015.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import unahhennessy.com.suspend.R;

public class SetUp3Screen extends Activity
{
    /* all set up so now you get sent to the Suspend Off screen */
  private Button mContinue;
    private static final String TAG = "SetUp3Screen Activity";

  protected void onCreate(Bundle paramBundle)
  {
      this.log("entered onCreate() within SetUp3Screen.java");
    super.onCreate(paramBundle);
    setContentView(R.layout.setuptwocontinue);
    this.mContinue = (Button) findViewById(R.id.button_continue);
    this.mContinue.setOnClickListener(new View.OnClickListener() {
      public void onClick(View paramAnonymousView) {

        SetUp3Screen.this.startActivity(new Intent(SetUp3Screen.this, SuspendOff.class));
        SetUp3Screen.this.finish();
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
    Log.i(SetUp3Screen.TAG, msg);

  }
}
