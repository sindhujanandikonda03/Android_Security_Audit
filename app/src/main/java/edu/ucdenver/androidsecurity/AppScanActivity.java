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
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AppScanActivity extends AppCompatActivity {

    Map<String,Double> installedApps;
    Map<String,List<String>> permissions = new HashMap<>();
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_scan);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floating_btn_app_scan);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressDialog = ProgressDialog.show(AppScanActivity.this, "App Scan",
                        "Scanning apps, please wait...", true);

                new Thread(new Runnable() {
                    @Override
                    public void run()
                    {
                        // scanning....
                        populate();
                        Log.d("Data:", "Populated");


                        //Log.d("test", permissions.get(0));
                        final ExpandableListView Applist = (ExpandableListView)findViewById(R.id.EList_apps);
                        final ExpandableListAdapter EAdapter= new MyExpandableListAdapter(AppScanActivity.this,installedApps,permissions);
                        //Log.d("permission check",EAdapter.getChild(2,1).toString());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run()
                            {
                                Applist.setAdapter(EAdapter);

                                TextView txt_app_risk = (TextView)findViewById(R.id.txt_App_risks);
                                int Risk_count=0;
                                Set<Map.Entry<String, Double>> entries=installedApps.entrySet();
                                for (Map.Entry<String, Double> entry : entries) {
                                    String App = entry.getKey();
                                    Double Risk = entry.getValue();
                                    if(Risk>10.0)
                                    {
                                        Risk_count++;
                                    }
                                }
                                txt_app_risk.setText(String.valueOf((Risk_count))+"/"+installedApps.size());
                                progressDialog.dismiss();
                            }
                        });
                    }
                }).start();

            }
        });



    }

    private void populate()
    {
        //= new ArrayList<String>();
        Class_App_Scan app_scan = new Class_App_Scan(AppScanActivity.this);
        /*
        for(String item: installedApps) {
            Log.d("Installed apps test", item);
        }*/
        permissions=app_scan.app_permissions();
        installedApps = app_scan.getApp_Risks();

        /*for(String item: installedApps) {
            permissions.put(item,app_scan.app_permissions(item));

        }*/

        //code to check data loaded into the hash map
        /*Set<Map.Entry<String, List<String>>> entries=permissions.entrySet();
        for (Map.Entry<String, List<String>> entry : entries) {
            String key=entry.getKey();
            List<String> permission_app=entry.getValue();
            Log.d("Installed apps test", key);
            for(String each: permission_app)
            {
                Log.d(key, each);
            }
        }*/

    }
}