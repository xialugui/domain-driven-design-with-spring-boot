package com.lugew.springbootddd.snackmachine;

/**
 * @author 夏露桂
 * @since 2021/6/7 11:59
 */
public class Money {
    private int oneCentCount;
    private int tenCentCount;
    private int quarterCount;
    private int oneDollarCount;
    private int fiveDollarCount;
    private int twentyDollarCount;

    public Money(int oneCentCount, int tenCentCount, int quarterCount, int
            oneDollarCount, int fiveDollarCount, int twentyDollarCount) {
        this.oneCentCount = oneCentCount;
        this.tenCentCount = tenCentCount;
        this.quarterCount = quarterCount;
        this.oneDollarCount = oneDollarCount;
        this.fiveDollarCount = fiveDollarCount;
        this.twentyDollarCount = twentyDollarCount;
    }

    public static Money add(Money money1, Money money2) {
        return new Money(
                money1.oneCentCount + money2.oneCentCount,
                money1.tenCentCount + money2.tenCentCount,
                money1.quarterCount + money2.quarterCount,
                money1.oneDollarCount + money2.oneDollarCount,
                money1.fiveDollarCount + money2.fiveDollarCount,
                money1.twentyDollarCount + money2.twentyDollarCount);
    }
}
