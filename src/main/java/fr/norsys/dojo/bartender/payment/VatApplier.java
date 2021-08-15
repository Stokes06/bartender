package fr.norsys.dojo.bartender.payment;

import fr.norsys.dojo.bartender.model.menu.Drink;

public class VatApplier {

    public double getVatPrice(Drink drink) {
        return CashAmountRounder.roundToTwoDecimal(
                drink.getPrice() * (1 + (drink.getVat() / 100))
        );
    }

}
