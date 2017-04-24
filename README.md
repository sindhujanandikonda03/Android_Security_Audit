# Android Security Enforcer

Since Android is an open source platform, companies all over the world use it as the operating 
system for their devices. Therefore, the default security settings are not uniform across all
Android devices. This can result in potential security vulnerabilities in some devices. The goal of
this project is to contribute an application to the community that will make Android devices more
secured. In order to accomplish this, the Android application will do the following:

1. Enforce the use of a passcode. One of the most important and simple features for securing a
mobile device is to add a passcode to unlock it.

2. Define a minimum length for the device passcode. Having a passcode that is only two characters
defeats the whole purpose of a passcode since it will be relatively easy to crack. By enforcing a
minimum length passcode, we will greatly reduce the possibility of someone cracking it.

3. Set maximum inactivity time out before device is locked. Ensures that the device is locked after
a certain amount of time.

4. Scan device for encryption state. Makes recommendation to encrypt device if supported.

5. Provide option for the user to disable camera. There is a possibility of a hostile application to
turn on and use the device’s camera without the user knowing. The application will give the user an
option to disable the device camera at a system level to prevent this.

6. Scan all current apps and their permissions (provide feedback about risky permissions). By
scanning all the installed apps and their current granted system permissions, we can give the user
feedback about which permissions represent more risk. We will rank the risk on the different
permissions using the research paper [_Exploring Permission-Induced Risk in Android Applications for
Malicious Application Detection_](http://ieeexplore.ieee.org/document/6891250/) by Wei Wang, Xing
Wang and Dawei Feng. This paper ranks each of the Android permissions by how much risk they
represent.

7. Provide feature for the user to disable unsecured WIFI connections. The application will give the
user feedback when he/she connects to an unsecured WiFi access point. Connecting to an unsecured
WiFi opens the user to potential hackers using tools such as Packets Sniffers to get information
(especially by using sites that don’t establish a secured connections, such as HTTP, FTP, etc.).

8. Check if _Install from Unknown Sources_ settings is checked. There are a number of security 
settings that are hard to find for the average mobile user, one of these is the install from unknown
sources. By letting the user know if this setting is enabled, we will greatly increase the device
security by preventing the installation of apps that have not been signed by a developer or vetted
by the Google Play store. Android does not permit any application to change the value of this
setting programmatically, so the app is only able to make a recommendation and let the user know.

9. Add feature that audits the general security of a device by doing the following:
* Present apps that have potentially risky permissions
* Check if device is encrypted
* Check if cameras are disabled
* Check if device is able to connect to unsecured WiFi networks
* Check _Install from Unknown Sources_ setting