package com.lugew.springbootddd;

import com.lugew.springbootddd.snackmachine.Money;
import lombok.Getter;
import lombok.Setter;

/**
 * @author 夏露桂
 * @since 2021/6/10 12:00
 */
@Getter
@Setter
public class SnackMachineDto {
    private Money moneyInside;
    private Money moneyInTransaction;
    private long id;
}
