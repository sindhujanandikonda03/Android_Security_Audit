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

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ConnectionsAdapter extends ArrayAdapter<SaveConnection> {

    private Context context;
    private List<SaveConnection> saveConnectionList;

    public ConnectionsAdapter(Context theContext, int resource, ArrayList<SaveConnection> theSaveConnections) {
        super(theContext, resource, theSaveConnections);

        this.context = theContext;
        this.saveConnectionList = theSaveConnections;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        SaveConnection saveConnection = saveConnectionList.get(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_connection, null);

        TextView connectionName = (TextView)view.findViewById(R.id.item_connection_name);
        ImageView securityImage = (ImageView)view.findViewById(R.id.item_connection_security_image);

        connectionName.setText(saveConnection.getWifiName());
        //int imageID = context.getResources().getIdentifier(saveConnection.getImageIcon(), "drawable", context.getPackageName());
        securityImage.setImageResource(saveConnection.getImageIcon());

        return view;
    }
}
