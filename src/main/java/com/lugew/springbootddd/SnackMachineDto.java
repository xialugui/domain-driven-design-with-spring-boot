package com.lugew.springbootddd;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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

}
