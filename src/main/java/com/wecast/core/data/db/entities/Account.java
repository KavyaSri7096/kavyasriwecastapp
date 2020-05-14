package com.wecast.core.data.db.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ageech@live.com
 */

public class Account {

    @SerializedName("id")
    private int id;

    @SerializedName("email")
    private String email;

    @SerializedName("first_name")
    private String firstName;

    @SerializedName("last_name")
    private String lastName;

    @SerializedName("address")
    private String address;

    @SerializedName("age")
    private int age;

    @SerializedName("facebook")
    private String facebook;

    @SerializedName("sex")
    private boolean sex;

    @SerializedName("city_id")
    private int cityId;

    @SerializedName("country_id")
    private int countryId;

    @SerializedName("zip")
    private String zip;

    @SerializedName("tax_id")
    private String taxId;

    @SerializedName("subscription_id")
    private int subscriptionId;

    @SerializedName("market_id")
    private int marketId;

    @SerializedName("home_internet_speed")
    private String homeInternetSpeed;

    @SerializedName("balance")
    private float balance;

    @SerializedName("purchase_pin")
    private String purchasePin;

    @SerializedName("expire")
    private String expire;

    @SerializedName("status")
    private int status;

    @SerializedName("type")
    private int type;

    @SerializedName("deleted")
    private boolean deleted;

    @SerializedName("card_password")
    private String cardPassword;

    @SerializedName("first_login_details")
    private boolean firstLoginDetails;

    @SerializedName("last_login")
    private String lastLogin;

    @SerializedName("token")
    private String token;

    @SerializedName("payment_type")
    private String paymentType;

    @SerializedName("countries_blocking")
    private boolean countriesBlocking;

    @SerializedName("devices_blocking")
    private boolean devicesBlocking;

    @SerializedName("login_policy")
    private boolean loginPolicy;

    @SerializedName("login_method")
    private String loginMethod;

    @SerializedName("billing_first_name")
    private String billingFirstName;

    @SerializedName("billing_last_name")
    private String billingLastName;

    @SerializedName("billing_country_id")
    private int billingCountryId;

    @SerializedName("billing_city_id")
    private int billingCityId;

    @SerializedName("billing_address")
    private String billingAddress;

    @SerializedName("billing_phone")
    private String billingPhone;

    @SerializedName("billing_email")
    private String billingEmail;

    @SerializedName("reseller_user")
    private int resellerUser;

    @SerializedName("tax_number")
    private int taxNumber;

    @SerializedName("subscription_expired")
    private boolean subscriptionExpired;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public int getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(int subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public int getMarketId() {
        return marketId;
    }

    public void setMarketId(int marketId) {
        this.marketId = marketId;
    }

    public String getHomeInternetSpeed() {
        return homeInternetSpeed;
    }

    public void setHomeInternetSpeed(String homeInternetSpeed) {
        this.homeInternetSpeed = homeInternetSpeed;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public String getPurchasePin() {
        return purchasePin;
    }

    public void setPurchasePin(String purchasePin) {
        this.purchasePin = purchasePin;
    }

    public String getExpire() {
        return expire;
    }

    public void setExpire(String expire) {
        this.expire = expire;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getCardPassword() {
        return cardPassword;
    }

    public void setCardPassword(String cardPassword) {
        this.cardPassword = cardPassword;
    }

    public boolean isFirstLoginDetails() {
        return firstLoginDetails;
    }

    public void setFirstLoginDetails(boolean firstLoginDetails) {
        this.firstLoginDetails = firstLoginDetails;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public boolean isCountriesBlocking() {
        return countriesBlocking;
    }

    public void setCountriesBlocking(boolean countriesBlocking) {
        this.countriesBlocking = countriesBlocking;
    }

    public boolean isDevicesBlocking() {
        return devicesBlocking;
    }

    public void setDevicesBlocking(boolean devicesBlocking) {
        this.devicesBlocking = devicesBlocking;
    }

    public boolean isLoginPolicy() {
        return loginPolicy;
    }

    public void setLoginPolicy(boolean loginPolicy) {
        this.loginPolicy = loginPolicy;
    }

    public String getLoginMethod() {
        return loginMethod;
    }

    public void setLoginMethod(String loginMethod) {
        this.loginMethod = loginMethod;
    }

    public String getBillingFirstName() {
        return billingFirstName;
    }

    public void setBillingFirstName(String billingFirstName) {
        this.billingFirstName = billingFirstName;
    }

    public String getBillingLastName() {
        return billingLastName;
    }

    public void setBillingLastName(String billingLastName) {
        this.billingLastName = billingLastName;
    }

    public int getBillingCountryId() {
        return billingCountryId;
    }

    public void setBillingCountryId(int billingCountryId) {
        this.billingCountryId = billingCountryId;
    }

    public int getBillingCityId() {
        return billingCityId;
    }

    public void setBillingCityId(int billingCityId) {
        this.billingCityId = billingCityId;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getBillingPhone() {
        return billingPhone;
    }

    public void setBillingPhone(String billingPhone) {
        this.billingPhone = billingPhone;
    }

    public String getBillingEmail() {
        return billingEmail;
    }

    public void setBillingEmail(String billingEmail) {
        this.billingEmail = billingEmail;
    }

    public int getResellerUser() {
        return resellerUser;
    }

    public void setResellerUser(int resellerUser) {
        this.resellerUser = resellerUser;
    }

    public int getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(int taxNumber) {
        this.taxNumber = taxNumber;
    }

    public boolean isSubscriptionExpired() {
        return subscriptionExpired;
    }

    public void setSubscriptionExpired(boolean subscriptionExpired) {
        this.subscriptionExpired = subscriptionExpired;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                ", facebook='" + facebook + '\'' +
                ", sex=" + sex +
                ", cityId=" + cityId +
                ", countryId=" + countryId +
                ", zip='" + zip + '\'' +
                ", taxId='" + taxId + '\'' +
                ", subscriptionId=" + subscriptionId +
                ", marketId=" + marketId +
                ", homeInternetSpeed='" + homeInternetSpeed + '\'' +
                ", balance=" + balance +
                ", purchasePin='" + purchasePin + '\'' +
                ", expire='" + expire + '\'' +
                ", status=" + status +
                ", type=" + type +
                ", deleted=" + deleted +
                ", cardPassword='" + cardPassword + '\'' +
                ", firstLoginDetails=" + firstLoginDetails +
                ", lastLogin='" + lastLogin + '\'' +
                ", token='" + token + '\'' +
                ", paymentType='" + paymentType + '\'' +
                ", countriesBlocking=" + countriesBlocking +
                ", devicesBlocking=" + devicesBlocking +
                ", loginPolicy=" + loginPolicy +
                ", loginMethod='" + loginMethod + '\'' +
                ", billingFirstName='" + billingFirstName + '\'' +
                ", billingLastName='" + billingLastName + '\'' +
                ", billingCountryId=" + billingCountryId +
                ", billingCityId=" + billingCityId +
                ", billingAddress='" + billingAddress + '\'' +
                ", billingPhone='" + billingPhone + '\'' +
                ", billingEmail='" + billingEmail + '\'' +
                ", resellerUser=" + resellerUser +
                ", taxNumber=" + taxNumber +
                ", subscriptionExpired=" + subscriptionExpired +
                '}';
    }
}
