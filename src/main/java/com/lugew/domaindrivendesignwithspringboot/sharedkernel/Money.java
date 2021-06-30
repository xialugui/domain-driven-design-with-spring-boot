package com.lugew.domaindrivendesignwithspringboot.sharedkernel;

import com.lugew.domaindrivendesignwithspringboot.common.ValueObject;
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

    public boolean canAllocate(float amount) {
        Money money = allocateCore(amount);
        return money.getAmount() == amount;
    }

    public Money allocate(float amount) {
        if (!canAllocate(amount))
            throw new IllegalStateException();
        return allocateCore(amount);
    }

    private Money allocateCore(float amount) {
        int twentyDollarCount = Math.min((int) (amount / 20), this.twentyDollarCount);
        amount = amount - twentyDollarCount * 20;
        int fiveDollarCount = Math.min((int) (amount / 5), this.fiveDollarCount);
        amount = amount - fiveDollarCount * 5;
        int oneDollarCount = Math.min((int) amount, this.oneDollarCount);
        amount = amount - oneDollarCount;
        int quarterCount = Math.min((int) (amount / 0.25f), this.quarterCount);
        amount = amount - quarterCount * 0.25f;
        int tenCentCount = Math.min((int) (amount / 0.1f), this.tenCentCount);
        amount = amount - tenCentCount * 0.1f;
        int oneCentCount = Math.min((int) (amount / 0.01f), this.oneCentCount);
        return new Money(
                oneCentCount,
                tenCentCount,
                quarterCount,
                oneDollarCount,
                fiveDollarCount,
                twentyDollarCount);
    }

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
        this.amount = getAmount();
    }

    public Money(float amount, int oneCentCount, int tenCentCount, int quarterCount, int
            oneDollarCount, int fiveDollarCount, int twentyDollarCount) {
        this(oneCentCount, tenCentCount, quarterCount, oneDollarCount, fiveDollarCount, twentyDollarCount);
        if (amount < 0) {
            throw new IllegalStateException();
        }
        this.amount = amount;
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

    public Money add(Money other) {
        return new Money(
                other.oneCentCount + this.oneCentCount,
                other.tenCentCount + this.tenCentCount,
                other.quarterCount + this.quarterCount,
                other.oneDollarCount + this.oneDollarCount,
                other.fiveDollarCount + this.fiveDollarCount,
                other.twentyDollarCount + this.twentyDollarCount);
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

    @Override
    public String toString() {
        if (getAmount() < 1)
            return "¢" + getAmount() * 100;
        return "$" + getAmount();
    }
}
