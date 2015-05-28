package unahhennessy.com.suspend.activity;
/**
 * Created by unahe_000 on 21/05/2015.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import unahhennessy.com.suspend.R;
import unahhennessy.com.suspend.util.SystemUiHider;

public class HomeScreen extends Activity {

    private static final boolean AUTO_HIDE = true;
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;
    private static final boolean TOGGLE_ON_CLICK = true;
    private static final int HIDER_FLAGS = SystemUiHider.FLAG_HIDE_NAVIGATION;
    private SystemUiHider mSystemUiHider;


    private Button mAbout;
    private Button mSettings;
    private Button mCancel;
    private CheckBox mSettingsCheckbox;

    protected void onCreate(Bundle paramBundle)
    {   // set up the home screen
        super.onCreate(paramBundle);
        setContentView(R.layout.home);
        // set up listener on the about button
        this.mAbout = ((Button)findViewById(R.id.button_about));
        this.mAbout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView)
            {
                onOptionsItemSelected(mAbout);
            }
        });
        // set up listener on the settings button
        this.mSettings = ((Button)findViewById(R.id.settings));
        this.mSettings.setOnClickListener(new View.OnClickListener() {
           public void onClick(View paramAnonymousView)
           {
               onOptionsItemSelected(mSettings);
           }
        });
        // set up listener on the settings button
        this.mCancel = ((Button)findViewById(R.id.button_cancel));
        this.mCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView)
            {
                onOptionsItemSelected(mCancel);
            }

            });
    }

    public boolean onOptionsItemSelected(Button button)
    {
        switch (button.getId())
        {
            default:
                return false;
            case R.id.button_cancel:
                HomeScreen.this.finish();
                return true;
            case R.id.button_about:
            startActivity(new Intent(this, About.class));
                HomeScreen.this.finish();
            return true;
            case R.id.settings:
                startActivity(new Intent(this, WelcomeScreen.class));
                HomeScreen.this.finish();
                return true;
        }



}}

