package fr.norsys.dojo.bartender.game;

import fr.norsys.dojo.bartender.Bar;
import fr.norsys.dojo.bartender.Bartender;

import java.util.Scanner;

public class Game {

    public static void run() {
        new Game().beginPlay();
    }

    public void beginPlay() {
        GameState gameState = GameState.makeGameState();
        Bar bar = Bar.makeBar();
        try (var scanner = new Scanner(System.in)) {
            final Bartender bartender = new Bartender(gameState, bar, scanner::nextLine);
            bartender.beginPlay();
        }
    }
}
