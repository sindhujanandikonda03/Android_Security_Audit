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
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MyExpandableListAdapter extends BaseExpandableListAdapter {

    Context context;
    List<String> items;
    Map<String, Double> App_Risks;
    Map<String, List<String>> ChildItems;

    public MyExpandableListAdapter(Context context, Map<String, Double> items, Map<String, List<String>> childItems) {
        this.context = context;
        this.items = new ArrayList<String>(items.keySet());
        App_Risks = items;
        ChildItems = childItems;
    }

    @Override
    public int getGroupCount() {
        return items.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return ChildItems.get(items.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return items.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return ChildItems.get(items.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String item = (String) getGroup(groupPosition);

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.expandablelist_parent,null);

        }
        TextView txtParent = (TextView) convertView.findViewById(R.id.txt_parent);
        TextView txtParentrisk = (TextView) convertView.findViewById(R.id.txt_parent_Risk);
        //Log.d("parent","before text");
        Double Risk= App_Risks.get(item);
        if (Risk>5.0)
        {
            txtParentrisk.setTextColor(Color.RED);
        } else {
            txtParentrisk.setTextColor(Color.BLACK);
        }
        if(item!=null) {
            txtParent.setText(item);
            txtParentrisk.setText("Risk: "+String.valueOf(Risk)+" %");
        }
        //Log.d("parent","After text");
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String ChildItem = (String) getChild(groupPosition,childPosition);

        if(convertView ==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.expandablelist_child,null);

        }

        TextView txtChild = (TextView) convertView.findViewById(R.id.txt_child);
        //Log.d("Child","before text");
        if(ChildItem!=null) {
            txtChild.setText(ChildItem);
        }
        //Log.d("Child","After text");
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
