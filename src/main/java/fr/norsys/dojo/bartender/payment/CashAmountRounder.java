package fr.norsys.dojo.bartender.payment;

public class CashAmountRounder {

    private CashAmountRounder(){}

    public static double roundToTwoDecimal(double amount) {
        return Math.round(amount * 100) / 100.0;
    }
}
