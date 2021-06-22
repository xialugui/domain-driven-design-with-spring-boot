package com.lugew.springbootddd.snackmachine;

import com.lugew.springbootddd.AggregateRoot;
import com.lugew.springbootddd.Slot;
import com.lugew.springbootddd.Snack;
import com.lugew.springbootddd.SnackMachineDto;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.lugew.springbootddd.snackmachine.Money.None;

@Getter
@Setter
public final class SnackMachine extends AggregateRoot {
    private Money moneyInside;
    private Money moneyInTransaction;
    private List<Slot> slots;

    public SnackMachine() {
        moneyInside = None;
        moneyInTransaction = None;
        slots = new ArrayList<>();
        slots.add(new Slot(this, 1, null, 0, 1));
        slots.add(new Slot(this, 2, null, 0, 1));
        slots.add(new Slot(this, 3, null, 0, 1));
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


    public void buySnack(int position) {
        Slot slot = slots.stream().filter(x -> x.getPosition() ==
                position).findAny().orElse(null);
        slot.setQuantity(slot.getQuantity() - 1);
        moneyInside = Money.add(moneyInside, moneyInTransaction);
        moneyInTransaction = None;
    }

    public SnackMachineDto convertToSnackMachineDto() {
        SnackMachineDto snackMachineDto = new SnackMachineDto();
        snackMachineDto.setId(id);
        snackMachineDto.setMoneyInTransaction(moneyInTransaction.getAmount());
        snackMachineDto.setOneCentCount(moneyInside.getOneCentCount());
        snackMachineDto.setTenCentCount(moneyInside.getTenCentCount());
        snackMachineDto.setQuarterCount(moneyInside.getQuarterCount());
        snackMachineDto.setOneDollarCount(moneyInside.getOneDollarCount());
        snackMachineDto.setFiveDollarCount(moneyInside.getFiveDollarCount());
        snackMachineDto.setTwentyDollarCount(moneyInside.getTwentyDollarCount());
        return snackMachineDto;
    }

    public void loadSnacks(int position, Snack snack, int quantity, float price) {
        Slot slot = slots.stream().filter(x -> x.getPosition() ==
                position).findAny().orElse(null);
        if (slot != null) {
            slot.setSnack(snack);
            slot.setQuantity(quantity);
            slot.setPrice(price);
        }
    }
}