/*

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

*/

package edu.ucdenver.androidsecurity;

import android.content.Context;
import android.content.DialogInterface;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;

import static edu.ucdenver.androidsecurity.Utilities.isWiFiSecured;
import static edu.ucdenver.androidsecurity.Utilities.showToast;

public class ConnectionsActivity extends AppCompatActivity {

    private List<WifiConfiguration> savedConnections;
    private ListView mListView;
    private ArrayList<SaveConnection> savedConnectionItems;
    private ArrayAdapter<SaveConnection> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connections);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        AppCompatCheckBox checkBoxSecured =
                (AppCompatCheckBox) findViewById(R.id.checkbox_connect_only_secure);
        checkBoxSecured.setChecked(AppSettings.getSecuredWifiOnlyFlag(ConnectionsActivity.this));
        checkBoxSecured.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    AppSettings.setSecuredWifiOnlyFlag(ConnectionsActivity.this, true);
                    showToast(ConnectionsActivity.this, getString(R.string.toast_enabled_secured_wifi));
                } else {
                    AppSettings.setSecuredWifiOnlyFlag(ConnectionsActivity.this, false);
                    showToast(ConnectionsActivity.this, getString(R.string.toast_disabled_secured_wifi));
                }
            }
        });


        mListView = (ListView) findViewById(R.id.listview_saved_connections);
        WifiManager wifi = (WifiManager) getApplicationContext().getSystemService(ConnectionsActivity.this.WIFI_SERVICE);
        if (wifi.isWifiEnabled()) {
            savedConnections = wifi.getConfiguredNetworks();
            savedConnectionItems = new ArrayList<>();
            for (WifiConfiguration connection : savedConnections) {
                //connections.add(connection.SSID);
                if (isWiFiSecured(connection)) {
                    savedConnectionItems.add(new SaveConnection(connection.SSID, R.drawable.ic_lock_outline_black_24dp, connection.networkId));
                } else {
                    savedConnectionItems.add(new SaveConnection(connection.SSID, R.drawable.ic_lock_open_black_24dp, connection.networkId));
                }

            }

            adapter = new ConnectionsAdapter(ConnectionsActivity.this, 0, savedConnectionItems);
            mListView.setAdapter(adapter);
            mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                    // In Android version 6.0 and later, you're not able to delete a saved connection
                    // configuration, we will need to check.
                    if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                        // Marshmallow and later
                        AlertDialog alert = new AlertDialog.Builder(ConnectionsActivity.this).create();
                        alert.setTitle(R.string.alert_deletion_not_allowed_title);
                        alert.setIcon(R.drawable.ic_info_black_24dp);
                        alert.setMessage(String.format
                                (getString(R.string.alert_deletion_not_allowed_message), Build.VERSION.RELEASE));
                        alert.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        alert.show();

                    } else{
                        final SaveConnection saveConnection =
                                (SaveConnection) ((ListView) parent).getAdapter().getItem(position);
                        final String networkToDelete = saveConnection.getWifiName();
                        final ArrayAdapter<SaveConnection> theAdapter = adapter;
                        AlertDialog.Builder alert = new AlertDialog.Builder(ConnectionsActivity.this);
                        alert.setTitle(getString(R.string.alert_deletion_network_title));
                        alert.setIcon(R.drawable.ic_info_black_24dp);
                        alert.setMessage(String.format(getString(R.string.alert_deletion_network_message), networkToDelete));
                        alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                WifiManager wifiManager =
                                        (WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                                if (wifiManager.removeNetwork(saveConnection.getNetworkID())) {
                                    theAdapter.remove(saveConnection);
                                    theAdapter.notifyDataSetChanged();
                                    showToast(ConnectionsActivity.this,
                                            getString(R.string.toast_successful_connection_deletion));
                                } else {
                                    showToast(ConnectionsActivity.this,
                                            getString(R.string.toast_failed_connection_deletion));
                                }
                                dialog.dismiss();
                            }
                        });
                        alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        alert.show();
                    }
                    return true;
                }
            });
        } else {
            showToast(ConnectionsActivity.this, getString(R.string.toast_wifi_disabled_message));
        }
    }

}