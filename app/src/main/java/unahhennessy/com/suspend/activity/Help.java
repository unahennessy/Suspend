package unahhennessy.com.suspend.activity;
/**
 * Created by unahe_000 on 21/05/2015.
 */
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import unahhennessy.com.suspend.R;
import unahhennessy.com.suspend.factors.FactorsInThisApp;

public class Help extends Activity
{
  // variables declared that are used in the help screen
  private Button mClose;
  private TextView mMusicUrl;
  private TextView mNavigationUrl;
  private SharedPreferences mPref;
  private static final String TAG = "Help Activity";

  private void launchUrl(String paramString)
  {
    startActivity(new Intent("android.intent.action.VIEW", Uri.parse(paramString)));
  }

  protected void onCreate(Bundle paramBundle)
  {
    this.log("entered onCreate() within Help.java");
    super.onCreate(paramBundle);
    setContentView(R.layout.help);
    this.mPref = getSharedPreferences(FactorsInThisApp.mSUSPEND_PREF, 0);
    this.mClose = ((Button)findViewById(R.id.button_close));
    this.mMusicUrl = ((TextView)findViewById(R.id.music_url));
    this.mNavigationUrl = ((TextView)findViewById(R.id.navigation_url));
    if (this.mPref.getBoolean("is_suspend_on", false))
    {
      this.mMusicUrl.setVisibility(View.GONE);
      this.mNavigationUrl.setVisibility(View.GONE);
    }
    for (;;)
    {
      this.mClose.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        { // close the help screen by leaving
          Help.this.finish();
        }
      });
      this.mMusicUrl.setOnClickListener(new View.OnClickListener() {
        public void onClick(View paramAnonymousView) {
            // google play for music
          Help.this.launchUrl("http://www.googleplay.com");
        }
      });
      this.mNavigationUrl.setOnClickListener(
              new View.OnClickListener() {
        public void onClick(View paramAnonymousView) {
            // google maps for navigation
          Help.this.launchUrl("http://www.googlemaps.com");
        }
      });
      this.mMusicUrl.setVisibility(View.VISIBLE);
      this.mNavigationUrl.setVisibility(View.VISIBLE);
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
