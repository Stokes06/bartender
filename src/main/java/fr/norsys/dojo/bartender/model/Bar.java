package fr.norsys.dojo.bartender.model;

import fr.norsys.dojo.bartender.model.menu.Drink;
import fr.norsys.dojo.bartender.model.menu.DrinkType;

import java.util.ArrayList;
import java.util.List;

public class Bar {

    private final List<Drink> drinks = new ArrayList<>();

    public void addDrink(Drink drink) {
        drinks.add(drink);
    }

    public List<Drink> getDrinks() {
        return drinks;
    }

    public static Bar makeBar() {
        final Bar bar = new Bar();

        bar.addDrink(new Drink(DrinkType.ALCOHOL, "beer", 4.50, 18));
        bar.addDrink(new Drink(DrinkType.ALCOHOL, "red wine", 3.13, 18));
        bar.addDrink(new Drink(DrinkType.ALCOHOL, "virgin bloody marry", 6.87, 14));
        bar.addDrink(new Drink(DrinkType.JUICE, "papaya", 2.00));
        bar.addDrink(new Drink(DrinkType.JUICE, "pomegranate", 2.00));
        final Drink bananaJuice = new Drink(DrinkType.JUICE, "banana", 2.00);
        bar.addDrink(bananaJuice);
        bar.addDrink(new Drink(DrinkType.JUICE, "cucumber", 2.00));
        bar.addDrink(new Drink(DrinkType.SODA, "soda", 3.42));

        bar.getDrinks()
                .forEach(VatInitiator::initVat);

        bananaJuice.setVat(3.0);
        return bar;
    }

    static class VatInitiator {

        private VatInitiator() {
            // no need to create an instance of this class
        }

        private static final double JUICE_VAT = 12.0;
        private static final double SODA_VAT = 5.0;
        private static final double ALCOHOL_VAT = 20.0;

        static void initVat(Drink drink) {
            double vat = 0.0;
            switch (drink.getDrinkType()) {
                case ALCOHOL:
                    vat = ALCOHOL_VAT;
                    break;
                case JUICE:
                    vat = JUICE_VAT;
                    break;
                case SODA:
                    vat = SODA_VAT;
                    break;
            }
            drink.setVat(vat);
        }

    }
}
