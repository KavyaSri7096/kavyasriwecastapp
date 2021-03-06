package com.wecast.core.utils;

import com.wecast.core.logger.Logger;

import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;

/**
 * Created by ageech@live.com
 */

public final class DeviceUtils {

    public static String getMacAddress() {
        try {
            String interfaceName = "wlan0";
            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface intf : interfaces) {
                if (!intf.getName().equalsIgnoreCase(interfaceName)) {
                    continue;
                }

                byte[] mac = intf.getHardwareAddress();
                if (mac == null) {
                    return "";
                }

                StringBuilder buf = new StringBuilder();
                for (byte aMac : mac) {
                    buf.append(String.format("%02X:", aMac));
                }
                if (buf.length() > 0) {
                    buf.deleteCharAt(buf.length() - 1);
                }
                return buf.toString();
            }
        } catch (Exception ex) {
            Logger.e("Cannot read MAC address");
        }
        return "";
    }
}
