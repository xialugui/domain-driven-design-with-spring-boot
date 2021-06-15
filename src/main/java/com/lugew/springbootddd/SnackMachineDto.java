package com.lugew.springbootddd;

import com.lugew.springbootddd.snackmachine.Money;
import com.lugew.springbootddd.snackmachine.SnackMachine;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author 夏露桂
 * @since 2021/6/10 12:00
 */
@Getter
@Setter
@Entity
public class SnackMachineDto {
    @Id
    @GeneratedValue
    private long id;
    private int oneCentCount;
    private int tenCentCount;
    private int quarterCount;
    private int oneDollarCount;
    private int fiveDollarCount;
    private int twentyDollarCount;
    private float moneyInTransaction;

    public SnackMachine convertToSnackMachine() {
        SnackMachine snackMachine = new SnackMachine();
        snackMachine.setId(id);
        snackMachine.setMoneyInTransaction(convertAmountToMoney());
        snackMachine.setMoneyInside(new
                Money(oneCentCount, tenCentCount, quarterCount, oneDollarCount, fiveDollarCount, twentyDollarCount));
        return snackMachine;
    }

    private Money convertAmountToMoney() {
        float amount = this.moneyInTransaction;

        int twentyDollarCount = (int) (amount / 20f);
        amount %= 20f;
        int fiveDollarCount = (int) (amount / 5f);
        amount %= 5f;
        int oneDollarCount = (int) (amount);
        amount %= 1f;
        int quarterCount = (int) (amount / 0.25f);
        amount %= 0.25f;
        int tenCentCount = (int) (amount / 0.10f);
        amount %= 0.10f;
        int oneCentCount = (int) (amount / 0.01f);

        return new Money(this.moneyInTransaction, oneCentCount, tenCentCount, quarterCount, oneDollarCount, fiveDollarCount, twentyDollarCount);
    }
}
