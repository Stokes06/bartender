package fr.norsys.dojo.bartender.payment;

import fr.norsys.dojo.bartender.model.menu.Drink;

public class VatApplier {

    public Money getVatPrice(Drink drink) {
        return Money.of(drink.getPrice() * (1 + (drink.getVat() / 100)));
    }

}
