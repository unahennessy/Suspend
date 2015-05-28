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

public class Agree_Setup extends Activity
{ // About is a screen about the app and its aims with info on who to contact if problems
    private Button mCancel;
    private Button mAccept;

    protected void onCreate(Bundle paramBundle)
    {

        super.onCreate(paramBundle);
        setContentView(R.layout.about);
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
        switch (button.getId())
        {
            default:
                return false;
            case R.id.button_cancel:
                Agree_Setup.this.finish();
                return true;
            case R.id.button_accept:
                startActivity(new Intent(this, WelcomeScreen.class));
                Agree_Setup.this.finish();
                return true;
        }
    }
}