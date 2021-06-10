package com.lugew.springbootddd.snackmachine;

import org.junit.jupiter.api.Test;

import static com.lugew.springbootddd.snackmachine.Money.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author 夏露桂
 * @since 2021/6/9 18:12
 */
public class SnackMachineTest {

    @Test
    public void return_money_empties_money_in_transaction() {
        SnackMachine snackMachine = new SnackMachine();
        snackMachine.insertMoney(Dollar);
        snackMachine.returnMoney();
        assertEquals(snackMachine.getMoneyInTransaction().getAmount(), 0, 0);
    }

    @Test
    public void inserted_money_goes_to_money_in_transaction() {
        SnackMachine snackMachine = new SnackMachine();
        snackMachine.insertMoney(Cent);
        snackMachine.insertMoney(Dollar);
        assertEquals(snackMachine.getMoneyInTransaction().getAmount(), 1.01f, 0);
    }

    @Test
    public void cannot_insert_more_than_one_coin_or_note_at_a_time() {
        SnackMachine snackMachine = new SnackMachine();
        Money twoCent = Money.add(Cent, Cent);
        assertThrows(IllegalStateException.class, () -> {
            snackMachine.insertMoney(twoCent);
        });
    }

    @Test
    public void money_in_transaction_goes_to_money_inside_after_purchase() {
        SnackMachine snackMachine = new SnackMachine();
        snackMachine.insertMoney(Dollar);
        snackMachine.insertMoney(Dollar);
        snackMachine.buySnack();
        assertEquals(snackMachine.getMoneyInTransaction(), None);
        assertEquals(snackMachine.getMoneyInside().getAmount(), 2, 0);
    }

}
