package com.lugew.springbootddd;

import com.lugew.springbootddd.snackmachine.SnackMachine;
import lombok.Getter;
import lombok.Setter;

/**
 * @author 夏露桂
 * @since 2021/6/16 10:47
 */
@Getter
@Setter
public class Slot extends Entity {
    private Snack snack;
    private int quantity;
    private float price;
    private SnackMachine snackMachine;
    private int position;

    public Slot() {
    }

    public Slot(SnackMachine snackMachine, int position, Snack snack, int quantity,
                float price) {
        this.snackMachine = snackMachine;
        this.position = position;
        this.snack = snack;
        this.quantity = quantity;
        this.price = price;
    }
}
