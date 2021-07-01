package com.lugew.domaindrivendesignwithspringboot.snackmachine;

import com.lugew.domaindrivendesignwithspringboot.sharedkernel.Money;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "snackMachineId")
    private List<SlotDto> slotDtoList;

    public float getAmount() {
        return oneCentCount * 0.01f + tenCentCount * 0.10f + quarterCount * 0.25f +
                oneDollarCount * 1f
                + fiveDollarCount * 5f + twentyDollarCount * 20f;
    }

    public SnackMachine convertToSnackMachine() {
        SnackMachine snackMachine = new SnackMachine();
        snackMachine.setId(id);
        snackMachine.setMoneyInTransaction(moneyInTransaction);
        snackMachine.setMoneyInside(new
                Money(oneCentCount, tenCentCount, quarterCount,
                oneDollarCount, fiveDollarCount, twentyDollarCount));
        List<Slot> slotList = new ArrayList<>();
        for (SlotDto slotDto : slotDtoList) {
            slotList.add(slotDto.convertToSlot());
        }
        snackMachine.setSlots(slotList);
        return snackMachine;
    }

}
