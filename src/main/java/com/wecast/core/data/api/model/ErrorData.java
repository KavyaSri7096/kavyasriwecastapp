package com.wecast.core.data.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ageech@live.com
 */

public class ErrorData {

    @SerializedName("username")
    private List<String> username = null;

    @SerializedName("first_name")
    private List<String> first_name = null;

    @SerializedName("last_name")
    private List<String> last_name = null;

    @SerializedName("email")
    private List<String> email = null;

    @SerializedName("password")
    private List<String> password = null;

    @SerializedName("confirm_password")
    private List<String> confirmPassword = null;

    @SerializedName("age")
    private List<String> age = null;

    @SerializedName("sex")
    private List<String> sex = null;

    @SerializedName("purchase_pin")
    private List<String> purchasePin = null;

    @SerializedName("pin")
    private List<String> pin = null;

    public List<String> getUsername() {
        return username;
    }

    public List<String> getFirstName() {
        return first_name;
    }

    public List<String> getLastName() {
        return last_name;
    }

    public List<String> getEmail() {
        return email;
    }

    public List<String> getPassword() {
        return password;
    }

    public List<String> getConfirmPassword() {
        return confirmPassword;
    }

    public List<String> getAge() {
        return age;
    }

    public List<String> getSex() {
        return sex;
    }

    public List<String> getPin() {
        return pin;
    }

    public List<String> getPurchasePin() {
        return purchasePin;
    }
}
