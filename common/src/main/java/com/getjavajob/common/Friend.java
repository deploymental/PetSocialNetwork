package com.getjavajob.common;

import com.getjavajob.common.enums.AccountsRelationStatus;

public class Friend {
    Account iniciator;
    Account account;
    AccountsRelationStatus ars;

    public Account getIniciator() {
        return iniciator;
    }

    public void setIniciator(Account iniciator) {
        this.iniciator = iniciator;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public AccountsRelationStatus getArs() {
        return ars;
    }

    public void setArs(AccountsRelationStatus ars) {
        this.ars = ars;
    }
}
