package com.lugew.springbootddd.snackmachine;

import com.lugew.springbootddd.common.AggregateRoot;
import com.lugew.springbootddd.sharedkernel.Money;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.lugew.springbootddd.sharedkernel.Money.None;


public final class SnackMachine extends AggregateRoot {

    @Getter
    @Setter
    private Money moneyInside;


    @Getter
    @Setter
    private float moneyInTransaction;
    @Setter
    private List<Slot> slots;

    public SnackMachine() {
        moneyInside = None;
        moneyInTransaction = 0;
        slots = new ArrayList<>();
        slots.add(new Slot(this, 1));
        slots.add(new Slot(this, 2));
        slots.add(new Slot(this, 3));
    }

    public Slot getSlot(int position) {
        return slots.stream().filter(x -> x.getPosition() ==
                position).findAny().orElse(null);
    }

    public SnackPile getSnackPile(int position) {
        return getSlot(position).getSnackPile();
    }

    public void insertMoney(Money money) {
        Money[] coinsAndNotes = {Money.Cent, Money.TenCent, Money.Quarter,
                Money.Dollar, Money.FiveDollar,
                Money.TwentyDollar};
        if (!Arrays.asList(coinsAndNotes).contains(money))
            throw new IllegalStateException();
        moneyInTransaction = moneyInTransaction + money.getAmount();
        moneyInside = moneyInside.add(money);
    }

    public void returnMoney() {
        Money moneyToReturn = moneyInside.allocate(moneyInTransaction);
        moneyInside = moneyInside.substract(moneyToReturn);
        moneyInTransaction = 0;
    }

    public void buySnack(int position) {
        Slot slot = getSlot(position);
        if (slot.getSnackPile().getPrice() > moneyInTransaction) {
            throw new IllegalStateException();
        }
        slot.setSnackPile(slot.getSnackPile().subtractOne());
        Money change = moneyInside.allocate(moneyInTransaction - slot.getSnackPile().getPrice());
        if (change.getAmount() < moneyInTransaction - slot.getSnackPile().getPrice()) {
            throw new IllegalStateException();
        }

        moneyInside = moneyInside.substract(change);
        moneyInTransaction = 0;
    }

    public SnackMachineDto convertToSnackMachineDto() {
        SnackMachineDto snackMachineDto = new SnackMachineDto();
        snackMachineDto.setId(id);
        snackMachineDto.setMoneyInTransaction(moneyInTransaction);
        List<SlotDto> slotDtoList = new ArrayList<>();
        for (Slot slot : slots) slotDtoList.add(slot.convertToSlotDto());
        snackMachineDto.setSlotDtoList(slotDtoList);
        snackMachineDto.setOneCentCount(moneyInside.getOneCentCount());
        snackMachineDto.setTenCentCount(moneyInside.getTenCentCount());
        snackMachineDto.setQuarterCount(moneyInside.getQuarterCount());
        snackMachineDto.setOneDollarCount(moneyInside.getOneDollarCount());
        snackMachineDto.setFiveDollarCount(moneyInside.getFiveDollarCount());
        snackMachineDto.setTwentyDollarCount(moneyInside.getTwentyDollarCount());
        return snackMachineDto;
    }

    public void loadSnacks(int position, SnackPile snackPile) {
        Slot slot = slots.stream().filter(x -> x.getPosition() ==
                position).findAny().orElse(null);
        if (slot != null) {
            slot.setSnackPile(snackPile);
        }
    }

    public void loadMoney(Money money) {
        moneyInside = moneyInside.add(money);
    }
}