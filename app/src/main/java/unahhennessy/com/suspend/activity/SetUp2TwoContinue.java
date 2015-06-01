package unahhennessy.com.suspend.activity;
/**
 * Created by unahe_000 on 21/05/2015.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import unahhennessy.com.suspend.R;

public class SetUp2TwoContinue extends Activity
{
    /* all set up so now you get sent to the Suspend Off screen */
  private Button mContinue;

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.setuptwocontinue);
    this.mContinue = ((Button)findViewById(R.id.button_continue));
    this.mContinue.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {

        SetUp2TwoContinue.this.startActivity(new Intent(SetUp2TwoContinue.this,SuspendOff.class));
        SetUp2TwoContinue.this.finish();
      }
    });
  }
}

