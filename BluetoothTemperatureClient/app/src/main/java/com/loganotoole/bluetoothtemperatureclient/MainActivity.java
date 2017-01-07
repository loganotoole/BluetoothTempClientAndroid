package com.loganotoole.bluetoothtemperatureclient;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static BluetoothAdapter bTAdapter;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView)findViewById(R.id.devices_list);
        init();
    }

    private void init() {
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        bTAdapter = BluetoothAdapter.getDefaultAdapter();
        registerReceiver(bReciever, filter);
        bTAdapter.startDiscovery();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(bReciever);

    }

    private final BroadcastReceiver bReciever = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            //if (BluetoothDevice.ACTION_FOUND.equals(action)) {
            Log.d("DEVICELIST", "Bluetooth device found\n");
            BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

            if(device != null) {
                mTextView.append(device.getAddress() + "\n");
            }

            // Create a new device item
            //DeviceItem newDevice = new DeviceItem(device.getName(), device.getAddress(), "false");
            // Add it to our adapter
            //mAdapter.add(newDevice);
            //mAdapter.notifyDataSetChanged();
            //}
        }
    };
}
