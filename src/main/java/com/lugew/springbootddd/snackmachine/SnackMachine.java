package com.lugew.springbootddd.snackmachine;

import com.lugew.springbootddd.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

import static com.lugew.springbootddd.snackmachine.Money.None;

@Getter
@Setter
public final class SnackMachine extends Entity {
    private Money moneyInside;
    private Money moneyInTransaction;

    public SnackMachine() {
        moneyInside = None;
        moneyInTransaction = None;
    }

    public void insertMoney(Money money) {
        Money[] coinsAndNotes = {Money.Cent, Money.TenCent, Money.Quarter,
                Money.Dollar, Money.FiveDollar,
                Money.TwentyDollar};
        if (!Arrays.asList(coinsAndNotes).contains(money))
            throw new IllegalStateException();

        moneyInTransaction = Money.add(moneyInTransaction, money);

    }

    public void returnMoney() {
        moneyInTransaction = None;
    }

    public void buySnack() {
        moneyInside = Money.add(moneyInside, moneyInTransaction);
        moneyInTransaction = None;
    }
}