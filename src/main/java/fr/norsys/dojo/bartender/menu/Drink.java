package fr.norsys.dojo.bartender.menu;

import static fr.norsys.dojo.bartender.menu.DrinkType.ALCOHOL;

public class Drink {
    private final DrinkType drinkType;
    private final String label;
    private final double price;
    private final int requiredAge;

    public Drink(DrinkType drinkType, String label, double price) {
        this(drinkType, label, price, 0);
    }
    public Drink(DrinkType drinkType, String label, double price, int requiredAge) {
        this.drinkType = drinkType;
        this.label = label;
        this.price = price;
        this.requiredAge = requiredAge;
    }

    public String getLabel() {
        return label;
    }

    public double getPrice() {
        return price;
    }

    public DrinkType getDrinkType() {
        return drinkType;
    }

    public int getRequiredAge() {
        return requiredAge;
    }

    public boolean containsAlcohol() {
        return drinkType == ALCOHOL;
    }
}
