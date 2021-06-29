package com.lugew.springbootddd.atm;


import org.junit.jupiter.api.Test;

import static com.lugew.springbootddd.sharedkernel.Money.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AtmTest {
    @Test
    public void take_money_exchanges_money_with_commission() {
        Atm atm = new Atm();
        atm.loadMoney(Dollar);
        atm.takeMoney(1);
        assertEquals(atm.getMoneyInside().getAmount(), 0, 0);
        assertEquals(atm.getMoneyCharged(), 1.01, 0.001);
    }

    @Test
    public void commission_is_at_least_one_cent() {
        Atm atm = new Atm();
        atm.loadMoney(Cent);
        atm.takeMoney(0.01f);
        assertEquals(atm.getMoneyCharged(), 0.02, 0.001);
    }

    @Test
    public void commission_is_rounded_up_to_the_next_cent() {
        Atm atm = new Atm();
        atm.loadMoney(Dollar.add(TenCent));
        atm.takeMoney(1.1f);
        assertEquals(atm.getMoneyCharged(), 1.12, 0.01);
    }
}
