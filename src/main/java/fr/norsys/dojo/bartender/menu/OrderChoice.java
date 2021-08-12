package fr.norsys.dojo.bartender.menu;

import java.util.Arrays;

public enum OrderChoice {
    JUICE("juice"),
    BEER("beer"),
    SODA("soda"),
    NOTHING("nothing"),
    BAD_CHOICE("bad_choice");

    private final String orderChoiceName;

    OrderChoice(String name) {
        this.orderChoiceName = name;
    }

    public String getOrderChoiceName() {
        return orderChoiceName;
    }

    public boolean isMenuChoice() {
        return this != NOTHING && this != BAD_CHOICE;
    }

    public static OrderChoice getFromString(final String input) {
        return Arrays.stream(OrderChoice.values())
                .filter(name -> name.getOrderChoiceName().equalsIgnoreCase(input))
                .findAny()
                .orElse(BAD_CHOICE);
    }

}
