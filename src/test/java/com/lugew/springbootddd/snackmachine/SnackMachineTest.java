package com.lugew.springbootddd.snackmachine;

import com.lugew.springbootddd.sharedkernel.Money;
import org.junit.jupiter.api.Test;

import static com.lugew.springbootddd.sharedkernel.Money.*;
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
        assertEquals(snackMachine.getMoneyInTransaction(), 0, 0);
    }

    @Test
    public void inserted_money_goes_to_money_in_transaction() {
        SnackMachine snackMachine = new SnackMachine();
        snackMachine.insertMoney(Cent);
        snackMachine.insertMoney(Dollar);
        assertEquals(snackMachine.getMoneyInTransaction(), 1.01f, 0);
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
    public void buySnack_trades_inserted_money_for_a_snack() {

        SnackMachine snackMachine = new SnackMachine();

        snackMachine.loadSnacks(1, new SnackPile(Snack.Chocolate, 10, 1));
        snackMachine.insertMoney(Dollar);
        snackMachine.buySnack(1);
        assertEquals(snackMachine.getMoneyInTransaction(), Money.None.getAmount());
        assertEquals(snackMachine.getMoneyInside().getAmount(), 1, 0.5);
        assertEquals(snackMachine.getSnackPile(1).getQuantity(), 9);
    }

    @Test
    public void cannot_make_purchase_when_there_is_no_snacks() {
        SnackMachine snackMachine = new SnackMachine();
        assertThrows(IllegalStateException.class, () -> {
            snackMachine.buySnack(1);
        });
    }

    @Test
    public void cannot_make_purchase_if_not_enough_money_inserted() {
        SnackMachine snackMachine = new SnackMachine();
        snackMachine.loadSnacks(1, new SnackPile(Snack.Chocolate, 1, 2));
        snackMachine.insertMoney(Dollar);
        assertThrows(IllegalStateException.class, () -> {
            snackMachine.buySnack(1);
        });

    }

    @Test
    public void snack_machine_returns_money_with_highest_denomination_first() {
        SnackMachine snackMachine = new SnackMachine();
        snackMachine.loadMoney(Dollar);
        snackMachine.insertMoney(Quarter);
        snackMachine.insertMoney(Quarter);
        snackMachine.insertMoney(Quarter);
        snackMachine.insertMoney(Quarter);
        snackMachine.returnMoney();
        assertEquals(snackMachine.getMoneyInside().getQuarterCount(), 4);
        assertEquals(snackMachine.getMoneyInside().getOneDollarCount(), 0);
    }

    @Test
    public void after_purchase_change_is_returned() {
        SnackMachine snackMachine = new SnackMachine();
        snackMachine.loadSnacks(1, new SnackPile(Snack.Chocolate, 1,
                0.5f));
        snackMachine.loadMoney(
                new Money(0, 10, 0, 0, 0, 0)
        );

        snackMachine.insertMoney(Dollar);
        snackMachine.buySnack(1);
        assertEquals(snackMachine.getMoneyInside().getAmount(), 1.5, 0);
        assertEquals(snackMachine.getMoneyInTransaction(), 0, 0);
    }

    @Test
    public void cannot_buy_snack_if_not_enough_change() {
        SnackMachine snackMachine = new SnackMachine();
        snackMachine.loadSnacks(1, new SnackPile(Snack.Chocolate, 1, 0.5f));
        snackMachine.insertMoney(Dollar);
        assertThrows(IllegalStateException.class, () -> {
            snackMachine.buySnack(1);
        });
    }
}
