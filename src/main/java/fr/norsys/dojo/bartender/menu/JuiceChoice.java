package fr.norsys.dojo.bartender.menu;

import java.util.Arrays;

public enum JuiceChoice {
    PAPAYA("papaya"),
    POMEGRANATE("pomegranate"),
    BANANA("banana"),
    CUCUMBER("cucumber"),
    BAD_CHOICE("bad_choice");

    private final String name;

    JuiceChoice(String name) {
        this.name = name;
    }

    public String getJuiceName() {
        return name;
    }

    public static JuiceChoice getFromString(final String input) {
        return Arrays.stream(JuiceChoice.values())
                .filter(name -> name.getJuiceName().equalsIgnoreCase(input))
                .findAny()
                .orElse(BAD_CHOICE);
    }
}
