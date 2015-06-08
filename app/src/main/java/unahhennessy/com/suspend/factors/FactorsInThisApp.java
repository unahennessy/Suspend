package unahhennessy.com.suspend.factors;
/**
 * Created by unahe_000 on 1/06/2015.
 */
import java.util.Vector;

import unahhennessy.com.suspend.other.SuspendDbHelper;

  public class FactorsInThisApp
  {
      // variables that are used in various activities in Suspend
      // written to by the SharedPreferences class in alot of the activities expecially if I want the
      // information in another activity
      public static SuspendDbHelper mSUSPEND_DB = null;
      public static String mIS_PHONE_SERVICE_ACTIVE = "is_phone_service_active";
      public static String mIS_SETUP_COMPLETE = "Is_Setup_Complete";
      public static String mIS_SETTINGSAREGOOD = "is_settingsAreGood";
      public static String mIs_BLUETOOTH_ON = "is_bluetooth_on";
      public static String mBLUETOOTH = "bluetooth";
      public static Boolean mBlueTooth_none_on_device = false;
      public static String mSUSPEND_PREF;
      public static final String mMUSIC_APP = "music_app";
      public static final String mNAVIGATION_APP = "navigation_app";

      private static final String mDATABASE_NAME = "SUSPEND_DB";
      public static int mBLUETOOTH_Count = 0;
      public static int mMUSIC_NAVIGATION = 0;
      public static int mIMAP_COUNT = 0;
      public static int mPOP_COUNT = 0;
      public static int mGMAIL_COUNT = 0;
      private static final int mDATABASE_VERSION = 1;

      public static Vector<String> accOnDevice;
       static
      {
        mSUSPEND_PREF = "Suspend_Pref";
        mIS_SETUP_COMPLETE = "Is_Setup_Complete";
        mIS_SETTINGSAREGOOD = "is_settingsAreGood";
        mIS_PHONE_SERVICE_ACTIVE = "is_phone_service_active";
        mIs_BLUETOOTH_ON = "is_bluetooth_on";
        mBlueTooth_none_on_device = false;
        mGMAIL_COUNT  = 0;
        mIMAP_COUNT = 0;
        mPOP_COUNT = 0;
        mBLUETOOTH_Count = 0;
        mMUSIC_NAVIGATION = 0;
        mSUSPEND_DB = null;
        mMUSIC_NAVIGATION = 0;
      }
  }

