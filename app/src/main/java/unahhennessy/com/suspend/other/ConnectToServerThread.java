package unahhennessy.com.suspend.other;

/**
 * Created by unahe_000 on 08/06/2015 unahhennessy.com.suspend.other Suspend.
 */

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

import java.io.IOException;
import java.util.UUID;

import unahhennessy.com.suspend.activity.BlueToothActivity;


public class ConnectToServerThread extends Thread {
        public CommsTread commsThread;
        public BluetoothSocket bluetoothSocket;
        private BluetoothAdapter bluetoothAdapter;
        public ConnectToServerThread(BluetoothDevice device,
                                     BluetoothAdapter btAdapter) {
            BluetoothSocket tmp = null;
            bluetoothAdapter = btAdapter;
//---get a BluetoothSocket to connect with the given
// BluetoothDevice---
            try {
//---UUID must be the same for both the client and
// the server---
                tmp = device.createRfcommSocketToServiceRecord(
                        UUID.fromString(BlueToothActivity.UUID));
            } catch (IOException e) {
                Log.d("ConnectToServerThread", e.getLocalizedMessage());
            }
            bluetoothSocket = tmp;
        }
        public void run() {
//---cancel discovery because it will slow down the
// connection---
            bluetoothAdapter.cancelDiscovery();
            try {
//---connect the device through the socket. This will
// block until it succeeds or throws an exception---
                bluetoothSocket.connect();
//---create a thread for the communication channel---
                commsThread = new CommsTread(
                        bluetoothSocket);
                commsThread.start();
            } catch (IOException connectException) {
//---unable to connect; close the socket and get out---
                try {
                    bluetoothSocket.close();
                } catch (IOException closeException) {
                    Log.d("ConnectToServerThread",




                closeException.getLocalizedMessage());
            }
            return;
        }
    }
        public void cancel() {
            try {
                bluetoothSocket.close();
                if (commsThread!=null) commsThread.cancel();
            } catch (IOException e) {
                Log.d("ConnectToServerThread", e.getLocalizedMessage());
            }
        }
    }
