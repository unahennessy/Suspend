package unahhennessy.com.suspend.constants;

import java.util.Vector;

import unahhennessy.com.suspend.util.SuspendDbHelper;

public class AppConstants
{
  public static final String CONTACT_CATEGORY = "contact_category";
  public static final String CONTACT_COUNT = "contact_count";
  public static final String CONTACT_ID = "contact_id";
  public static final String CONTACT_NAME = "contact_name";
  public static final String CONTACT_NUMBER = "contact_number";
  public static final String CUSTOM_MSG = "custom_msg";
  public static SuspendDbHelper SUSPEND_DB = null;
  public static Vector CONTACT_VEC;
  public static final String CUSTOM_MESSAGE = "custom_message";

  public static final String SUSPEND_MESSAGE_HEADER = "SUSPEND Reply";
  public static String SUSPEND_PREF;
  public static final String EDIT_EMAIL_COMPLETED = "edit_email_completed";
  public static final String EDIT_EMAIL_ID = "edit_email_id";
  public static final String EDIT_EMAIL_INDEX = "edit_email_index";
  public static final String EDIT_EMAIL_PORT = "edit_email_port";
  public static final String EDIT_EMAIL_PWD = "edit_email_pwd";
  public static final String EDIT_EMAIL_SECURITY = "edit_email_security";
  public static final String EDIT_EMAIL_SERVER = "edit_email_server";
  public static final String EDIT_EMAIL_SMTP_PORT = "edit_email_smtp_port";
  public static final String EDIT_EMAIL_SMTP_SECURITY = "edit_email_smtp_security";
  public static final String EDIT_EMAIL_SMTP_SERVER = "edit_email_smtp_server";
  public static final String EDIT_EMAIL_TYPE = "edit_email_type";
  public static final String EMAIL_CONFIGURED_COUNT = "email_configured_count";
  public static final String EMAIL_ID = "email_id";
  public static int EMAIL_IMAP_COUNT1 = 0;
  public static int EMAIL_IMAP_COUNT2 = 0;
  public static int EMAIL_POP_COUNT1 = 0;
  public static int EMAIL_POP_COUNT2 = 0;
  public static int GMAIL_EMAIL_COUNT1 = 0;
  public static int GMAIL_EMAIL_COUNT2 = 0;
  public static final String IS_SUSPEND_CALL_ACTIVE = "is_suspend_call_active";
  public static final String IS_SUSPEND_CALL_OUT_ACTIVE = "is_suspend_call_out_active";
  public static final String IS_SUSPEND_OFF_POPUP_SHOWN = "is_suspend_off_popup_shown";
  public static final String IS_SUSPEND_ON = "is_suspend_on";
  public static final String IS_SUSPEND_ON_POPUP_SHOWN = "is_suspend_on_popup_shown";
  public static final String IS_FIRST_TIME = "is_first_time";
  public static final String IS_MMS_ENABLED = "is_mms_enabled";
  public static final String IS_PHONE_ENABLED = "is_phone_enabled";
  public static String IS_PHONE_SERVICE_ACTIVE;
  public static String IS_SETUP_COMPLETE;
  public static final String IS_SMS_ENABLED = "is_sms_enabled";
  public static final String LAST_SMS_RECEIVED_MSG = "last_sms_received_msg";
  public static final String LAST_SMS_RECEIVED_NO = "last_sms_received_no";
  public static final String LAST_SMS_RECEIVED_TIME = "last_sms_received_time";
  public static final String MMS_MIME_TYPE = "application/vnd.wap.mms-message";
  public static final String MUSIC_APP = "music_app";
  public static int MUSIC_NAVIGATION = 0;
  public static final String MUSIC_PKG = "music_pkg";
  public static final String NAVIGATION_APP = "navigation_app";

  public static final String NAVIGATION_PKG = "navigation_pkg";
  public static final String STEPS_COMPLETED = "steps_completed";

  public static final int TYPE_HOME = 1;
  public static final int TYPE_MOBILE = 2;
  public static final int TYPE_OTHER = 7;
  public static final int TYPE_WORK = 3;

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

