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

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class PasscodeActivity extends AppCompatActivity {

    private AdminPoliciesManager adminPoliciesManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passcode);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        adminPoliciesManager = new AdminPoliciesManager(PasscodeActivity.this);
        // gets timeout in milliseconds
        Long a = adminPoliciesManager.getmDPM().getMaximumTimeToLock(adminPoliciesManager.getmDeviceAdmin());
        TextView textviewTimeout = (TextView)findViewById(R.id.textview_max_timeout);
        textviewTimeout.setText("The maximum time to lock is " + (a/1000) + " seconds");

    }
}
