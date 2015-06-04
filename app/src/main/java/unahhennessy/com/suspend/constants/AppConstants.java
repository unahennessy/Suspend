package unahhennessy.com.suspend.constants;
/**
 * Created by unahe_000 on 1/06/2015.
 */
import java.util.Vector;

import unahhennessy.com.suspend.util.SuspendDbHelper;

public class AppConstants
{
    // variables that are used in various activities in Suspend
  public static SuspendDbHelper SUSPEND_DB = null;
  public static Vector CONTACT_VEC;
  public static String IS_PHONE_SERVICE_ACTIVE;
  public static String IS_SETUP_COMPLETE;
  public static String SUSPEND_PREF;
  public static int EMAIL_IMAP_COUNT1 = 0;
  public static int EMAIL_IMAP_COUNT2 = 0;
  public static int EMAIL_POP_COUNT1 = 0;
  public static int EMAIL_POP_COUNT2 = 0;
  public static int GMAIL_EMAIL_COUNT1 = 0;
  public static int GMAIL_EMAIL_COUNT2 = 0;
  public static final String MUSIC_APP = "music_app";
  public static final String NAVIGATION_APP = "navigation_app";
  public static int MUSIC_NAVIGATION = 0;

  public static Vector<String> accOnDevice;
   static
  {
    SUSPEND_PREF = "Suspend_Pref";
    IS_SETUP_COMPLETE = "Is_Setup_Complete";
    IS_PHONE_SERVICE_ACTIVE = "is_phone_service_active";
    GMAIL_EMAIL_COUNT1 = 0;
    GMAIL_EMAIL_COUNT2 = 0;
    EMAIL_IMAP_COUNT1 = 0;
    EMAIL_IMAP_COUNT2 = 0;
    EMAIL_POP_COUNT1 = 0;
    EMAIL_POP_COUNT2 = 0;
    MUSIC_NAVIGATION = 0;
    CONTACT_VEC = new Vector();
     }
}

