package unahhennessy.com.suspend.activity;
/**
 * Created by unahe_000 on 21/05/2015 ${PACKAGE_NAME} Suspend.
 *
 */
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import unahhennessy.com.suspend.R;
import unahhennessy.com.suspend.util.SystemUiHider;

public class HomeScreen extends Activity {
    /*
    *  The HomeScreen is the 1st activity in Suspend, It has 3 buttons About(when you press it you get more information about this app)
    *  it has the Agree button which launches the Terms and Condtions Screen which have to be accepted by the driver
    *  and it has a cancel button, so if a driver choses not to setup suspend they can leave.

    */

    // declare variables to be used in this activity
    private static final boolean AUTO_HIDE = true;
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;
    private static final boolean TOGGLE_ON_CLICK = true;
    private static final int HIDER_FLAGS = SystemUiHider.FLAG_HIDE_NAVIGATION;
    private SystemUiHider mSystemUiHider;
    private Button mAbout;
    private Button mAgree;
    private Button mCancel;
    private boolean mIsSettingsCheckbox_checked;
    private CheckBox mSettingsCheck;
    private SharedPreferences pref;


    private void initializeCheckedValue()
    {   // I want to use this to avoid going through all the settings everytime and instead go straight to SuspendOn
        this.mIsSettingsCheckbox_checked = this.pref.getBoolean("is_settings_checked", true);
        this.mSettingsCheck.setChecked(this.mIsSettingsCheckbox_checked);
       
    }

    private void saveCheckedValue()
    {       
        this.mIsSettingsCheckbox_checked = this.mSettingsCheck.isChecked();
        SharedPreferences.Editor localEditor = this.pref.edit();
        localEditor.putBoolean("is_whatsapp_enabled", this.mIsSettingsCheckbox_checked);
        localEditor.commit();
    }

    private void saveEdit()
    {
        SharedPreferences.Editor localEditor = this.pref.edit();

    }

    protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
    {
        super.onActivityResult(paramInt1, paramInt2, paramIntent);
    }



    protected void onCreate(Bundle paramBundle)
    {   // set up the home screen
        super.onCreate(paramBundle);
        setContentView(R.layout.home);
        this.mSettingsCheck = ((CheckBox)findViewById(R.id.settingsok_checkbox));
        //initialize listener for settings checkbox
        this.mSettingsCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
            {
                HomeScreen.this.mIsSettingsCheckbox_checked = paramAnonymousBoolean;
            }
        });
        if (!mIsSettingsCheckbox_checked)
        {    HomeScreen.this.mSettingsCheck.setVisibility(View.INVISIBLE);
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

        if (mIsSettingsCheckbox_checked)
        {  // this is what i hope to use when settings have been saved
            //go directly to Suspend screen
            startActivity(new Intent(this, SuspendOff.class));
            HomeScreen.this.finish();
        }
        
        else
        {

            // set up listener on the about button

            this.mAbout = ((Button)findViewById(R.id.button_about));
            this.mAbout.setOnClickListener(new View.OnClickListener() {
                public void onClick(View paramAnonymousView)
                {// call method onOptionsItemSelected using mAbout value
                    onOptionsItemSelected(mAbout);
                }
            });
            // set up listener on the settings button
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
                {// call method onOptionsItemSelected using mCancel value
                    onOptionsItemSelected(mCancel);
                }

            });
        }


    }

    public boolean onOptionsItemSelected(Button button)
    {
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



}}

