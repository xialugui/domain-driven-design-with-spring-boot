package com.lugew.springbootddd.snackmachine;

import com.lugew.springbootddd.Snack;
import com.lugew.springbootddd.SnackPile;
import org.junit.jupiter.api.Test;

import static com.lugew.springbootddd.snackmachine.Money.Cent;
import static com.lugew.springbootddd.snackmachine.Money.Dollar;
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
    public void buySnack_trades_inserted_money_for_a_snack() {

        SnackMachine snackMachine = new SnackMachine();

        snackMachine.loadSnacks(1, new SnackPile(new Snack("Some snack"), 10, 1));
        snackMachine.insertMoney(Dollar);
        snackMachine.buySnack(1);
        assertEquals(snackMachine.getMoneyInTransaction(), Money.None);
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
        snackMachine.loadSnacks(1, new SnackPile(new Snack("Some snack"), 1, 2));
        snackMachine.insertMoney(Dollar);
        assertThrows(IllegalStateException.class, () -> {
            snackMachine.buySnack(1);
        });

    }

}
