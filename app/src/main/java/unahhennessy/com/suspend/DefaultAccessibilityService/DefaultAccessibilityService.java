package unahhennessy.com.suspend.DefaultAccessibilityService;

/**
 * Created by unahe_000 on 01/06/2015.
 */

import android.accessibilityservice.AccessibilityService;
import android.app.Notification;
import android.os.Build;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.widget.RemoteViews;

import java.lang.reflect.Field;
import java.util.ArrayList;


public class DefaultAccessibilityService extends AccessibilityService
{
    //public static final String FACEBOOK_PACKAGE_NAME = "com.facebook.orca";
    public static final String WHATSAPP_PACKAGE_NAME = "com.whatsapp";
    public static final String mActions = null;
    private final static String TAG = "Accessibility service";

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        this.log("entered onAccessibilityEvent() within DefaultAccessibilityService.java");
        final int eventType = event.getEventType();
        try {
            if (Build.VERSION.SDK_INT >= 16)
            {
                switch (eventType)
                {
                    case AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED:
                        Notification notification = (Notification) event.getParcelableData();
                        if (event.getPackageName().equals(WHATSAPP_PACKAGE_NAME))
                        {
                            RemoteViews views = notification.bigContentView;
                            Class<?> secretClass = views.getClass();

                            ArrayList<String> raw = new ArrayList<String>();

                            Field outerFields[] = secretClass.getDeclaredFields();
                            for (Field outerField : outerFields)
                            {
                                if (outerField.getName().equals("mActions"))
                                {
                                    outerField.setAccessible(true);
                                    ArrayList<Object> actions = null;
                                    try
                                    {
                                        actions = (ArrayList<Object>) outerField.get(views);
                                        for (Object action : actions) {
                                            Field innerFields[] = action.getClass().getDeclaredFields();

                                            Object value = null;
                                            Integer type = null;
                                            for (Field field : innerFields)
                                            {
                                                try
                                                {
                                                    field.setAccessible(true);
                                                    if (field.getName().equals("type")) {
                                                        type = field.getInt(action);
                                                    } else if (field.getName().equals("value")) {
                                                        value = field.get(action);
                                                    }
                                                }
                                                catch (IllegalArgumentException e)
                                                { e.printStackTrace();
                                                }
                                                catch (IllegalAccessException e)
                                                {  e.printStackTrace();
                                                }
                                            }

                                            if (type != null && type == 10 && value != null) {
                                                raw.add(value.toString());
                                            }
                                        }
                                    } catch (IllegalArgumentException e1)
                                    {  e1.printStackTrace();
                                    } catch (IllegalAccessException e1)
                                    {   e1.printStackTrace();
                                    }
                                }
                                parseWhatsappRawMessages(raw);

                            }
                        }
                }
            }
        } catch (IllegalArgumentException e1)
        {e1.printStackTrace();
        }
    }

    @Override
    public void onInterrupt()
    {
        this.log("entered onInterrupt() within DefaultAccessibilityService.java");
    }

    public void parseWhatsappRawMessages(ArrayList<String> raw)
    {
        this.log("entered parseWhatsappRawMessages() within DefaultAccessibilityService.java");
        int count = raw.size();
        if (count > 2) {
            Log.d(TAG, "RAW TITLE: " + raw.get(0));
            Log.d(TAG, "RAW MESSAGE: " + raw.get(1));
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
        Log.i(DefaultAccessibilityService.TAG, msg);

    }

}



