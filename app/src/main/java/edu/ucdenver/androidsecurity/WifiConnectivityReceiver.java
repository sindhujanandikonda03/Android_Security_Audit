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

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import static android.net.wifi.WifiConfiguration.KeyMgmt.IEEE8021X;
import static android.net.wifi.WifiConfiguration.KeyMgmt.WPA_EAP;
import static android.net.wifi.WifiConfiguration.KeyMgmt.WPA_PSK;
import static edu.ucdenver.androidsecurity.Utilities.isWiFiSecured;

public class WifiConnectivityReceiver extends BroadcastReceiver {

    private static final String TAG = "WiFiReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "Wifi state changed!");
        WifiConfiguration activeWifi = null;
        WifiManager wifiManager = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
        // Need to check if the WiFi is enabled, otherwise if user disables the WiFi the app crashes
        if (wifiManager.isWifiEnabled()) {
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            if (wifiInfo != null) {
                for (WifiConfiguration wifiConfiguration : wifiManager.getConfiguredNetworks()) {
                    if (wifiConfiguration.status == WifiConfiguration.Status.CURRENT) {
                        activeWifi = wifiConfiguration;
                        break;
                    }
                }
                if (activeWifi != null) {
                    if (isWiFiSecured(activeWifi)) {
                        Log.d(TAG, "Wifi is secured!");
                    } else {
                        Log.d(TAG, "Wifi is not secured");
                        if (AppSettings.getSecuredWifiOnlyFlag(context)) {
                            Log.d(TAG, "Disconnecting from unsecured WiFi");
                            wifiManager.disconnect();
                            displayNotification(context);
                        } else {
                            Log.d(TAG, "Connecting to unsecured Wifi");
                        }
                    }
                }
            }
        }
    }

    private void displayNotification(Context context) {
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
                new Intent(context, MainActivity.class), 0);
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);

        NotificationCompat.Builder mBuilder =
                (NotificationCompat.Builder) new NotificationCompat.Builder(context)
                        .setContentTitle
                            (context.getString(R.string.notification_title_disconnect_unsecured_wifi))
                        .setContentText
                            (context.getString(R.string.notification_message_disconnect_unsecured_wifi));
        // Lollipop requires a white logo withh transparency
        if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            mBuilder.setSmallIcon(R.mipmap.ic_launcher);
        } else {
            mBuilder.setSmallIcon(R.drawable.notification_icon);
        }
        mBuilder.setContentIntent(contentIntent);
        mBuilder.setDefaults(Notification.DEFAULT_SOUND);
        mBuilder.setAutoCancel(true);
        //mBuilder.setSound(alarmSound);
        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(1, mBuilder.build());

    }
}
