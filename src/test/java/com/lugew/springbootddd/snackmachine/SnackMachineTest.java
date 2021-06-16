package com.lugew.springbootddd.snackmachine;

import com.lugew.springbootddd.Slot;
import com.lugew.springbootddd.Snack;
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
        snackMachine.loadSnacks(1, new Snack("Some snack"), 10, 1);
        snackMachine.insertMoney(Dollar);
        snackMachine.buySnack(1);
        assertEquals(snackMachine.getMoneyInTransaction().getAmount(),
                0);
        assertEquals(snackMachine.getMoneyInside().getAmount(), 1, 0.5);
        Slot slot = snackMachine.getSlots().stream().filter(x -> x.getPosition() ==
                1).findAny().orElse(null);
        assertEquals(slot.
                getQuantity(), 9);
    }


}
