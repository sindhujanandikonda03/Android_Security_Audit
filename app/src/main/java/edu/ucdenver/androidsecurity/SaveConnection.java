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

public class SaveConnection {

    private String wifiName;
    private int imageIcon;
    private int networkID;

    public SaveConnection(String theWifiName, int theImageIcon, int theNetworkID) {
        wifiName = theWifiName;
        imageIcon = theImageIcon;
        networkID = theNetworkID;
    }

    public String getWifiName() {
        return wifiName;
    }

    public int getImageIcon() {
        return imageIcon;
    }

    public int getNetworkID() {
        return networkID;
    }
}
