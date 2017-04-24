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

import android.app.admin.DevicePolicyManager;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatCheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import static edu.ucdenver.androidsecurity.Config.REQUEST_CODE_START_ENCRYPTION;
import static edu.ucdenver.androidsecurity.Utilities.showToast;

public class OtherSettingsActivity extends AppCompatActivity {

    AdminPoliciesManager adminPoliciesManager;
    AppCompatCheckBox checkboxUnknownSources;
    AppCompatCheckBox checkboxDisableCameras;
    AppCompatCheckBox checkboxEncryptDevice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_settings);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        adminPoliciesManager = new AdminPoliciesManager(OtherSettingsActivity.this);

        checkboxUnknownSources = (AppCompatCheckBox) findViewById(R.id.checkbox_unknown_sources);
        checkboxUnknownSources
                .setChecked(adminPoliciesManager.getInstallFromUnknownSourcesValue());
        checkboxUnknownSources.setEnabled(false); // read only

        checkboxDisableCameras = (AppCompatCheckBox) findViewById(R.id.checkbox_disable_cameras);
        checkboxDisableCameras.setChecked(AppSettings.getDisableCamerasFlag(OtherSettingsActivity.this));
        checkboxDisableCameras.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    AppSettings.setDisableCamerasFlag(OtherSettingsActivity.this, true);
                    showToast(OtherSettingsActivity.this, getString(R.string.toast_disabled_cameras_message));
                } else {
                    AppSettings.setDisableCamerasFlag(OtherSettingsActivity.this, false);
                    showToast(OtherSettingsActivity.this, getString(R.string.toast_enabled_cameras_message));
                }
                adminPoliciesManager.setCameraDisabled(AppSettings.getDisableCamerasFlag(OtherSettingsActivity.this));
            }
        });

        checkboxEncryptDevice =
                (AppCompatCheckBox) findViewById(R.id.checkbox_encrypt_device);
        checkboxEncryptDevice.setChecked(adminPoliciesManager.isStorageEncrypted());
        if (adminPoliciesManager.isStorageEncrypted()) {
            checkboxEncryptDevice.setEnabled(false);
            TextView textView = (TextView)findViewById(R.id.textview_encrypt_device);
            textView.setText(R.string.othersettings_textview_encrypt_device_ro);
            textView.setTextColor(getResources().getColor(R.color.colorPrimaryDisable));
        }
        checkboxEncryptDevice.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // There is a weird bug that causes the onCheckedChanged event to fired
                // when the buttonView is not pressed! The solution is to make sure that the
                // buttonView was pressed before we do anything here!
                // Found this at...
                // http://stackoverflow.com/questions/27641705/oncheckedchanged-called-automatically
                if (buttonView.isPressed()) {
                    if (isChecked) {
                        if(adminPoliciesManager.encryptDevice()) {
                            Intent intent = new Intent(DevicePolicyManager.ACTION_START_ENCRYPTION);
                            startActivityForResult(intent, REQUEST_CODE_START_ENCRYPTION);
                        }
                    }
                }
                checkboxEncryptDevice.setChecked(adminPoliciesManager.isStorageEncrypted());
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Setting the values of the checkboxes in case the user has changed the settings externally
        checkboxUnknownSources
                .setChecked(adminPoliciesManager.getInstallFromUnknownSourcesValue());
        checkboxEncryptDevice.setChecked(AppSettings.getSecuredWifiOnlyFlag(OtherSettingsActivity.this));
        checkboxDisableCameras.setChecked(AppSettings.getDisableCamerasFlag(OtherSettingsActivity.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_START_ENCRYPTION) {
            if (resultCode == RESULT_OK) {
                showToast(OtherSettingsActivity.this, getString(R.string.toast_successful_encryption));
            } else {
                showToast(OtherSettingsActivity.this, getString(R.string.toast_failed_encryption));
            }
        }
    }
}
