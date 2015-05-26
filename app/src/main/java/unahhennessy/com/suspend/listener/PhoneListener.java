package unahhennessy.com.suspend.listener;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class PhoneListener extends Service {
    public PhoneListener() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");

    }
}
