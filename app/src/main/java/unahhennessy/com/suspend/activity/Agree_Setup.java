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

public class Agree_Setup extends Activity
{ // About is a screen about the app and its aims with info on who to contact if problems
    private Button mCancel;
    private Button mAccept;
    private static final String TAG = "Agree_Setup Activity";




    protected void onCreate(Bundle paramBundle)
    {
        this.log("entered onCreate() within Agree_Setup.java");
        super.onCreate(paramBundle);
        setContentView(R.layout.agree_setup);
        // go to the welcome screen if accept button clicked

        this.mAccept = ((Button)findViewById(R.id.button_accept));
        this.mAccept.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                onOptionsItemSelected(mAccept);
            }
        });
        //  to the screen before if cancel button clicked
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
        this.log("entered onOptionsitemSelected() within Agree_Setup.java");
        switch (button.getId())
        {

            default:
                return false;
            case R.id.button_cancel:
                // person does not want to accept terms and conditions so they cancel and leave the app
                Agree_Setup.this.finish();
                return true;
            case R.id.button_accept:
                // person accepts terms and conditions and the welcomescreen class loads
                startActivity(new Intent(this, WelcomeScreen.class));
                Agree_Setup.this.finish();
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
        Log.i(Agree_Setup.TAG, msg);

    }
}