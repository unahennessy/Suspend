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

public class About extends Activity
{ // About is a screen about the app and its aims with info on who to contact if problems
  private Button mClose;
  private Button mBack;

  protected void onCreate(Bundle paramBundle)
  {

    super.onCreate(paramBundle);
    setContentView(R.layout.about);
      // go back to the screen before if back button clicked
    this.mBack = ((Button)findViewById(R.id.button_back));
    this.mBack.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
         onOptionsItemSelected(mBack);
     }
    });
      // close app to the screen before if close button clicked
    this.mClose = ((Button)findViewById(R.id.button_close));
    this.mClose.setOnClickListener(new View.OnClickListener() {
      public void onClick(View paramAnonymousView)
      {
          onOptionsItemSelected(mClose);
      }
    });
  }

  public boolean onOptionsItemSelected(Button button)
    {
        switch (button.getId())
        {
            default:
                return false;
            case R.id.button_close:
                About.this.finish();
                return true;
            case R.id.button_back:
                startActivity(new Intent(this, HomeScreen.class));
                About.this.finish();
                return true;
        }
    }
}
