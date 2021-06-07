package com.lugew.springbootddd.snackmachine;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class SnackMachine {
    private int oneCentCount;
    private int tenCentCount;
    private int quarterCount;
    private int oneDollarCount;
    private int fiveDollarCount;
    private int twentyDollarCount;


    private int oneCentCountInTransaction = 0;
    private int tenCentCountInTransaction = 0;
    private int quarterCountInTransaction = 0;
    private int oneDollarCountInTransaction = 0;
    private int fiveDollarCountInTransaction = 0;
    private int twentyDollarCountInTransaction = 0;

    public void insertMoney(int oneCentCount,
                            int tenCentCount,
                            int quarterCount,
                            int oneDollarCount,
                            int fiveDollarCount,
                            int twentyDollarCount) {
        oneCentCountInTransaction += oneCentCount;
        tenCentCountInTransaction += tenCentCount;
        quarterCountInTransaction += quarterCount;
        oneDollarCountInTransaction += oneDollarCount;
        fiveDollarCountInTransaction += fiveDollarCount;
        twentyDollarCountInTransaction += twentyDollarCount;

    }

    public void returnMoney() {
        oneCentCountInTransaction = 0;
        tenCentCountInTransaction = 0;
        quarterCountInTransaction = 0;
        oneDollarCountInTransaction = 0;
        fiveDollarCountInTransaction = 0;
        twentyDollarCountInTransaction = 0;
    }

    public void buySnack() {
        oneCentCount += oneCentCountInTransaction;
        tenCentCount += tenCentCountInTransaction;
        quarterCount += quarterCountInTransaction;
        oneDollarCount += oneDollarCountInTransaction;
        fiveDollarCount += fiveDollarCountInTransaction;
        twentyDollarCount += twentyDollarCountInTransaction;
        oneCentCountInTransaction = 0;
        tenCentCountInTransaction = 0;
        quarterCountInTransaction = 0;
        oneDollarCountInTransaction = 0;
        fiveDollarCountInTransaction = 0;
        twentyDollarCountInTransaction = 0;
    }
}