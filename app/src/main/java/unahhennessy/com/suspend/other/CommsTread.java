package unahhennessy.com.suspend.other;

/**
 * Created by unahe_000 on 08/06/2015 unahhennessy.com.suspend.other Suspend.
 */

import android.bluetooth.BluetoothSocket;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import unahhennessy.com.suspend.activity.BlueToothActivity;


    public class CommsTread extends Thread {
        final BluetoothSocket bluetoothSocket;
        final InputStream inputStream;
        final OutputStream outputStream;
        public CommsTread(BluetoothSocket socket) {
            bluetoothSocket = socket;
            InputStream tmpIn = null;
            OutputStream tmpOut = null;
            try {
                    //---creates the inputstream and outputstream objects
                    // for reading and writing through the sockets---
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) {
                Log.d("CommsThread", e.getLocalizedMessage());
            }
            inputStream = tmpIn;
            outputStream = tmpOut;
        }
        public void run() {
            //---buffer store for the stream---
            byte[] buffer = new byte[1024];
            //---bytes returned from read()---
            int bytes;
            //---keep listening to the InputStream until an
            // exception occurs---
            while (true) {
                try {
                //---read from the inputStream---
                    bytes = inputStream.read(buffer);
                //---update the main activity UI---
                    BlueToothActivity.UIupdater.obtainMessage(0,bytes, -1,
                            buffer).sendToTarget();
                } catch (IOException e) {
                    Log.d("CommsThread", e.getLocalizedMessage());
                    break;
                }
            }
        }
                    //---call this from the main Activity to
                    // send data to the remote device---



        public void write(String str) {
            try {
                outputStream.write(str.getBytes());
            } catch (IOException e) {
                Log.d("CommsThread", e.getLocalizedMessage());
            }
        }
                 //---call this from the main Activity to
                    // shutdown the connection---
        public void cancel() {
            try {
                bluetoothSocket.close();
            } catch (IOException e) {
                Log.d("CommsThread", e.getLocalizedMessage());
            }
        }
    }
