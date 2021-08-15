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
        Bar bar = new Bar();
        bar.addDrink(new Drink(DrinkType.ALCOHOL, "beer", 4.50, 18));
        bar.addDrink(new Drink(DrinkType.ALCOHOL, "red wine", 3.13, 18));
        bar.addDrink(new Drink(DrinkType.ALCOHOL, "virgin bloody marry", 6.87, 14));
        bar.addDrink(new Drink(DrinkType.JUICE, "papaya", 2.00));
        bar.addDrink(new Drink(DrinkType.JUICE, "pomegranate", 2.00));
        bar.addDrink(new Drink(DrinkType.JUICE, "banana", 2.00));
        bar.addDrink(new Drink(DrinkType.JUICE, "cucumber", 2.00));
        bar.addDrink(new Drink(DrinkType.SODA, "soda", 3.42));
        return bar;
    }
}
