package fr.norsys.dojo.bartender.payment;

import java.util.Objects;

public class Money implements Comparable<Money> {

    private final double amount;

    private Money(double amount) {
        this.amount = roundAmountToCents(amount);
    }

    public static Money of(double amount) {
        return new Money(amount);
    }

    private static double roundAmountToCents(double amount) {
        return Math.round(amount * 100) / 100.0;
    }

    public Money subtract(Money money) {
        return new Money(amount - money.getAmount());
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public int compareTo(Money o) {
        return Double.compare(this.amount, o.amount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return Double.compare(money.amount, amount) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
