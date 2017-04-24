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

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class AdminRequestActivity extends AppCompatActivity {
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private RadioGroup radioGroup;
    private AdminPoliciesManager adminPoliciesManager;
    private RadioButton maxTimeout;
    private Button b2;
    long x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        adminPoliciesManager = new AdminPoliciesManager(AdminRequestActivity.this);
        if (adminPoliciesManager.getmDPM() != null && adminPoliciesManager.isAdminActive()) {
            Intent intent = new Intent(AdminRequestActivity.this, MainActivity.class);
            startActivity(intent);
        } else { // App is NOT device administrator
            setContentView(R.layout.activity_admin_request);

            radioGroup = (RadioGroup)findViewById(R.id.timeout_radio_group);
            b2=(Button)findViewById(R.id.enabledAdminButton);

            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    int selectedRadio = radioGroup.getCheckedRadioButtonId();
                    maxTimeout = (RadioButton)findViewById(selectedRadio);

                    if(maxTimeout.getId() == R.id.timeout_option_1){
                        x=60000L;
                    }
                    if(maxTimeout.getId() == R.id.timeout_option_2){
                        x=120000L;
                    }
                    if(maxTimeout.getId() == R.id.timeout_option_3){
                        x=180000L;
                    }
                    Intent i=new Intent(AdminRequestActivity.this, MainActivity.class);
                    i.putExtra("time1",x);
                    System.out.println(x);
                    startActivity(i);

                }
            });
        }
    }
}
