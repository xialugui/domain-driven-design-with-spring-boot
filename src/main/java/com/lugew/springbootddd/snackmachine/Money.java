package com.lugew.springbootddd.snackmachine;

import com.lugew.springbootddd.ValueObject;
import lombok.Getter;

/**
 * @author 夏露桂
 * @since 2021/6/7 11:59
 */
@Getter
public class Money extends ValueObject<Money> {

    public static Money None = new Money(0, 0, 0, 0, 0, 0);
    public static Money Cent = new Money(1, 0, 0, 0, 0, 0);
    public static Money TenCent = new Money(0, 1, 0, 0, 0, 0);
    public static Money Quarter = new Money(0, 0, 1, 0, 0, 0);
    public static Money Dollar = new Money(0, 0, 0, 1, 0, 0);
    public static Money FiveDollar = new Money(0, 0, 0, 0, 1, 0);
    public static Money TwentyDollar = new Money(0, 0, 0, 0, 0, 1);

    private final int oneCentCount;
    private final int tenCentCount;
    private final int quarterCount;
    private final int oneDollarCount;
    private final int fiveDollarCount;
    private final int twentyDollarCount;
    private float amount;

    public float getAmount() {
        return oneCentCount * 0.01f + tenCentCount * 0.10f + quarterCount * 0.25f +
                oneDollarCount * 1f
                + fiveDollarCount * 5f + twentyDollarCount * 20f;
    }

    public Money(int oneCentCount, int tenCentCount, int quarterCount, int
            oneDollarCount, int fiveDollarCount, int twentyDollarCount) {
        if (oneCentCount < 0)
            throw new IllegalStateException();
        if (tenCentCount < 0)
            throw new IllegalStateException();
        if (quarterCount < 0)
            throw new IllegalStateException();
        if (oneDollarCount < 0)
            throw new IllegalStateException();
        if (fiveDollarCount < 0)
            throw new IllegalStateException();
        if (twentyDollarCount < 0)
            throw new IllegalStateException();

        this.oneCentCount = oneCentCount;
        this.tenCentCount = tenCentCount;
        this.quarterCount = quarterCount;
        this.oneDollarCount = oneDollarCount;
        this.fiveDollarCount = fiveDollarCount;
        this.twentyDollarCount = twentyDollarCount;
    }

    public Money substract(Money other) {
        return new Money(
                oneCentCount - other.oneCentCount,
                tenCentCount - other.tenCentCount,
                quarterCount - other.quarterCount,
                oneDollarCount - other.oneDollarCount,
                fiveDollarCount - other.fiveDollarCount,
                twentyDollarCount - other.twentyDollarCount);
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

    @Override
    protected boolean equalsCore(Money other) {
        return oneCentCount == other.oneCentCount
                && tenCentCount == other.tenCentCount
                && quarterCount == other.quarterCount
                && oneDollarCount == other.oneDollarCount
                && fiveDollarCount == other.fiveDollarCount
                && twentyDollarCount == other.twentyDollarCount;
    }

    @Override
    protected int getHashCodeCore() {
        int hashCode = oneCentCount;
        hashCode = (hashCode * 397) ^ tenCentCount;
        hashCode = (hashCode * 397) ^ quarterCount;
        hashCode = (hashCode * 397) ^ oneDollarCount;
        hashCode = (hashCode * 397) ^ fiveDollarCount;
        hashCode = (hashCode * 397) ^ twentyDollarCount;
        return hashCode;
    }
}
