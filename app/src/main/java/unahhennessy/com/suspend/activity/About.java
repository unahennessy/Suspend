package unahhennessy.com.suspend.activity;
/**
 * Created by unahe_000 on 21/05/2015.
 */
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import unahhennessy.com.suspend.R;

public class About extends Activity
{
  private Button mClose;

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.about);
    this.mClose = ((Button)findViewById(R.id.button_close));
    this.mClose.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        About.this.finish();
      }
    });
  }
}
