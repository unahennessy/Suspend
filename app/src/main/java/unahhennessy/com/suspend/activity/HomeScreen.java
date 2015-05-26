package unahhennessy.com.suspend.activity;
/**
 * Created by unahe_000 on 21/05/2015.
 */
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import unahhennessy.com.suspend.R;
import unahhennessy.com.suspend.constants.AppConstants;
public class HomeScreen   extends Activity
{
  private Thread mSplashThread;
  private SharedPreferences prefs = null;
     @Override
    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        this.prefs = getSharedPreferences(AppConstants.SUSPEND_PREF, 0);
        setContentView(R.layout.splashscreen);
        this.mSplashThread = new Thread();
        setContentView(R.layout.splashscreen);

        mSplashThread =  new Thread(){
            @Override
            public void run(){
                try {
                    synchronized(this){
                        wait(3000);
                    }
                }
                catch(InterruptedException ex){
                }
                finish();

                Intent intent = new Intent();
                intent.setClass(HomeScreen.this, HomeScreen.class);
                startActivity(intent);
            }
        };

        mSplashThread.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent evt)
    {
        if(evt.getAction() == MotionEvent.ACTION_DOWN)
        {
            synchronized(mSplashThread){
                mSplashThread.notifyAll();
            }
        }
        return true;
    }
}