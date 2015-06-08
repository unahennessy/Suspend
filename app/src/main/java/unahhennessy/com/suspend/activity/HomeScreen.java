package unahhennessy.com.suspend.activity;
/**
 * Created by unahe_000 on 21/05/2015 ${PACKAGE_NAME} Suspend.
 *
 */
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import unahhennessy.com.suspend.R;

public class HomeScreen extends Activity {
    /*
    *  The HomeScreen is the 1st activity in Suspend, It has 3 buttons About(when you press it you get more information about this app)
    *  it has the Agree button which launches the Terms and Condtions Screen which have to be accepted by the driver
    *  and it has a cancel button, so if a driver choses not to setup suspend they can leave.
    *  This is where you would first come into the app to begin process of setting up settings
    *  after the settings have all been setup then you are directed to the SuspendOff screen as a future entry point to the app

    */

    // declare variables to be used in this activity
    private Button mAbout;
    private Button mAgree;
    private Button mCancel;
    private SharedPreferences pref;
    private Boolean mSettingsAreGood;

    private static final String TAG = "HomeScreen Activity";

    private void initializeCheckedValue()
    {
        this.log("entered initializeCheckedValue() within HomeScreen.java");
        //mSettingsAreGood true then settings are already saved
        this.mSettingsAreGood = this.pref.getBoolean("is_settingsAreGood", true);

    }

    protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
    {
        this.log("entered onActivityResult() within HomeScreen.java");
        super.onActivityResult(paramInt1, paramInt2, paramIntent);
    }



    protected void onCreate(Bundle paramBundle)
    {   // set up the home screen
        this.log("entered onCreate() within HomeScreen.java");
        super.onCreate(paramBundle);
        initializeCheckedValue();
        if (mSettingsAreGood == true)
        {
            //todo bring up Suspend screen instead of going through settings
            startActivity(new Intent(this, SuspendOff.class));
            HomeScreen.this.finish();

        }

        else{

        setContentView(R.layout.home);



            this.mAbout = ((Button)findViewById(R.id.button_about));
            this.mAbout.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View paramAnonymousView)
                { // call method onOptionsItemSelected using mAbout value
                    onOptionsItemSelected(mAbout);
                }
            });
            // set up listener on the agree button
            this.mAgree = ((Button)findViewById(R.id.btn_agree));
            this.mAgree.setOnClickListener(new View.OnClickListener() {

                public void onClick(View paramAnonymousView)
                {// call method onOptionsItemSelected using mAgree value
                    onOptionsItemSelected(mAgree);
                }
            });
            // set up listener on the settings button
            this.mCancel = ((Button)findViewById(R.id.button_cancel));
            this.mCancel.setOnClickListener(new View.OnClickListener() {
                public void onClick(View paramAnonymousView)
                {
                    // call method onOptionsItemSelected using mCancel value
                    onOptionsItemSelected(mCancel);
                }

            });
        }

    }

    public boolean onOptionsItemSelected(Button button)
    {  this.log("entered onOptionsItemsSelected() within HomeScreen.java");
        switch (button.getId())
        {
            default:
                return false;
            case R.id.button_cancel:
                // cancel user ends session and we close this activity
                HomeScreen.this.finish();
                return true;
            case R.id.button_about:
                // about user ends session and we close this activity and starts up activity About.class
            startActivity(new Intent(this, About.class));
                HomeScreen.this.finish();
            return true;
            case R.id.btn_agree:
                // agree user ends session and we close this activity and starts up activity Agree.class
                startActivity(new Intent(this, Agree_Setup.class));
                HomeScreen.this.finish();
                return true;
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

