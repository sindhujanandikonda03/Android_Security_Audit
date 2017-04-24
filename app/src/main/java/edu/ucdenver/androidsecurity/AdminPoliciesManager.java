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
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;

import static android.app.admin.DevicePolicyManager.ENCRYPTION_STATUS_ACTIVATING;
import static android.app.admin.DevicePolicyManager.ENCRYPTION_STATUS_ACTIVE;
import static android.app.admin.DevicePolicyManager.ENCRYPTION_STATUS_ACTIVE_DEFAULT_KEY;
import static android.app.admin.DevicePolicyManager.ENCRYPTION_STATUS_ACTIVE_PER_USER;
import static android.app.admin.DevicePolicyManager.ENCRYPTION_STATUS_INACTIVE;
import static android.app.admin.DevicePolicyManager.ENCRYPTION_STATUS_UNSUPPORTED;

public class AdminPoliciesManager {

    private DevicePolicyManager mDPM;
    private ComponentName mDeviceAdmin;
    private Context context;

    public AdminPoliciesManager(Context thecontext) {
        context = thecontext;
        mDPM = (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);
        mDeviceAdmin = new ComponentName(context, DeviceSecurityAdminReceiver.class);
    }

    public void setCameraDisabled(boolean value) {
        mDPM.setCameraDisabled(mDeviceAdmin, value);
    }

    public void setEncryptionPolicy(boolean value) {
        mDPM.setStorageEncryption(mDeviceAdmin, value);
    }

    public boolean isStorageEncrypted() {
        int encryptStatusInt = mDPM.getStorageEncryptionStatus();
        boolean status;

        if (encryptStatusInt == ENCRYPTION_STATUS_ACTIVATING ||
                encryptStatusInt == ENCRYPTION_STATUS_ACTIVE ||
                encryptStatusInt == ENCRYPTION_STATUS_ACTIVE_DEFAULT_KEY ||
                encryptStatusInt == ENCRYPTION_STATUS_ACTIVE_PER_USER) {
            status = true;
        } else {
            status = false;
        }
        return status;
    }

    public boolean encryptDevice() {
        int encryptStatusInt = mDPM.getStorageEncryptionStatus();
        AlertDialog alert = new AlertDialog.Builder(context).create();
        //We're only going to try to encrypt file is encryption is supported
        if (encryptStatusInt == ENCRYPTION_STATUS_INACTIVE) {
            mDPM.setStorageEncryption(mDeviceAdmin, true);
            return true;
        }
        else if (encryptStatusInt == ENCRYPTION_STATUS_UNSUPPORTED) {
            alert.setTitle(R.string.alert_encryption_unsupported_title);
            alert.setIcon(R.drawable.ic_info_black_24dp);
            alert.setMessage(context.getString(R.string.alert_encryptiion_unsupported_message));
            alert.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alert.show();
        }
        else if (encryptStatusInt == ENCRYPTION_STATUS_ACTIVATING ||
                encryptStatusInt == ENCRYPTION_STATUS_ACTIVE ||
                encryptStatusInt == ENCRYPTION_STATUS_ACTIVE_DEFAULT_KEY ||
                encryptStatusInt == ENCRYPTION_STATUS_ACTIVE_PER_USER) {

            alert.setTitle(R.string.alert_encrypted_title);
            alert.setIcon(R.drawable.ic_info_black_24dp);
            alert.setMessage(context.getString(R.string.alert_encrypted_message));
            alert.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alert.show();
        }
        return false;
    }

    public boolean getInstallFromUnknownSourcesValue() {
        // Let's always assume it's true unless proven otherwise, which is the safe approach
        boolean isUnknownSources = true;
        try {
            isUnknownSources = android.provider.Settings.Secure
                    .getInt(context.getContentResolver(),
                            Settings.Secure.INSTALL_NON_MARKET_APPS) == 1;
        } catch (Settings.SettingNotFoundException e) {
            // TODO: handle exception
        }
        return isUnknownSources;
    }

    public boolean getUnsecuredWiFiStatus() {
        return AppSettings.getSecuredWifiOnlyFlag(context);
    }

    public boolean getCameraStatus() {
        return AppSettings.getDisableCamerasFlag(context);
    }

    public Utilities.EncryptionStatus getEncryptionStatus() {
        int encryptStatusInt = mDPM.getStorageEncryptionStatus();
        if (encryptStatusInt == ENCRYPTION_STATUS_INACTIVE) {
            return Utilities.EncryptionStatus.Inactive;
        }
        else if (encryptStatusInt == ENCRYPTION_STATUS_UNSUPPORTED) {
            return Utilities.EncryptionStatus.Unsupported;
        }
        return Utilities.EncryptionStatus.Active;
    }

    public DevicePolicyManager getmDPM() {
        return mDPM;
    }

    public ComponentName getmDeviceAdmin() {
        return mDeviceAdmin;
    }

    public boolean isAdminActive() {
        return mDPM.isAdminActive(mDeviceAdmin);
    }

    public void removeAdminRights() {
        this.getmDPM().removeActiveAdmin(this.getmDeviceAdmin());
    }
}
