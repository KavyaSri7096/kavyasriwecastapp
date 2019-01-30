package com.wecast.core.data.api.manager;

import com.wecast.core.data.api.model.ErrorData;
import com.wecast.core.data.api.model.PagedData;
import com.wecast.core.data.api.model.ResponseModel;
import com.wecast.core.data.api.service.AccountService;
import com.wecast.core.data.db.entities.Authentication;
import com.wecast.core.data.db.entities.PaymentHistory;
import com.wecast.core.data.db.entities.Subscription;
import com.wecast.core.data.db.entities.Token;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by ageech@live.com
 */

public class AccountManager {

    private AccountService accountService;

    @Inject
    public AccountManager(AccountService accountService) {
        this.accountService = accountService;
    }

    public Observable<ResponseModel<Authentication>> login(String username, String password) {
        return accountService.loginWithUsernameAndPassword(username, password);
    }

    public Observable<ResponseModel<Authentication>> loginWithUID() {
        return accountService.loginWithUID();
    }

    public Observable<ResponseModel<Token>> checkDeviceToken(String token) {
        return accountService.checkDeviceToken(token);
    }

    public Observable<ResponseModel> resetPassword(String email) {
        return accountService.resetPassword(email);
    }

    public Observable<ResponseModel<ErrorData>> register(String username, String firstName, String lastName, String email, String password, String age, String pin, String purchasePin, int gender, String subscriptionId) {
        return accountService.register(username, firstName, lastName, email, password, age, pin, purchasePin, gender, subscriptionId);
    }

    public Observable<ResponseModel<List<Subscription>>> getSubscriptions() {
        return accountService.getSubscriptions();
    }

    public Observable<ResponseModel<Authentication>> checkSubscription() {
        return accountService.checkSubscription();
    }

    public Observable<ResponseModel<ErrorData>> updateInfo(String email, String firstName, String lastName, String password, String confirmPassword, String purchasePin, String pin) {
        return accountService.updateInfo(email, firstName, lastName, password, confirmPassword, purchasePin, pin);
    }

    public Observable<ResponseModel<ErrorData>> updateInfo(String email, String firstName, String lastName, String currentPassword, String password, String confirmPassword, String purchasePin, String pin) {
        return accountService.updateInfo(email, firstName, lastName, password, confirmPassword, purchasePin, pin);
    }

    public Observable<ResponseModel<PagedData<PaymentHistory>>> getPaymentHistory(int page) {
        return accountService.getPaymentHistory(page);
    }

    public Observable<ResponseModel> logout() {
        return accountService.logout();
    }
}
