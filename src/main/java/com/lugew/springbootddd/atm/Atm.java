package com.lugew.springbootddd.atm;


import com.lugew.springbootddd.common.AggregateRoot;
import com.lugew.springbootddd.sharedkernel.Money;
import lombok.Getter;
import lombok.Setter;

import static com.lugew.springbootddd.sharedkernel.Money.None;

@Getter
@Setter
public class Atm extends AggregateRoot {
    private static float commissionRate = 0.01f;
    private Money moneyInside = None;
    private float moneyCharged;

    public void loadMoney(Money money) {
        moneyInside = moneyInside.add(money);
    }

    public void takeMoney(float amount) {
        if (!canTakeMoney(amount).equals("")) {
            throw new IllegalStateException();
        }
        Money output = moneyInside.allocate(amount);
        moneyInside = moneyInside.substract(output);
        float amountWithCommission = calculateAmountWithCommission(amount);
        moneyCharged += amountWithCommission;
    }

    public String canTakeMoney(float amount) {
        if (amount <= 0f)
            return "Invalid amount";
        if (moneyInside.getAmount() < amount)
            return "Not enough money";
        if (!moneyInside.canAllocate(amount))
            return "Not enough change";
        return "";
    }

    public float calculateAmountWithCommission(float amount) {
        float commission = amount * commissionRate;
        float lessThanCent = commission % 0.01f;
        if (lessThanCent > 0) {
            commission = commission - lessThanCent + 0.01f;
        }
        return amount + commission;
    }
}