package unahhennessy.com.suspend.factors;
/**
 * Created by unahe_000 on 1/06/2015.
 */
import java.util.Vector;

import unahhennessy.com.suspend.other.SuspendDbHelper;

public class FactorsInThisApp
{
    // variables that are used in various activities in Suspend
  public static SuspendDbHelper mSUSPEND_DB = null;
  public static Vector mCONTACT_VEC;
  public static String mIS_PHONE_SERVICE_ACTIVE;
  public static String mIS_SETUP_COMPLETE;
  public static String mSUSPEND_PREF;
  public static int mIMAP_COUNT = 0;
  public static int mPOP_COUNT = 0;
  public static int mGMAIL_COUNT = 0;
  public static final String mMUSIC_APP = "music_app";
  public static final String mNAVIGATION_APP = "navigation_app";
  public static int mMUSIC_NAVIGATION = 0;

  public static Vector<String> accOnDevice;
   static
  {
    mSUSPEND_PREF = "Suspend_Pref";
    mIS_SETUP_COMPLETE = "Is_Setup_Complete";
    mIS_PHONE_SERVICE_ACTIVE = "is_phone_service_active";
    mGMAIL_COUNT  = 0;
    mIMAP_COUNT = 0;
    mPOP_COUNT = 0;
    mMUSIC_NAVIGATION = 0;
    mCONTACT_VEC = new Vector();
  }
}

