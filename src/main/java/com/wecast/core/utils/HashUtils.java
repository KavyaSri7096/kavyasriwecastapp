package com.wecast.core.utils;

import android.util.Base64;

/**
 * Created by ageech@live.com
 */

public final class HashUtils {

    public static String getBasicAuthDigest(String username, String password) {
        String usernameAndPassword = username + ":" + password;
        return Base64.encodeToString(usernameAndPassword.getBytes(), Base64.NO_WRAP);
    }
}