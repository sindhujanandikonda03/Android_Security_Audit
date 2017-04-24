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
import android.content.SharedPreferences;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AppSettings {

    // Settings
    private static final String TAG = "AppSettings";
    private static final String APP_SETTINGS_FILE = "edu.ucdenver.androidsecurity.appsettingsfile";
    private static final String SECURED_WIFI_FLAG = "securedWifiFlag";
    private static final String DISABLE_CAMERAS_FLAG = "disableCamerasFlag";
    private static final String LAST_AUDIT_DATE = "lastAuditDate";

    // Audit recommendation
    private static final String CONNECTION_RECOMMENDATION = "connectionRecommendation";
    private static final String CAMERA_RECOMMENDATION = "cameraRecommendation";
    private static final String UNKNOWNSOURCES_RECOMMENDATION = "unknownSourcesRecommendation";
    private static final String ENCRYPTION_RECOMMENDATION = "encryptionRecommendation";
    private static final String APPS_RECOMMENDATION = "appsRecommendation";

    // Audit default message
    private static final String noAuditDefaultMsg = "No app scan data available";


    private static void setFlag(Context context, String setting, boolean value) {
        SharedPreferences sharedPreferences =
                context.getSharedPreferences(APP_SETTINGS_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(setting, value);
        editor.commit();
    }

    private static void setPersistentStringData(Context context, String setting, String value) {
        SharedPreferences sharedPreferences =
                context.getSharedPreferences(APP_SETTINGS_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(setting, value);
        editor.commit();
    }

    private static boolean getBooleanFlag(Context context, String setting) {
        SharedPreferences sharedPreferences =
                context.getSharedPreferences(APP_SETTINGS_FILE, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(setting, false);
    }

    private static String getPersistentStringData(Context context, String setting, String defaultValue) {
        SharedPreferences sharedPreferences =
                context.getSharedPreferences(APP_SETTINGS_FILE, Context.MODE_PRIVATE);
        return sharedPreferences.getString(setting, defaultValue);
    }

    public static void setConnectionRecommendation(Context context, String msg) {
        setPersistentStringData(context, CONNECTION_RECOMMENDATION, msg);
    }

    public static String getConnectionRecommendation(Context context) {
        return getPersistentStringData(context, CONNECTION_RECOMMENDATION, noAuditDefaultMsg);
    }

    public static void setCameraRecommendation(Context context, String msg) {
        setPersistentStringData(context, CAMERA_RECOMMENDATION, msg);
    }

    public static String getCameraRecommendation(Context context) {
        return getPersistentStringData(context, CAMERA_RECOMMENDATION, noAuditDefaultMsg);
    }

    public static void setUnknownSourcesRecommendation(Context context, String msg) {
        setPersistentStringData(context, UNKNOWNSOURCES_RECOMMENDATION, msg);
    }

    public static String getUnknownSourcesRecommendation(Context context) {
        return getPersistentStringData(context, UNKNOWNSOURCES_RECOMMENDATION, noAuditDefaultMsg);
    }

    public static void setEncryptionRecommendation(Context context, String msg) {
        setPersistentStringData(context, ENCRYPTION_RECOMMENDATION, msg);
    }

    public static String getEncryptionRecommendation(Context context) {
        return getPersistentStringData(context, ENCRYPTION_RECOMMENDATION, noAuditDefaultMsg);
    }

    public static void setAppsRecommendation(Context context, String msg) {
        setPersistentStringData(context, APPS_RECOMMENDATION, msg);
    }

    public static String getAppsRecommendation(Context context) {
        return getPersistentStringData(context, APPS_RECOMMENDATION, noAuditDefaultMsg);
    }

    public static boolean getSecuredWifiOnlyFlag(Context context) {
        return getBooleanFlag(context, SECURED_WIFI_FLAG);
    }

    public static void setSecuredWifiOnlyFlag(Context context, boolean flag) {
        setFlag(context, SECURED_WIFI_FLAG, flag);
    }

    public static boolean getDisableCamerasFlag(Context context) {
        return getBooleanFlag(context, DISABLE_CAMERAS_FLAG);
    }

    public static void setDisableCamerasFlag(Context context, boolean flag) {
        setFlag(context, DISABLE_CAMERAS_FLAG, flag);
    }

    public static void setLastAuditDate(Context context) {
        //String currentDateTimeString = new SimpleDateFormat("HH:mm:ss").format(new Date());
        String currentDateTimeString = new SimpleDateFormat("MMM d yyyy hh:mm aaa").format(new Date());
        setPersistentStringData(context, LAST_AUDIT_DATE, currentDateTimeString);
    }

    public static String getLastAuditDate(Context context) {
        return getPersistentStringData(context, LAST_AUDIT_DATE, context.getString(R.string.home_textview_default_last_audit));
    }
}
