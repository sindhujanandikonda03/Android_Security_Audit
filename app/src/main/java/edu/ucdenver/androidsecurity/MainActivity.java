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

import android.app.ProgressDialog;
import android.app.admin.DevicePolicyManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import com.jaredrummler.android.device.DeviceName;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static edu.ucdenver.androidsecurity.AppSettings.getAppsRecommendation;
import static edu.ucdenver.androidsecurity.AppSettings.getCameraRecommendation;
import static edu.ucdenver.androidsecurity.AppSettings.getConnectionRecommendation;
import static edu.ucdenver.androidsecurity.AppSettings.getEncryptionRecommendation;
import static edu.ucdenver.androidsecurity.AppSettings.getUnknownSourcesRecommendation;
import static edu.ucdenver.androidsecurity.Config.DEVICE_ADMIN_REQUEST;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public AdminPoliciesManager adminPoliciesManager;
    protected static final int SET_PASSWORD = 2;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adminPoliciesManager = new AdminPoliciesManager(MainActivity.this);

        // App is device administrator
        if (adminPoliciesManager.getmDPM() != null && adminPoliciesManager.isAdminActive()) {
            loadHomeScreen();
        } else { // App is NOT device administrator
            loadAdminRequestScreen();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SET_PASSWORD) {
            if (resultCode == RESULT_OK || resultCode == RESULT_FIRST_USER) {
                loadHomeScreen();
            } else {
                Log.d("some error", "passcode failed!");
                adminPoliciesManager.removeAdminRights();
                Intent intent = new Intent(MainActivity.this, AdminRequestActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        }


        if (requestCode == DEVICE_ADMIN_REQUEST) {

            if (resultCode == RESULT_OK) {

                Long value = getIntent().getExtras().getLong("time1");
                System.out.println(value);
                //  long x= Long.parseLong(value);
                adminPoliciesManager.getmDPM().setMaximumTimeToLock(
                        adminPoliciesManager.getmDeviceAdmin(), value);
                //The key argument here must match that used in the other activity

                // TODO: This should configurable by the user
//                adminPoliciesManager.getmDPM().setMaximumFailedPasswordsForWipe(
//                        adminPoliciesManager.getmDeviceAdmin(), 30);

                adminPoliciesManager.getmDPM().setPasswordQuality(
                        adminPoliciesManager.getmDeviceAdmin(),
                        DevicePolicyManager.PASSWORD_QUALITY_COMPLEX);

                boolean isSufficient = adminPoliciesManager.getmDPM()
                        .isActivePasswordSufficient();

                if (isSufficient) {
                    adminPoliciesManager.getmDPM().lockNow();

                } else {
                    Intent setPasswordIntent = new Intent(

                            DevicePolicyManager.ACTION_SET_NEW_PASSWORD);

                    startActivityForResult(setPasswordIntent, SET_PASSWORD);
                    adminPoliciesManager.getmDPM().setPasswordExpirationTimeout(
                            adminPoliciesManager.getmDeviceAdmin(), 864000000L);//864000000L

                }
                loadHomeScreen();
            }

                //loadHomeScreen();
            else {
                Intent intent = new Intent(MainActivity.this, AdminRequestActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

//                setContentView(R.layout.activity_enable_admin);
//                    Button enabledAdminButton = (Button) findViewById(R.id.enabledAdminButton);
//                enabledAdminButton.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            loadAdminRequestScreen();
//                        }
//                });
            }
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent intent;

        if (id == R.id.nav_passcode) {
            intent = new Intent(this, PasscodeActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_appscan) {
            intent = new Intent(this, AppScanActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_connections) {
            intent = new Intent(this, ConnectionsActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_othersettings) {
            intent = new Intent(this, OtherSettingsActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_about) {
            intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        //setAuditRecommendations();
    }

    private void loadHomeScreen() {
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //setAuditRecommendations();

                progressDialog = ProgressDialog.show(MainActivity.this, "App Scan",
                        "Scanning apps, please wait...", true);

                new Thread(new Runnable() {
                    @Override
                    public void run()
                    {
                        Class_App_Scan app_scan = new Class_App_Scan(MainActivity.this);
                        List<String> a = app_scan.get_risky_apps();
                        String apps = a.toString();
                        AppSettings.setAppsRecommendation(MainActivity.this, apps);
                        // Update the last audit date and time
                        AppSettings.setLastAuditDate(MainActivity.this);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run()
                            {
                                TextView lastAudit = (TextView)findViewById(R.id.textview_last_audit);
                                lastAudit.setText(String.format(getString(R.string.home_textview_last_audit),
                                        AppSettings.getLastAuditDate(MainActivity.this)));
                                TextView riskyApps = (TextView)findViewById(R.id.textview_apps_recommendation);
                                riskyApps.setText(getAppsRecommendation(MainActivity.this));
                                progressDialog.dismiss();
                            }
                        });
                    }
                }).start();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        final String[] deviceName = {DeviceName.getDeviceName()};
        if (deviceName[0] == null) {
            // It's not in the local list of device name, we'll need to request this data
            DeviceName.with(this).request(new DeviceName.Callback() {
                @Override public void onFinished(DeviceName.DeviceInfo info, Exception error) {
                    deviceName[0] = info.getName();
                }
            });
        }
        String androidVersion = Utilities.getAndroidVersion();

        TextView deviceNameView = (TextView)findViewById(R.id.textview_device_information);
        deviceNameView.setText(String.format(getString(R.string.home_textview_device_information), deviceName[0], androidVersion));

        TextView lastAudit = (TextView)findViewById(R.id.textview_last_audit);
        lastAudit.setText(String.format(getString(R.string.home_textview_last_audit),
                AppSettings.getLastAuditDate(MainActivity.this)));

        setAuditRecommendations();
    }

    private void loadAdminRequestScreen() {
        Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
        intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, adminPoliciesManager.getmDeviceAdmin());
        intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION,
                getString(R.string.description_receiver));
        startActivityForResult(intent, DEVICE_ADMIN_REQUEST);
    }

    private void loadAuditRecommendations() {

        TextView textViewConnectionRecommendation =
                (TextView)findViewById(R.id.textview_connections_recommendation);
        TextView textViewCameraRecommendation =
                (TextView)findViewById(R.id.textview_camera_recommendation);
        TextView textViewUnknownRecommendation =
                (TextView)findViewById(R.id.textview_unknownsources_recommendation);
        TextView textViewEncryptionRecommendation =
                (TextView)findViewById(R.id.textview_encryption_recommendation);
        TextView textViewAppsRecommendation =
                (TextView)findViewById(R.id.textview_apps_recommendation);

        textViewConnectionRecommendation.setText(getConnectionRecommendation(MainActivity.this));
        textViewCameraRecommendation.setText(getCameraRecommendation(MainActivity.this));
        textViewUnknownRecommendation.setText(getUnknownSourcesRecommendation(MainActivity.this));
        textViewEncryptionRecommendation.setText(getEncryptionRecommendation(MainActivity.this));
        textViewAppsRecommendation.setText(getAppsRecommendation(MainActivity.this));
    }

    private void setAuditRecommendations() {

        // Update the last audit date and time
//        AppSettings.setLastAuditDate(MainActivity.this);
//        TextView lastAudit = (TextView)findViewById(R.id.textview_last_audit);
//        lastAudit.setText(String.format(getString(R.string.home_textview_last_audit),
//                AppSettings.getLastAuditDate(MainActivity.this)));

        // Auditing unsecured connections
        if (adminPoliciesManager.getUnsecuredWiFiStatus()) {
            AppSettings.setConnectionRecommendation(MainActivity.this,
                    getString(R.string.recommendation_unsecured_connection_inactive));
        } else {
            AppSettings.setConnectionRecommendation(MainActivity.this,
                    getString(R.string.recommendation_unsecured_connection_active));
        }

        // Auditing camera status
        if (adminPoliciesManager.getCameraStatus()) {
            AppSettings.setCameraRecommendation(MainActivity.this,
                    getString(R.string.recommendation_cameras_inactive));
        } else {
            AppSettings.setCameraRecommendation(MainActivity.this,
                    getString(R.string.recommendation_cameras_active));
        }

        // Auditing Unknown Sources setting
        if (adminPoliciesManager.getInstallFromUnknownSourcesValue()) {
            AppSettings.setUnknownSourcesRecommendation(MainActivity.this,
                    getString(R.string.recommendation_unknownsource_active));
        } else {
            AppSettings.setUnknownSourcesRecommendation(MainActivity.this,
                    getString(R.string.recommendation_unknownsource_inactive));
        }

        // Auditing device encryption
        if (adminPoliciesManager.getEncryptionStatus() == Utilities.EncryptionStatus.Active) {
            AppSettings.setEncryptionRecommendation(MainActivity.this,
                    getString(R.string.recommendation_encryption_active));
        } else if (adminPoliciesManager.getEncryptionStatus() == Utilities.EncryptionStatus.Inactive){
            AppSettings.setEncryptionRecommendation(MainActivity.this,
                    getString(R.string.recommendation_encryption_inactive));
        } else if (adminPoliciesManager.getEncryptionStatus() == Utilities.EncryptionStatus.Unsupported) {
            AppSettings.setEncryptionRecommendation(MainActivity.this,
                    getString(R.string.recommendation_encryption_unsupported));
        } else {
            AppSettings.setEncryptionRecommendation(MainActivity.this,
                    getString(R.string.recommendation_encryption_noinformation));
        }

        loadAuditRecommendations();
    }
}