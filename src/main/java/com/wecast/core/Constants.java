package com.wecast.core;

/**
 * Created by ageech@live.com
 */

public interface Constants {

    String PREF_NAME = "wecast_pref";

    // Marble = 0
    // Prism = 1
    int DEFAULT_THEME = 0;

    long MIN_EPG_UPDATE_INTERVAL = 6 * 60 * 60 * 1000;     // 6 hours
    long MAX_EPG_RETRY_ALLOWED = 3;

    long MIN_LENGTH_PASSWORD = 4;
    long MIN_LENGTH_PIN = 4;
    long MIN_LENGTH_PURCHASE_PIN = 5;

    // Send video played X seconds after start
    int DEFAULT_TRACK_TIME = 15 * 1000;
    // Send play issues X seconds after start
    int DEFAULT_MIN_BUFFER_TIME = 10 * 1000;

    // Select app login type
    LoginType LOGIN_TYPE = LoginType.WITH_USERNAME_AND_PASSWORD;

    enum LoginType {
        WITH_USERNAME_AND_PASSWORD,
        WITH_UID
    }
}
