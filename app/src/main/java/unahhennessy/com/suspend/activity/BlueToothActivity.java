package unahhennessy.com.suspend.activity;

import android.app.ListActivity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import unahhennessy.com.suspend.other.ConnectToServerThread;
import unahhennessy.com.suspend.other.ServerThread;

/**
 * Created by unahe_000 on 08/06/2015 unahhennessy.com.suspend.activity Suspend.
 */
public class BlueToothActivity extends ListActivity
{
    @Override
    public void onResume() {
        super.onResume();
//---start the socket server---
        serverThread = new ServerThread(bluetoothAdapter);
        serverThread.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        //---cancel discovery of other bluetooth devices
        bluetoothAdapter.cancelDiscovery();
        //---unregister the broadcast receiver for
        // discovering devices---
        if (discoverDevicesReceiver != null) {
            try {
                unregisterReceiver(discoverDevicesReceiver);
            } catch(Exception e) {
            }
        }
            //---if you are currently connected to someone...---
        if (connectToServerThread!=null)
        {
            try {
                    //---close the connection---
                connectToServerThread.bluetoothSocket.close();
                 }
            catch (IOException e)
            {
                Log.d("BlueToothActivity", e.getLocalizedMessage());
            }
        }
            //---stop the thread running---
        if (serverThread!=null) serverThread.cancel();
    }

    //---when a client is tapped in the ListView---
    public void onListItemClick(ListView parent, View v,
                                int position, long id) {
//---if you are already talking to someone...---
        if (connectToServerThread!=null) {
            try {
//---close the connection first---
                connectToServerThread.bluetoothSocket.close();
            } catch (IOException e) {
                Log.d("MainActivity", e.getLocalizedMessage());
            }
        }
//---connect to the selected Bluetooth device---
        BluetoothDevice deviceSelected = discoveredDevices.get(position);
        connectToServerThread = new
                ConnectToServerThread(deviceSelected, bluetoothAdapter);
        connectToServerThread.start();
//---tell the user that he is connected to who---
        Toast.makeText(this, "You have connected to " +
                        discoveredDevices.get(position).getName(),
                Toast.LENGTH_SHORT).show();
    }


        public final static String UUID = "3606f360-e4df-11e0-9572-0800200c9a66";
        BluetoothAdapter bluetoothAdapter;
        BroadcastReceiver discoverDevicesReceiver;
        BroadcastReceiver discoveryFinishedReceiver;
        //---store all the discovered devices---
        ArrayList<BluetoothDevice> discoveredDevices;
        ArrayList<String> discoveredDevicesNames;
        static TextView txtData;
        EditText txtMessage;
        //---thread for running the server socket---
        ServerThread serverThread;
        //---thread for connecting to the client socket---
        ConnectToServerThread connectToServerThread;
    //---used to discover other bluetooth devices---
    private void DiscoveringDevices()
    {
        if (discoverDevicesReceiver == null) {
            discoverDevicesReceiver = new BroadcastReceiver() {
                //---fired when a new device is discovered---
                @Override
                public void onReceive(Context context, Intent intent) {
                    String action = intent.getAction();
                        //---a device is discovered---
                    if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                        //---get the BluetoothDevice object from
                        // the Intent---

                        BluetoothDevice device =
                                intent.getParcelableExtra(
                                        BluetoothDevice.EXTRA_DEVICE);
                    //---add the name and address to an array
                    // adapter to show in a ListView---
                    //---only add if the device is not already
                       // in the list---
                        if (!discoveredDevices.contains(device)) {
                    //---add the device---
                            discoveredDevices.add(device);
                        //---add the name of the device; used for
                        // ListView---
                            discoveredDevicesNames.add(device.getName());
                        //---display the items in the ListView---
                            setListAdapter(new
                                    ArrayAdapter<String>(getBaseContext(),
                                    android.R.layout.simple_list_item_1,
                                    discoveredDevicesNames));
                        }
                    }
                }
            };
        }
        if (discoveryFinishedReceiver==null) {
            discoveryFinishedReceiver = new BroadcastReceiver() {
                //---fired when the discovery is done---
                @Override
                public void onReceive(Context context, Intent intent) {
                //---enable the listview when discovery is over;
                // about 12 seconds---
                    getListView().setEnabled(true);
                    Toast.makeText(getBaseContext(),
                            "Discovery completed. Select a device to " +
                                    "start chatting.",
                            Toast.LENGTH_LONG).show();
                    unregisterReceiver(discoveryFinishedReceiver);
                }
            };
        }
            //---register the broadcast receivers---
        IntentFilter filter1 = new
                IntentFilter(BluetoothDevice.ACTION_FOUND);
        IntentFilter filter2 = new
                IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        registerReceiver(discoverDevicesReceiver, filter1);
        registerReceiver(discoveryFinishedReceiver, filter2);
        //---disable the listview when discover is in progress---
        getListView().setEnabled(false);
        Toast.makeText(getBaseContext(),
                "Discovery in progress...please wait...",
                Toast.LENGTH_LONG).show();
        bluetoothAdapter.startDiscovery();
    }
    //---discover other bluetooth devices---
    public void DiscoverDevices(View view)
    {
        //---discover other devices---
        DiscoveringDevices();
    }
    //---used for updating the UI on the main activity---
    public static Handler UIupdater = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int numOfBytesReceived = msg.arg1;
            byte[] buffer = (byte[]) msg.obj;
        //---convert the entire byte array to string---
            String strReceived = new String(buffer);
        //---extract only the actual string received---
            strReceived = strReceived.substring(
                    0, numOfBytesReceived);
        //---display the text received on the TextView---
            txtData.setText(txtData.getText().toString() +
                    strReceived);
        }
    };

    private class WriteTask extends AsyncTask<String, Void, Void> {
        protected Void doInBackground(String... args) {
            try {
                connectToServerThread.commsThread.write(args[0]);
            } catch (Exception e) {
                Log.d("BlueToothActivity", e.getLocalizedMessage());
            }
            return null;
        }
    }
    //---send a message to the connected socket client---
    public void SendMessage(View view)
    {
        if (connectToServerThread!=null) {
            new WriteTask().execute(txtMessage.getText().toString());
        } else {
            Toast.makeText(this, "Select a client first",
                    Toast.LENGTH_SHORT).show();
        }
    }


}
