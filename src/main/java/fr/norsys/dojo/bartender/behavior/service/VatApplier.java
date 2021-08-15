package fr.norsys.dojo.bartender.behavior.service;

import fr.norsys.dojo.bartender.model.menu.Drink;

public class VatApplier {

    public double getVatPrice(Drink drink) {
        return roundToTwoDecimal(
                drink.getPrice() * (1 + (drink.getVat() / 100))
        );
    }

    private double roundToTwoDecimal(double amount) {
        return Math.round(amount * 100) / 100.0;
    }

}
