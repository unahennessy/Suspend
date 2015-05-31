package unahhennessy.com.suspend.activity;
/**
 * Created by unahe_000 on 21/05/2015.
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
    {
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
        //initialize listener
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
            this.mAbout.setOnClickListener(new View.OnClickListener() {
                public void onClick(View paramAnonymousView)
                {
                    onOptionsItemSelected(mAbout);
                }
            });
            // set up listener on the settings button
            this.mAgree = ((Button)findViewById(R.id.btn_agree));
            this.mAgree.setOnClickListener(new View.OnClickListener() {
                public void onClick(View paramAnonymousView)
                {
                    onOptionsItemSelected(mAgree);
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

        if (mIsSettingsCheckbox_checked)
        {
            startActivity(new Intent(this, SuspendOff.class));
            HomeScreen.this.finish();
        }
        
        else
        {

            // set up listener on the about button

            this.mAbout = ((Button)findViewById(R.id.button_about));
            this.mAbout.setOnClickListener(new View.OnClickListener() {
                public void onClick(View paramAnonymousView)
                {
                    onOptionsItemSelected(mAbout);
                }
            });
            // set up listener on the settings button
            this.mAgree = ((Button)findViewById(R.id.btn_agree));
            this.mAgree.setOnClickListener(new View.OnClickListener() {
                public void onClick(View paramAnonymousView)
                {
                    onOptionsItemSelected(mAgree);
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
            case R.id.btn_agree:
                startActivity(new Intent(this, Agree_Setup.class));
                HomeScreen.this.finish();
                return true;
        }



}}

