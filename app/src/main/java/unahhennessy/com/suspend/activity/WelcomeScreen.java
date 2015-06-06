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
import unahhennessy.com.suspend.other.SystemUiHider;

public class WelcomeScreen extends Activity {

    private static final boolean AUTO_HIDE = true;
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;
    private static final boolean TOGGLE_ON_CLICK = true;
    private static final int HIDER_FLAGS = SystemUiHider.FLAG_HIDE_NAVIGATION;
    private SystemUiHider mSystemUiHider;
    private static final String TAG = "WelcomeScreen Activity";

        private Button mContinue;

    protected void onCreate(Bundle paramBundle)
    {
        this.log("entered onCreate() within WelcomeScreen.java");
        super.onCreate(paramBundle);
        setContentView(R.layout.welcome);
        this.mContinue = ((Button)findViewById(R.id.button_continue));
        this.mContinue.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                WelcomeScreen.this.startActivity(new Intent(WelcomeScreen.this, SetUp1OneScreen.class));
                WelcomeScreen.this.finish();
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
        Log.i(WelcomeScreen.TAG, msg);

    }


}

