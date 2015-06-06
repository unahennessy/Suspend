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

public class NoteListScrn extends Activity
{
    /* all set up so now you get sent to the Suspend Off screen */
    private Button mBack;
    private static final String TAG = "NoteListScrn Activity";

    protected void onCreate(Bundle paramBundle)
    {
        this.log("entered onCreate() within NoteListScrn.java");

        super.onCreate(paramBundle);
        setContentView(R.layout.setuptwocontinue);
        this.mBack = (Button) findViewById(R.id.button_continue);
        this.mBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {

                NoteListScrn.this.startActivity(new Intent(NoteListScrn.this, SuspendOff.class));
                NoteListScrn.this.finish();
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
        Log.i(NoteListScrn.TAG, msg);

    }
}
