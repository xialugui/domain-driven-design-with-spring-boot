package com.lugew.springbootddd;

import com.lugew.springbootddd.snackmachine.SnackMachine;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.*;

@Entity
@Getter
@Setter
public class SlotDto {
    @Id
    @GeneratedValue
    private long id;
    private int quantity;
    private float price;
    @OneToOne(cascade = CascadeType.ALL)
    private SnackDto snackDto;
    private int position;

    public Slot convertToSlot() {
        Slot slot = new Slot();
        slot.setId(id);
        slot.setPosition(position);
        slot.setSnackPile(new SnackPile(snackDto.convertToSnack(), quantity, price));
        return slot;
    }
}