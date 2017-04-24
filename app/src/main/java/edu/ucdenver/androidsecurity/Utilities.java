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
import android.net.wifi.WifiConfiguration;
import android.os.Build;
import android.provider.Settings;
import android.widget.Toast;

import static android.net.wifi.WifiConfiguration.KeyMgmt.IEEE8021X;
import static android.net.wifi.WifiConfiguration.KeyMgmt.WPA_EAP;
import static android.net.wifi.WifiConfiguration.KeyMgmt.WPA_PSK;
import static android.net.wifi.WifiConfiguration.KeyMgmt.NONE;

public class Utilities {

    public static void showToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    /**
     * Determines if the WifiiConfiguration object represents a secured connection
     * @param wifiConfiguration
     * @return TRUE if the connection is secured, FALSE otherwise
     */
    public static boolean isWiFiSecured(WifiConfiguration wifiConfiguration) {

        // There is no key management scheme
        if (wifiConfiguration.allowedKeyManagement.get(NONE)) {
            return false;
        } else {
            return true;
        }
        // TODO: this is crashing the app....
//        if (wifiConfiguration.allowedKeyManagement.get(WPA_EAP) ||
//                wifiConfiguration.allowedKeyManagement.get(IEEE8021X) ||
//                wifiConfiguration.allowedKeyManagement.get(WPA_PSK)) {
//            return true;
//        }
//        return false;
    }

    public static String getAndroidVersion() {
        String osVersion = Build.VERSION.RELEASE;
        String androidName;
        switch(Build.VERSION.SDK_INT) {
            case(Build.VERSION_CODES.KITKAT):
                androidName = "KitKat";
                break;
            case(Build.VERSION_CODES.LOLLIPOP):
                androidName = "Lollipop";
                break;
            case(Build.VERSION_CODES.LOLLIPOP_MR1):
                androidName = "Lollipop";
                break;
            case(Build.VERSION_CODES.M):
                androidName = "Marshmallow";
                break;
            case(Build.VERSION_CODES.N_MR1):
                androidName = "Marshmallow";
                break;
            case(Build.VERSION_CODES.N):
                androidName = "Nougat";
                break;
            default:
                androidName = "Unknown";
                break;
        }
        return osVersion + " (" + androidName + ")";
    }

    public enum EncryptionStatus {
        Inactive,
        Unsupported,
        Active
    }
}
