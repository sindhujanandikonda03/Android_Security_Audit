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
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class Class_App_Scan {
    public Class_App_Scan(Context context) {
        this.context = context;
    }

    private Context context;
    //PackageManager pm = context.getPackageManager();

    private boolean isUserApp(ApplicationInfo ai) {
        int mask = ApplicationInfo.FLAG_SYSTEM | ApplicationInfo.FLAG_UPDATED_SYSTEM_APP | ApplicationInfo.FLAG_TEST_ONLY;
        return (ai.flags & mask) == 0;
    }

    private List<ApplicationInfo> app_list_Info()
    {
        PackageManager pm = context.getPackageManager();
        List<ApplicationInfo> apps = pm.getInstalledApplications(0);
        List<ApplicationInfo> installedApps = new ArrayList<ApplicationInfo>();

        for(ApplicationInfo app : apps) {
            //checks for flags; if flagged, check if updated system app
            if(isUserApp(app)) {
                installedApps.add(app);
            }
        }
        return installedApps;
    }



    //return string application names of apps updated or installed.
    /*
    public ArrayList<String> Apps()
    {
        PackageManager pm = context.getPackageManager();
        Log.d("checking apps","List of Applications");
        List<ApplicationInfo> installedAppsinfo = app_list_Info();

        ArrayList<String> Applications = new ArrayList<String>();

        for(ApplicationInfo item : installedAppsinfo)
        {
            Applications.add((String)pm.getApplicationLabel(item));
            Log.d("APPLICATIONS",(String)pm.getApplicationLabel(item));
        }
        return Applications;
    }*/

    private List<String> request_permissions(ApplicationInfo app)
    {
        PackageManager pm = context.getPackageManager();
        Log.d("test", "App: " + pm.getApplicationLabel(app)+ " Package: " + app.packageName);
        List<String> requestedPermissions=new ArrayList<String>();
        //requestedPermissions[0]="No Permissions";
        try {
            PackageInfo packageInfo = pm.getPackageInfo(app.packageName, PackageManager.GET_PERMISSIONS);
            //Get Permissions
            if(packageInfo.requestedPermissions!=null) {
                String[] requestedPermissions_temp = (packageInfo.requestedPermissions);
                for (String item : requestedPermissions_temp) {
                    //Log.d("test of permission", item);
                    requestedPermissions.add(item);
                }
            }
        }
        catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();

        }
        return requestedPermissions;
    }

    private Map<String,Double> App_Risks = new HashMap<String,Double>();

    public Map<String,List<String>> app_permissions()
    {
        Rules_Map Risk= new Rules_Map();
        PackageManager pm = context.getPackageManager();
        List<ApplicationInfo> installedApps = new ArrayList<ApplicationInfo>();
        installedApps = app_list_Info();
        Map<String,List<String>> App_permissions = new HashMap<>();
        for(ApplicationInfo item: installedApps)
        {
            String name = (String)pm.getApplicationLabel(item);
            List<String> permissions = request_permissions(item);
            //name = name + "         Risk: " + Risk.Get_risk(permissions)+"%";
            App_Risks.put(name,Risk.Get_risk(permissions));
            App_permissions.put(name,permissions);
        }
        return App_permissions;
    }

    public Map<String,Double> getApp_Risks(){
        /*Log.d("Installed apps risk test", "key");
        Set<Map.Entry<String,Double>> entries=App_Risks.entrySet();
        for (Map.Entry<String,Double> entry : entries) {
            String key=entry.getKey();
            Double Risk=entry.getValue();

            Log.d(key, String.valueOf(Risk));

        }*/

        return App_Risks;
    }

    public List<String> get_risky_apps()
    {
        Rules_Map Risk= new Rules_Map();
        List<String> Risky_apps = new ArrayList<String>();
        PackageManager pm = context.getPackageManager();
        List<ApplicationInfo> installedApps = new ArrayList<ApplicationInfo>();
        installedApps = app_list_Info();
        for(ApplicationInfo item: installedApps)
        {
            String name = (String)pm.getApplicationLabel(item);

            List<String> permissions = request_permissions(item);
            if (Risk != null) {
                if(Risk.Get_risk(permissions)!=0)
                {
                    Risky_apps.add(name);
                }
            }
        }
        return Risky_apps;
    }

}
