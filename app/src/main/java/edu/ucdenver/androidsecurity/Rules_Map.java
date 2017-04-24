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

import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Rules_Map {
    Map<Integer,Map<String,Boolean>> rules = new HashMap<Integer,Map<String,Boolean>>();
    Map<Integer,Double> risk_value = new HashMap<>();

    Rules_Map()
    {
        Create_Rules();
        create_risk_values();
    }

    public void Create_Rules()
    {
        Map<String,Boolean> set= new HashMap<>();

        //rule 281:
        set.put("android.permission.READ_SMS",Boolean.TRUE);
        set.put("android.permission.INTERNET",Boolean.TRUE);
        set.put("android.permission.READ_EXTERNAL_STORAGE",Boolean.FALSE);
        set.put("android.permission.WRITE_EXTERNAL_STORAGE",Boolean.TRUE);
        rules.put(281,new HashMap(set));
        set.clear();

        //rule 189:
        set.put("android.permission.SEND_SMS",Boolean.TRUE);
        set.put("android.permission.WRITE_SMS",Boolean.FALSE);
        set.put("android.permission.READ_EXTERNAL_STORAGE",Boolean.FALSE);
        set.put("android.permission.WRITE_EXTERNAL_STORAGE",Boolean.TRUE);
        rules.put(189,new HashMap(set));
        set.clear();

        //Rule 286:
        set.put("android.permission.WAKE_LOCK",Boolean.TRUE);
        set.put("android.permission.CALL_PHONE",Boolean.FALSE);
        set.put("android.permission.READ_SMS",Boolean.TRUE);
        set.put("android.permission.READ_PHONE_STATE",Boolean.TRUE);
        set.put("android.permission.SEND_SMS",Boolean.TRUE);
        set.put("android.permission.ACCESS_FINE_LOCATION",Boolean.FALSE);
        set.put("android.permission.ACCESS_NETWORK_STATE",Boolean.FALSE);
        set.put("android.permission.READ_CONTACTS",Boolean.FALSE);
        rules.put(286,new HashMap(set));
        set.clear();

        //Rule 261:
        set.put("android.permission.BROADCAST_STICKY",Boolean.FALSE);
        set.put("android.permission.CALL_PHONE",Boolean.TRUE);
        set.put("android.permission.DISABLE_KEYGUARD",Boolean.TRUE);
        set.put("android.permission.READ_SMS",Boolean.TRUE);
        set.put("android.permission.READ_EXTERNAL_STORAGE",Boolean.FALSE);
        set.put("android.permission.READ_CALL_LOG",Boolean.FALSE);
        set.put("android.permission.RECEIVE_WAP_PUSH",Boolean.FALSE);
        set.put("android.permission.READ_CONTACTS",Boolean.TRUE);
        rules.put(261,new HashMap(set));
        set.clear();

        //Rule 109:
        set.put("android.permission.CHANGE_WIFI_STATE",Boolean.FALSE);
        set.put("android.permission.READ_EXTERNAL_STORAGE",Boolean.FALSE);
        set.put("android.permission.SYSTEM_ALERT_WINDOW",Boolean.TRUE);
        set.put("android.permission.WRITE_HISTORY_BOOKMARKS",Boolean.FALSE);
        set.put("android.permission.WRITE_EXTERNAL_STORAGE",Boolean.TRUE);
        rules.put(109,new HashMap(set));
        set.clear();

        //Rule 266:
        set.put("android.permission.READ_HISTORY_BOOKMARKS",Boolean.FALSE);
        set.put("android.permission.READ_SMS",Boolean.TRUE);
        set.put("android.permission.READ_EXTERNAL_STORAGE",Boolean.FALSE);
        set.put("android.permission.READ_CALL_LOG",Boolean.FALSE);
        set.put("android.permission.ACCESS_WIFI_STATE",Boolean.TRUE);
        set.put("android.permission.WRITE_SMS",Boolean.TRUE);
        set.put("android.permission.RECEIVE_SMS",Boolean.FALSE);
        rules.put(266,new HashMap(set));
        set.clear();

        //Rule 206:
        set.put("android.permission.INTERNET",Boolean.TRUE);
        set.put("android.permission.READ_SMS",Boolean.FALSE);
        set.put("android.permission.SEND_SMS",Boolean.TRUE);
        set.put("android.permission.READ_EXTERNAL_STORAGE",Boolean.FALSE);
        set.put("android.permission.RECEIVE_BOOT_COMPLETED",Boolean.TRUE);
        set.put("android.permission.READ_CALL_LOG",Boolean.FALSE);
        set.put("android.permission.ACCESS_FINE_LOCATION",Boolean.FALSE);
        set.put("android.permission.ACCESS_NETWORK_STATE",Boolean.FALSE);
        set.put("android.permission.RECEIVE_SMS",Boolean.TRUE);
        rules.put(206,new HashMap(set));
        set.clear();

        //Rule 212:
        set.put("android.permission.RESTART_PACKAGES",Boolean.TRUE);
        set.put("android.permission.READ_SMS",Boolean.FALSE);
        set.put("android.permission.SEND_SMS",Boolean.TRUE);
        set.put("android.permission.READ_EXTERNAL_STORAGE",Boolean.FALSE);
        set.put("android.permission.READ_CALL_LOG",Boolean.FALSE);
        set.put("android.permission.ACCESS_NETWORK_STATE",Boolean.FALSE);
        set.put("android.permission.RECEIVE_SMS",Boolean.TRUE);
        rules.put(212,new HashMap(set));
        set.clear();

        //Rule 293:
        set.put("android.permission.RECORD_AUDIO",Boolean.FALSE);
        set.put("android.permission.VIBRATE",Boolean.FALSE);
        set.put("android.permission.CHANGE_WIFI_STATE",Boolean.TRUE);
        set.put("android.permission.BLUETOOTH_ADMIN",Boolean.FALSE);
        set.put("android.permission.READ_SMS",Boolean.TRUE);
        set.put("android.permission.READ_CALL_LOG",Boolean.FALSE);
        set.put("android.permission.READ_CONTACTS",Boolean.TRUE);
        rules.put(293,new HashMap(set));
        set.clear();

        //Rule 215:
        set.put("android.permission.INTERNET",Boolean.TRUE);
        set.put("android.permission.VIBRATE",Boolean.FALSE);
        set.put("android.permission.CALL_PHONE",Boolean.FALSE);
        set.put("android.permission.READ_PHONE_STATE",Boolean.TRUE);
        set.put("android.permission.SEND_SMS",Boolean.TRUE);
        set.put("android.permission.READ_EXTERNAL_STORAGE",Boolean.FALSE);
        set.put("android.permission.READ_CALL_LOG",Boolean.FALSE);
        set.put("android.permission.ACCESS_FINE_LOCATION",Boolean.FALSE);
        set.put("android.permission.ACCESS_NETWORK_STATE",Boolean.FALSE);
        set.put("android.permission.GET_ACCOUNTS",Boolean.FALSE);
        set.put("android.permission.RECEIVE_SMS",Boolean.TRUE);
        rules.put(215,new HashMap(set));
        set.clear();

        //Rule 44:
        set.put("android.permission.WAKE_LOCK",Boolean.TRUE);
        set.put("android.permission.READ_EXTERNAL_STORAGE",Boolean.FALSE);
        set.put("android.permission.ACCESS_NETWORK_STATE",Boolean.FALSE);
        set.put("android.permission.WRITE_EXTERNAL_STORAGE",Boolean.TRUE);
        rules.put(44,new HashMap(set));
        set.clear();

        //Rule 280:
        set.put("android.permission.RESTART_PACKAGES",Boolean.TRUE);
        set.put("android.permission.READ_SMS",Boolean.TRUE);
        set.put("android.permission.READ_EXTERNAL_STORAGE",Boolean.FALSE);
        set.put("android.permission.READ_CALL_LOG",Boolean.FALSE);
        set.put("android.permission.ACCESS_NETWORK_STATE",Boolean.TRUE);
        rules.put(280,new HashMap(set));
        set.clear();

        //Rule 277:
        set.put("android.permission.CHANGE_WIFI_STATE",Boolean.FALSE);
        set.put("android.permission.READ_SMS",Boolean.TRUE);
        set.put("android.permission.READ_EXTERNAL_STORAGE",Boolean.FALSE);
        set.put("android.permission.READ_CALL_LOG",Boolean.FALSE);
        set.put("android.permission.RECEIVE_WAP_PUSH",Boolean.TRUE);
        rules.put(277,new HashMap(set));
        set.clear();

        //Rule 157:
        set.put("android.permission.READ_SYNC_SETTINGS",Boolean.FALSE);
        set.put("android.permission.ACCESS_LOCATION_EXTRA_COMMANDS",Boolean.TRUE);
        set.put("android.permission.ACCESS_MOCK_LOCATION",Boolean.FALSE);
        set.put("android.permission.VIBRATE",Boolean.FALSE);
        set.put("android.permission.CHANGE_WIFI_STATE",Boolean.TRUE);
        set.put("android.permission.WAKE_LOCK",Boolean.FALSE);
        set.put("android.permission.RECEIVE_BOOT_COMPLETED",Boolean.TRUE);
        set.put("android.permission.RECEIVE_SMS",Boolean.FALSE);
        rules.put(157,new HashMap(set));
        set.clear();

        //Rule 301:
        set.put("android.permission.ACCESS_LOCATION_EXTRA_COMMANDS",Boolean.TRUE);
        set.put("android.permission.RESTART_PACKAGES",Boolean.FALSE);
        set.put("android.permission.VIBRATE",Boolean.FALSE);
        set.put("android.permission.READ_SMS",Boolean.TRUE);
        set.put("android.permission.READ_CALL_LOG",Boolean.FALSE);
        set.put("android.permission.ACCESS_WIFI_STATE",Boolean.TRUE);
        set.put("android.permission.RECEIVE_SMS",Boolean.FALSE);
        rules.put(301,new HashMap(set));
        set.clear();


        //Rule 56:
        set.put("android.permission.RESTART_PACKAGES",Boolean.FALSE);
        set.put("android.permission.VIBRATE",Boolean.FALSE);
        set.put("android.permission.BROADCAST_STICKY",Boolean.FALSE);
        set.put("android.permission.KILL_BACKGROUND_PROCESSES",Boolean.FALSE);
        set.put("android.permission.CAMERA",Boolean.FALSE);
        set.put("android.permission.READ_PHONE_STATE",Boolean.TRUE);
        set.put("android.permission.WRITE_OWNER_DATA",Boolean.FALSE);
        set.put("android.permission.ACCESS_COARSE_LOCATION",Boolean.FALSE);
        set.put("android.permission.READ_EXTERNAL_STORAGE",Boolean.FALSE);
        set.put("android.permission.WRITE_EXTERNAL_STORAGE",Boolean.TRUE);
        rules.put(56,new HashMap(set));
        set.clear();

        //Rule 244:
        set.put("android.permission.INTERNET",Boolean.TRUE);
        set.put("android.permission.WAKE_LOCK",Boolean.TRUE);
        set.put("android.permission.READ_PHONE_STATE",Boolean.TRUE);
        set.put("android.permission.SEND_SMS",Boolean.FALSE);
        set.put("android.permission.READ_EXTERNAL_STORAGE",Boolean.FALSE);
        set.put("android.permission.READ_CALL_LOG",Boolean.FALSE);
        set.put("android.permission.WRITE_SMS",Boolean.TRUE);
        set.put("android.permission.GET_ACCOUNTS",Boolean.FALSE);
        rules.put(244,new HashMap(set));
        set.clear();

        //Rule 268:
        set.put("android.permission.READ_HISTORY_BOOKMARKS",Boolean.TRUE);
        set.put("android.permission.READ_SMS",Boolean.TRUE);
        set.put("android.permission.READ_CALL_LOG",Boolean.FALSE);
        set.put("android.permission.ACCESS_NETWORK_STATE",Boolean.FALSE);
        rules.put(268,new HashMap(set));
        set.clear();

        //Rule 243:
        set.put("android.permission.INTERNET",Boolean.TRUE);
        set.put("android.permission.VIBRATE",Boolean.FALSE);
        set.put("android.permission.WAKE_LOCK",Boolean.FALSE);
        set.put("android.permission.READ_SMS",Boolean.TRUE);
        set.put("android.permission.READ_PHONE_STATE",Boolean.TRUE);
        set.put("android.permission.READ_EXTERNAL_STORAGE",Boolean.FALSE);
        set.put("android.permission.RECEIVE_BOOT_COMPLETED",Boolean.TRUE);
        set.put("android.permission.PROCESS_OUTGOING_CALLS",Boolean.FALSE);
        rules.put(243,new HashMap(set));
        set.clear();

        //Rule 147:
        set.put("android.permission.READ_SYNC_SETTINGS",Boolean.FALSE);
        set.put("android.permission.CHANGE_WIFI_STATE",Boolean.TRUE);
        set.put("android.permission.CAMERA",Boolean.FALSE);
        set.put("android.permission.READ_PHONE_STATE",Boolean.TRUE);
        set.put("android.permission.READ_EXTERNAL_STORAGE",Boolean.FALSE);
        set.put("android.permission.WRITE_EXTERNAL_STORAGE",Boolean.TRUE);
        rules.put(147,new HashMap(set));
        set.clear();

        //Rule 303:
        set.put("android.permission.ACCESS_LOCATION_EXTRA_COMMANDS",Boolean.TRUE);
        set.put("android.permission.RESTART_PACKAGES",Boolean.FALSE);
        set.put("android.permission.BROADCAST_STICKY",Boolean.FALSE);
        set.put("android.permission.READ_SMS",Boolean.TRUE);
        set.put("android.permission.GET_TASKS",Boolean.TRUE);
        set.put("android.permission.READ_CALL_LOG",Boolean.FALSE);
        set.put("android.permission.CHANGE_NETWORK_STATE",Boolean.FALSE);
        set.put("android.permission.RECEIVE_SMS",Boolean.FALSE);
        rules.put(303,new HashMap(set));
        set.clear();

        //Rule 72:
        set.put("android.permission.KILL_BACKGROUND_PROCESSES",Boolean.FALSE);
        set.put("android.permission.READ_HISTORY_BOOKMARKS",Boolean.FALSE);
        set.put("android.permission.READ_PHONE_STATE",Boolean.TRUE);
        set.put("android.permission.READ_EXTERNAL_STORAGE",Boolean.FALSE);
        set.put("android.permission.RECEIVE_BOOT_COMPLETED",Boolean.TRUE);
        set.put("android.permission.WRITE_HISTORY_BOOKMARKS",Boolean.FALSE);
        set.put("android.permission.WRITE_EXTERNAL_STORAGE",Boolean.TRUE);
        set.put("android.permission.READ_CONTACTS",Boolean.FALSE);
        rules.put(72,new HashMap(set));
        set.clear();

        //Rule 68:
        set.put("android.permission.RESTART_PACKAGES",Boolean.FALSE);
        set.put("android.permission.CAMERA",Boolean.FALSE);
        set.put("android.permission.READ_HISTORY_BOOKMARKS",Boolean.FALSE);
        set.put("android.permission.READ_PHONE_STATE",Boolean.TRUE);
        set.put("android.permission.USE_CREDENTIALS",Boolean.FALSE);
        set.put("android.permission.WRITE_OWNER_DATA",Boolean.FALSE);
        set.put("android.permission.READ_EXTERNAL_STORAGE",Boolean.FALSE);
        set.put("android.permission.WRITE_EXTERNAL_STORAGE",Boolean.TRUE);
        set.put("android.permission.READ_CONTACTS",Boolean.FALSE);
        rules.put(68,new HashMap(set));
        set.clear();

        //Rule 223:
        set.put("android.permission.CHANGE_CONFIGURATION",Boolean.FALSE);
        set.put("android.permission.CALL_PHONE",Boolean.FALSE);
        set.put("android.permission.READ_EXTERNAL_STORAGE",Boolean.FALSE);
        set.put("android.permission.SYSTEM_ALERT_WINDOW",Boolean.TRUE);
        set.put("android.permission.ACCESS_NETWORK_STATE",Boolean.TRUE);
        set.put("android.permission.RECEIVE_SMS",Boolean.TRUE);
        rules.put(223,new HashMap(set));
        set.clear();

        //Rule 121:
        set.put("android.permission.CHANGE_WIFI_STATE",Boolean.FALSE);
        set.put("android.permission.READ_EXTERNAL_STORAGE",Boolean.FALSE);
        set.put("android.permission.WRITE_HISTORY_BOOKMARKS",Boolean.TRUE);
        set.put("android.permission.WRITE_EXTERNAL_STORAGE",Boolean.TRUE);
        rules.put(121,new HashMap(set));
        set.clear();

        //Rule 270:
        set.put("android.permission.CHANGE_CONFIGURATION",Boolean.FALSE);
        set.put("android.permission.READ_SMS",Boolean.TRUE);
        set.put("android.permission.READ_EXTERNAL_STORAGE",Boolean.FALSE);
        set.put("android.permission.PROCESS_OUTGOING_CALLS",Boolean.FALSE);
        set.put("android.permission.READ_CALL_LOG",Boolean.FALSE);
        set.put("android.permission.RECEIVE_WAP_PUSH",Boolean.FALSE);
        set.put("android.permission.CHANGE_NETWORK_STATE",Boolean.TRUE);
        rules.put(270,new HashMap(set));
        set.clear();

        //Rule 252:
        set.put("android.permission.READ_SMS",Boolean.TRUE);
        set.put("android.permission.READ_PHONE_STATE",Boolean.TRUE);
        set.put("android.permission.READ_EXTERNAL_STORAGE",Boolean.FALSE);
        set.put("android.permission.PROCESS_OUTGOING_CALLS",Boolean.FALSE);
        set.put("android.permission.READ_CALL_LOG",Boolean.FALSE);
        set.put("android.permission.ACCESS_NETWORK_STATE",Boolean.TRUE);
        set.put("android.permission.WRITE_SMS",Boolean.FALSE);
        set.put("android.permission.READ_CONTACTS",Boolean.TRUE);
        rules.put(252,new HashMap(set));
        set.clear();

        //Rule 271:
        set.put("android.permission.READ_SMS",Boolean.TRUE);
        set.put("android.permission.READ_EXTERNAL_STORAGE",Boolean.FALSE);
        set.put("android.permission.PROCESS_OUTGOING_CALLS",Boolean.TRUE);
        set.put("android.permission.WRITE_SMS",Boolean.FALSE);
        set.put("android.permission.READ_CONTACTS",Boolean.FALSE);
        rules.put(271,new HashMap(set));
        set.clear();

        //Rule 294:
        set.put("android.permission.RECORD_AUDIO",Boolean.FALSE);
        set.put("android.permission.VIBRATE",Boolean.FALSE);
        set.put("android.permission.BLUETOOTH_ADMIN",Boolean.FALSE);
        set.put("android.permission.READ_SMS",Boolean.TRUE);
        set.put("android.permission.READ_PHONE_STATE",Boolean.TRUE);
        set.put("android.permission.READ_EXTERNAL_STORAGE",Boolean.TRUE);
        set.put("android.permission.READ_CALL_LOG",Boolean.FALSE);
        set.put("android.permission.ACCESS_FINE_LOCATION",Boolean.FALSE);
        set.put("android.permission.GET_ACCOUNTS",Boolean.FALSE);
        set.put("android.permission.RECEIVE_SMS",Boolean.TRUE);
        set.put("android.permission.READ_CONTACTS",Boolean.TRUE);
        rules.put(294,new HashMap(set));
        set.clear();

        //Rule 45:
        set.put("android.permission.CAMERA",Boolean.FALSE);
        set.put("android.permission.SET_WALLPAPER",Boolean.TRUE);
        set.put("android.permission.ACCESS_COARSE_LOCATION",Boolean.FALSE);
        set.put("android.permission.READ_EXTERNAL_STORAGE",Boolean.FALSE);
        set.put("android.permission.WRITE_EXTERNAL_STORAGE",Boolean.TRUE);
        rules.put(45,new HashMap(set));
        set.clear();

        //Rule 163:
        set.put("android.permission.ACCESS_LOCATION_EXTRA_COMMANDS",Boolean.TRUE);
        set.put("android.permission.RESTART_PACKAGES",Boolean.FALSE);
        set.put("android.permission.VIBRATE",Boolean.FALSE);
        set.put("android.permission.CHANGE_WIFI_STATE",Boolean.TRUE);
        set.put("android.permission.WRITE_SETTINGS",Boolean.FALSE);
        set.put("android.permission.RECEIVE_BOOT_COMPLETED",Boolean.TRUE);
        set.put("android.permission.GET_TASKS",Boolean.TRUE);
        set.put("android.permission.RECEIVE_SMS",Boolean.FALSE);
        rules.put(163,new HashMap(set));
        set.clear();

        //Rule 103:
        set.put("android.permission.READ_EXTERNAL_STORAGE",Boolean.FALSE);
        set.put("android.permission.SYSTEM_ALERT_WINDOW",Boolean.FALSE);
        set.put("android.permission.READ_OWNER_DATA",Boolean.TRUE);
        set.put("android.permission.WRITE_EXTERNAL_STORAGE",Boolean.TRUE);
        rules.put(103,new HashMap(set));
        set.clear();

        //Rule 22:
        set.put("android.permission.INTERNET",Boolean.TRUE);
        set.put("android.permission.WAKE_LOCK",Boolean.FALSE);
        set.put("android.permission.READ_SMS",Boolean.FALSE);
        set.put("android.permission.READ_PHONE_STATE",Boolean.TRUE);
        set.put("android.permission.WRITE_SETTINGS",Boolean.FALSE);
        set.put("android.permission.READ_EXTERNAL_STORAGE",Boolean.FALSE);
        set.put("android.permission.READ_CALL_LOG",Boolean.FALSE);
        set.put("android.permission.ACCESS_FINE_LOCATION",Boolean.FALSE);
        set.put("android.permission.ACCESS_NETWORK_STATE",Boolean.FALSE);
        set.put("android.permission.READ_CONTACTS",Boolean.TRUE);
        rules.put(22,new HashMap(set));
        set.clear();

        //Rule 153:
        set.put("android.permission.ACCESS_LOCATION_EXTRA_COMMANDS",Boolean.TRUE);
        set.put("android.permission.CHANGE_WIFI_STATE",Boolean.TRUE);
        set.put("android.permission.READ_SMS",Boolean.FALSE);
        set.put("android.permission.ACCESS_COARSE_LOCATION",Boolean.FALSE);
        set.put("android.permission.RECEIVE_BOOT_COMPLETED",Boolean.TRUE);
        set.put("android.permission.CHANGE_NETWORK_STATE",Boolean.FALSE);
        rules.put(153,new HashMap(set));
        set.clear();

        //Rule 195:
        set.put("android.permission.SEND_SMS",Boolean.TRUE);
        set.put("android.permission.READ_EXTERNAL_STORAGE",Boolean.FALSE);
        set.put("android.permission.EXPAND_STATUS_BAR",Boolean.TRUE);
        set.put("android.permission.RECEIVE_SMS",Boolean.FALSE);
        rules.put(195,new HashMap(set));
        set.clear();

        //Rule 33:
        set.put("android.permission.WAKE_LOCK",Boolean.FALSE);
        set.put("android.permission.WRITE_SETTINGS",Boolean.TRUE);
        set.put("android.permission.ACCESS_COARSE_LOCATION",Boolean.TRUE);
        set.put("android.permission.READ_EXTERNAL_STORAGE",Boolean.FALSE);
        set.put("android.permission.READ_CALL_LOG",Boolean.FALSE);
        set.put("android.permission.READ_CONTACTS",Boolean.TRUE);
        rules.put(33,new HashMap(set));
        set.clear();

        //Rule 184:
        set.put("android.permission.INTERNET",Boolean.TRUE);
        set.put("android.permission.SEND_SMS",Boolean.TRUE);
        set.put("android.permission.READ_EXTERNAL_STORAGE",Boolean.FALSE);
        set.put("android.permission.READ_CALL_LOG",Boolean.FALSE);
        set.put("android.permission.ACCESS_NETWORK_STATE",Boolean.FALSE);
        set.put("android.permission.RECEIVE_SMS",Boolean.FALSE);
        set.put("android.permission.READ_CONTACTS",Boolean.TRUE);
        rules.put(184,new HashMap(set));
        set.clear();

        //Rule 200:
        set.put("android.permission.CLEAR_APP_CACHE",Boolean.TRUE);
        set.put("android.permission.READ_SMS",Boolean.FALSE);
        set.put("android.permission.BIND_WALLPAPER",Boolean.TRUE);
        rules.put(200,new HashMap(set));
        set.clear();

        //Rule 66:
        set.put("android.permission.READ_PHONE_STATE",Boolean.TRUE);
        set.put("android.permission.USE_CREDENTIALS",Boolean.FALSE);
        set.put("android.permission.SEND_SMS",Boolean.FALSE);
        set.put("android.permission.READ_EXTERNAL_STORAGE",Boolean.FALSE);
        set.put("android.permission.SYSTEM_ALERT_WINDOW",Boolean.FALSE);
        set.put("android.permission.ACCESS_FINE_LOCATION",Boolean.TRUE);
        set.put("android.permission.GET_ACCOUNTS",Boolean.TRUE);
        set.put("android.permission.WRITE_EXTERNAL_STORAGE",Boolean.TRUE);
        set.put("android.permission.READ_CONTACTS",Boolean.FALSE);
        rules.put(66,new HashMap(set));
        set.clear();

        //Rule 106:
        set.put("android.permission.RESTART_PACKAGES",Boolean.TRUE);
        set.put("android.permission.KILL_BACKGROUND_PROCESSES",Boolean.FALSE);
        set.put("android.permission.WRITE_SETTINGS",Boolean.TRUE);
        set.put("android.permission.BIND_WALLPAPER",Boolean.FALSE);
        set.put("android.permission.SEND_SMS",Boolean.FALSE);
        set.put("android.permission.READ_OWNER_DATA",Boolean.TRUE);
        set.put("android.permission.RECEIVE_SMS",Boolean.FALSE);
        rules.put(106,new HashMap(set));
        set.clear();


        //Rule 83:
        set.put("android.permission.ACCESS_LOCATION_EXTRA_COMMANDS",Boolean.TRUE);
        set.put("android.permission.RECORD_AUDIO",Boolean.TRUE);
        set.put("android.permission.CHANGE_WIFI_STATE",Boolean.FALSE);
        set.put("android.permission.KILL_BACKGROUND_PROCESSES",Boolean.TRUE);
        set.put("android.permission.READ_SMS",Boolean.FALSE);
        set.put("android.permission.GET_TASKS",Boolean.FALSE);
        set.put("android.permission.WRITE_HISTORY_BOOKMARKS",Boolean.FALSE);
        set.put("android.permission.RECEIVE_SMS",Boolean.FALSE);
        rules.put(83,new HashMap(set));
        set.clear();


        //Rule 132:
        set.put("android.permission.INTERNET",Boolean.TRUE);
        set.put("android.permission.CHANGE_WIFI_STATE",Boolean.TRUE);
        set.put("android.permission.WAKE_LOCK",Boolean.FALSE);
        set.put("android.permission.READ_PHONE_STATE",Boolean.TRUE);
        set.put("android.permission.WRITE_SETTINGS",Boolean.FALSE);
        set.put("android.permission.READ_EXTERNAL_STORAGE",Boolean.FALSE);
        set.put("android.permission.ACCESS_NETWORK_STATE",Boolean.FALSE);
        set.put("android.permission.RECEIVE_SMS",Boolean.FALSE);
        rules.put(132,new HashMap(set));
        set.clear();

        //Rule 168:
        set.put("android.permission.ACCESS_LOCATION_EXTRA_COMMANDS",Boolean.TRUE);
        set.put("android.permission.RECORD_AUDIO",Boolean.TRUE);
        set.put("android.permission.CHANGE_WIFI_STATE",Boolean.TRUE);
        set.put("android.permission.CAMERA",Boolean.FALSE);
        set.put("android.permission.WAKE_LOCK",Boolean.TRUE);
        set.put("android.permission.DISABLE_KEYGUARD",Boolean.FALSE);
        set.put("android.permission.READ_SMS",Boolean.FALSE);
        set.put("android.permission.SEND_SMS",Boolean.FALSE);
        set.put("android.permission.SYSTEM_ALERT_WINDOW",Boolean.FALSE);
        set.put("android.permission.RECEIVE_BOOT_COMPLETED",Boolean.TRUE);
        rules.put(168,new HashMap(set));
        set.clear();

        //Rule 307:
        set.put("android.permission.ACCESS_LOCATION_EXTRA_COMMANDS",Boolean.TRUE);
        set.put("android.permission.CAMERA",Boolean.FALSE);
        set.put("android.permission.READ_SMS",Boolean.TRUE);
        set.put("android.permission.READ_EXTERNAL_STORAGE",Boolean.TRUE);
        set.put("android.permission.READ_CALL_LOG",Boolean.FALSE);
        set.put("android.permission.READ_CONTACTS",Boolean.TRUE);
        rules.put(307,new HashMap(set));
        set.clear();

        //Rule 175:
        set.put("android.permission.VIBRATE",Boolean.TRUE);
        set.put("android.permission.CALL_PHONE",Boolean.FALSE);
        set.put("android.permission.READ_SMS",Boolean.FALSE);
        set.put("android.permission.READ_PHONE_STATE",Boolean.TRUE);
        set.put("android.permission.SEND_SMS",Boolean.TRUE);
        set.put("android.permission.READ_CALL_LOG",Boolean.FALSE);
        set.put("android.permission.ACCESS_NETWORK_STATE",Boolean.FALSE);
        set.put("android.permission.GET_ACCOUNTS",Boolean.FALSE);
        set.put("android.permission.RECEIVE_SMS",Boolean.FALSE);
        set.put("android.permission.WRITE_EXTERNAL_STORAGE",Boolean.FALSE);
        rules.put(175,new HashMap(set));
        set.clear();

        //Rule 289:
        set.put("android.permission.CALL_PHONE",Boolean.TRUE);
        set.put("android.permission.READ_SMS",Boolean.TRUE);
        set.put("android.permission.READ_EXTERNAL_STORAGE",Boolean.TRUE);
        set.put("android.permission.ACCESS_NETWORK_STATE",Boolean.FALSE);
        set.put("android.permission.WRITE_SMS",Boolean.TRUE);
        set.put("android.permission.READ_CONTACTS",Boolean.FALSE);
        rules.put(289,new HashMap(set));
        set.clear();

        //Rule 125:
        set.put("android.permission.ACCESS_LOCATION_EXTRA_COMMANDS",Boolean.TRUE);
        set.put("android.permission.FLASHLIGHT",Boolean.TRUE);
        set.put("android.permission.CAMERA",Boolean.TRUE);
        set.put("android.permission.ACCESS_WIFI_STATE",Boolean.FALSE);
        set.put("android.permission.WRITE_HISTORY_BOOKMARKS",Boolean.TRUE);
        rules.put(125,new HashMap(set));
        set.clear();

        //Rule 202:
        set.put("android.permission.INTERNET",Boolean.FALSE);
        set.put("android.permission.ACCESS_MOCK_LOCATION",Boolean.FALSE);
        set.put("android.permission.WAKE_LOCK",Boolean.FALSE);
        set.put("android.permission.ACCESS_COARSE_LOCATION",Boolean.FALSE);
        set.put("android.permission.RECEIVE_BOOT_COMPLETED",Boolean.FALSE);
        set.put("android.permission.READ_CALL_LOG",Boolean.FALSE);
        set.put("android.permission.ACCESS_FINE_LOCATION",Boolean.TRUE);
        set.put("android.permission.RECEIVE_SMS",Boolean.TRUE);
        rules.put(202,new HashMap(set));
        set.clear();

        //Rule 5:
        set.put("android.permission.VIBRATE",Boolean.TRUE);
        set.put("android.permission.BROADCAST_STICKY",Boolean.FALSE);
        set.put("android.permission.CAMERA",Boolean.FALSE);
        set.put("android.permission.WAKE_LOCK",Boolean.TRUE);
        set.put("android.permission.CALL_PHONE",Boolean.FALSE);
        set.put("android.permission.WRITE_SETTINGS",Boolean.FALSE);
        set.put("android.permission.USE_CREDENTIALS",Boolean.FALSE);
        set.put("android.permission.READ_EXTERNAL_STORAGE",Boolean.FALSE);
        set.put("android.permission.ACCESS_FINE_LOCATION",Boolean.TRUE);
        set.put("android.permission.ACCESS_NETWORK_STATE",Boolean.FALSE);
        set.put("android.permission.GET_ACCOUNTS",Boolean.TRUE);
        set.put("android.permission.READ_CONTACTS",Boolean.FALSE);
        rules.put(5,new HashMap(set));
        set.clear();

        //Rule 94:
        set.put("android.permission.ACCESS_LOCATION_EXTRA_COMMANDS",Boolean.FALSE);
        set.put("android.permission.RECORD_AUDIO",Boolean.FALSE);
        set.put("android.permission.VIBRATE",Boolean.TRUE);
        set.put("android.permission.CHANGE_WIFI_STATE",Boolean.FALSE);
        set.put("android.permission.SET_WALLPAPER",Boolean.TRUE);
        set.put("android.permission.READ_SMS",Boolean.FALSE);
        set.put("android.permission.BIND_WALLPAPER",Boolean.FALSE);
        set.put("android.permission.READ_EXTERNAL_STORAGE",Boolean.TRUE);
        set.put("android.permission.GET_TASKS",Boolean.TRUE);
        set.put("android.permission.ACCESS_WIFI_STATE",Boolean.TRUE);
        set.put("android.permission.ACCESS_FINE_LOCATION",Boolean.TRUE);
        set.put("android.permission.WRITE_HISTORY_BOOKMARKS",Boolean.FALSE);
        rules.put(94,new HashMap(set));
        set.clear();

        //Rule 28:
        set.put("android.permission.INTERNET",Boolean.FALSE);
        set.put("android.permission.BLUETOOTH_ADMIN",Boolean.FALSE);
        set.put("android.permission.CAMERA",Boolean.TRUE);
        set.put("android.permission.READ_EXTERNAL_STORAGE",Boolean.FALSE);
        set.put("android.permission.BLUETOOTH",Boolean.TRUE);
        rules.put(28,new HashMap(set));
        set.clear();

        //Rule 97:
        set.put("android.permission.CHANGE_CONFIGURATION",Boolean.TRUE);
        set.put("android.permission.VIBRATE",Boolean.FALSE);
        set.put("android.permission.CHANGE_WIFI_STATE",Boolean.FALSE);
        set.put("android.permission.SEND_SMS",Boolean.FALSE);
        set.put("android.permission.GET_TASKS",Boolean.TRUE);
        set.put("android.permission.CHANGE_NETWORK_STATE",Boolean.TRUE);
        rules.put(97,new HashMap(set));
        set.clear();

        //Rule 128:
        set.put("android.permission.ACCESS_LOCATION_EXTRA_COMMANDS",Boolean.TRUE);
        set.put("android.permission.FLASHLIGHT",Boolean.TRUE);
        set.put("android.permission.CHANGE_WIFI_STATE",Boolean.FALSE);
        set.put("android.permission.BROADCAST_STICKY",Boolean.FALSE);
        set.put("android.permission.CALL_PHONE",Boolean.TRUE);
        set.put("android.permission.READ_SMS",Boolean.FALSE);
        set.put("android.permission.WRITE_HISTORY_BOOKMARKS",Boolean.TRUE);
        rules.put(128,new HashMap(set));
        set.clear();

        //Rule 172:
        set.put("android.permission.INTERNET",Boolean.FALSE);
        set.put("android.permission.SEND_SMS",Boolean.TRUE);
        set.put("android.permission.READ_EXTERNAL_STORAGE",Boolean.FALSE);
        set.put("android.permission.READ_CALL_LOG",Boolean.FALSE);
        set.put("android.permission.ACCESS_NETWORK_STATE",Boolean.TRUE);
        set.put("android.permission.RECEIVE_SMS",Boolean.FALSE);
        rules.put(172,new HashMap(set));
        set.clear();

        //Rule 36:
        set.put("android.permission.VIBRATE",Boolean.FALSE);
        set.put("android.permission.BROADCAST_STICKY",Boolean.TRUE);
        set.put("android.permission.BLUETOOTH_ADMIN",Boolean.FALSE);
        set.put("android.permission.WAKE_LOCK",Boolean.FALSE);
        set.put("android.permission.READ_PHONE_STATE",Boolean.TRUE);
        set.put("android.permission.READ_EXTERNAL_STORAGE",Boolean.FALSE);
        set.put("android.permission.ACCESS_WIFI_STATE",Boolean.FALSE);
        set.put("android.permission.ACCESS_NETWORK_STATE",Boolean.FALSE);
        rules.put(36,new HashMap(set));
        set.clear();

        //Rule 155:
        set.put("android.permission.ACCESS_LOCATION_EXTRA_COMMANDS",Boolean.TRUE);
        set.put("android.permission.CHANGE_WIFI_STATE",Boolean.TRUE);
        set.put("android.permission.READ_EXTERNAL_STORAGE",Boolean.TRUE);
        set.put("android.permission.ACCESS_WIFI_STATE",Boolean.FALSE);
        rules.put(155,new HashMap(set));
        set.clear();

        //Rule 264:
        set.put("android.permission.BROADCAST_STICKY",Boolean.TRUE);
        set.put("android.permission.READ_SMS",Boolean.TRUE);
        set.put("android.permission.PROCESS_OUTGOING_CALLS",Boolean.FALSE);
        set.put("android.permission.READ_CALL_LOG",Boolean.FALSE);
        set.put("android.permission.ACCESS_WIFI_STATE",Boolean.FALSE);
        set.put("android.permission.WRITE_EXTERNAL_STORAGE",Boolean.FALSE);
        rules.put(264,new HashMap(set));
        set.clear();

        //Rule 12:
        set.put("android.permission.INTERNET",Boolean.TRUE);
        set.put("android.permission.WAKE_LOCK",Boolean.FALSE);
        set.put("android.permission.USE_CREDENTIALS",Boolean.FALSE);
        set.put("android.permission.READ_EXTERNAL_STORAGE",Boolean.FALSE);
        set.put("android.permission.SYSTEM_ALERT_WINDOW",Boolean.FALSE);
        set.put("android.permission.RECEIVE_BOOT_COMPLETED",Boolean.TRUE);
        set.put("android.permission.WAKE_LOCK",Boolean.FALSE);
        set.put("android.permission.ACCESS_NETWORK_STATE",Boolean.FALSE);
        set.put("android.permission.GET_ACCOUNTS",Boolean.TRUE);
        set.put("android.permission.RECEIVE_SMS",Boolean.FALSE);
        set.put("android.permission.READ_CONTACTS",Boolean.FALSE);
        rules.put(12,new HashMap(set));
        set.clear();

        //Rule 232:
        set.put("android.permission.READ_SMS",Boolean.FALSE);
        set.put("android.permission.SEND_SMS",Boolean.TRUE);
        set.put("android.permission.READ_EXTERNAL_STORAGE",Boolean.TRUE);
        set.put("android.permission.PROCESS_OUTGOING_CALLS",Boolean.TRUE);
        set.put("android.permission.ACCESS_FINE_LOCATION",Boolean.FALSE);
        set.put("android.permission.RECEIVE_SMS",Boolean.TRUE);
        set.put("android.permission.READ_CONTACTS",Boolean.FALSE);
        rules.put(28,new HashMap(set));
        set.clear();


    /*//Test Rule 777
    set.put("android.permission.READ_EXTERNAL_STORAGE",Boolean.TRUE);
    set.put("android.permission.INTERNET",Boolean.TRUE);
    set.put("android.permission.WRITE_EXTERNAL_STORAGE",Boolean.TRUE);
    set.put("android.permission.RECORD_AUDIO",Boolean.FALSE);
    set.put("android.permission.READ_CONTEXT",Boolean.FALSE);
    set.put("android.permission.VIBRATE",Boolean.FALSE);
    rules.put(777,new HashMap(set));
    set.clear();

   //Test Rule 888
    set.put("android.permission.READ_EXTERNAL_STORAGE",Boolean.TRUE);
    set.put("android.permission.WRITE_EXTERNAL_STORAGE",Boolean.TRUE);
    rules.put(888,new HashMap(set));
    set.clear();*/

    }

    private void create_risk_values()
    {
        risk_value.put(281,99.7);
        risk_value.put(189,99.6);
        risk_value.put(286,99.5);
        risk_value.put(261,99.3);
        risk_value.put(109,99.2);
        risk_value.put(266,99.2);
        risk_value.put(206,99.1);
        risk_value.put(212,99.1);
        risk_value.put(293,98.9);
        risk_value.put(215,98.9);
        risk_value.put(44,98.9);
        risk_value.put(280,98.8);
        risk_value.put(277,98.5);
        risk_value.put(157,98.2);
        risk_value.put(301,98.2);
        risk_value.put(56,98.2);
        risk_value.put(244,98.0);
        risk_value.put(268,97.9);
        risk_value.put(243,97.9);
        risk_value.put(147,97.7);
        risk_value.put(303,97.6);
        risk_value.put(72,97.5);
        risk_value.put(68,96.9);
        risk_value.put(223,96.8);
        risk_value.put(121,96.7);
        risk_value.put(270,96.5);
        risk_value.put(250,96.0);
        risk_value.put(271,95.8);
        risk_value.put(294,94.6);
        risk_value.put(45,93.9);
        risk_value.put(163,93.4);
        risk_value.put(103,92.2);
        risk_value.put(22,91.7);
        risk_value.put(153,91.4);
        risk_value.put(195,89.9);
        risk_value.put(184,87.5);
        risk_value.put(200,86.7);
        risk_value.put(66,86.6);
        risk_value.put(106,84.1);
        risk_value.put(83,82.0);
        risk_value.put(132,80.5);
        risk_value.put(168,77.1);
        risk_value.put(175,70.7);
        risk_value.put(289,66.2);
        risk_value.put(125,63.0);
        risk_value.put(202,56.6);
        risk_value.put(5,54.0);
        risk_value.put(94,51.2);
        risk_value.put(28,50.0);
        risk_value.put(97,50.0);
        risk_value.put(128,50.0);
        risk_value.put(172,50.0);
        risk_value.put(36,49.2);
        risk_value.put(155,45.3);
        risk_value.put(264,45.3);
        risk_value.put(12,44.8);
        risk_value.put(232,44.3);
    }



    private List<String> True_set = new ArrayList<String>();
    private List<String> False_set = new ArrayList<String>();

    private void Get_sets(Map<String,Boolean> rule_set){
        Set<Map.Entry<String,Boolean>> rule_entries=rule_set.entrySet();
        True_set.clear();
        False_set.clear();
        for (Map.Entry<String, Boolean> rule_entry : rule_entries){
            if(rule_entry.getValue()==Boolean.TRUE)
            {
                True_set.add(rule_entry.getKey());
            }
            else {
                False_set.add(rule_entry.getKey());
            }
        }
    }


    public Double Get_risk(List<String> permissions){
        Double risk=0.0;
        Set<Map.Entry<Integer,Map<String,Boolean>>> rule_entries=rules.entrySet();
        for (Map.Entry<Integer,Map<String,Boolean>> rule_entry : rule_entries) {

            /*Log.d("Rule_entry","test");
            Set<Map.Entry<String,Boolean>> rule_entries2=rule_entry.getValue().entrySet();
            for (Map.Entry<String,Boolean> rule_entry2 : rule_entries2) {
                Log.d(String.valueOf(rule_entry2.getKey()), String.valueOf(rule_entry2.getValue()));
            }*/


            Get_sets(rule_entry.getValue());
            //for(String item: True_set)
            //{Log.d("This is the true set",item);}
            //Set<String> true_values = new HashSet<String>(True_set);
            //Set<String> False_values = new HashSet<String>(False_set);
            Set<String> App_permissions = new HashSet<String>(permissions);
            //Log.d("Installed apps test", key);
            if(False_set.size()==0 && App_permissions.containsAll(True_set)) {
                    risk = risk_value.get(rule_entry.getKey());
            }
            else if(App_permissions.containsAll(True_set))// && !App_permissions.containsAll(False_set))
            {
                boolean isRisky = true;
                for(String item: False_set) {
                    if (App_permissions.contains(item)) {
                        isRisky = false;
                        break;
                    }
                }
                if (isRisky)
                {
                    risk = risk_value.get(rule_entry.getKey());
                    break;
                }
            }
        }
        return risk;
    }

    /*public Map<String,List<String>> app_permissions_updated(Map<String,List<String>> permissions){
        Map<String,List<String>> permissions_updated = new HashMap<>();
        Set<Map.Entry<String, List<String>>> permission_entries=permissions.entrySet();
        for (Map.Entry<String, List<String>> permission_entry : permission_entries) {
            String key=permission_entry.getKey();
            List<String> permission_app=permission_entry.getValue();

        }
        return permissions;
    }
    */
}
