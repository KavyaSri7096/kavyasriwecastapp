package com.wecast.core;

import com.wecast.core.utils.DeviceUtils;

/**
 * Created by ageech@live.com
 */

public class WeCore {

    private static String apiUrl;
    private static String applicationId;
    private static String applicationType;
    private static String versionName;

    public static void init(String url, String type, String version) {
        apiUrl = url;
        applicationId = DeviceUtils.getMacAddress();
        applicationType = type;
        versionName = version;
    }

    public static String getApiUrl() {
        return apiUrl;
    }

    public static String getApplicationId() {
        return applicationId;
    }

    public static String getApplicationType() {
        return applicationType;
    }

    public static String getVersionName() {
        return versionName;
    }
}
