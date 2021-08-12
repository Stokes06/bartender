package fr.norsys.dojo.bartender.game;

import fr.norsys.dojo.bartender.Bartender;

import java.util.Arrays;
import java.util.Scanner;

public class Game {

    public static void run(String[] args) {
        new Game().beginPlay(args);
    }

    public void beginPlay(String[] args) {
        System.out.println("Game started with args" + Arrays.toString(args));
        GameState gameState = GameState.makeGameState();
        try (var scanner = new Scanner(System.in)) {
            final Bartender bartender = new Bartender(gameState, scanner::nextLine);
            bartender.waitForCommands();
        }
    }
}
