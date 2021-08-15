package fr.norsys.dojo.bartender.game;

import fr.norsys.dojo.bartender.model.Bar;
import fr.norsys.dojo.bartender.model.Bartender;
import fr.norsys.dojo.bartender.model.PlayerBank;
import fr.norsys.dojo.bartender.model.PlayerInformation;

import java.security.SecureRandom;
import java.util.Scanner;

public class Game {

    public static void run() {
        new Game().beginPlay();
    }

    public void beginPlay() {
        double initialPlayerCash = 50.0 + new SecureRandom().nextInt(10);
        PlayerBank playerBank = new PlayerBank(initialPlayerCash);
        PlayerInformation playerInformation = new PlayerInformation();
        GameState gameState = GameState.makeGameState(playerBank, playerInformation);
        Bar bar = Bar.makeBar();
        try (var scanner = new Scanner(System.in)) {
            final Bartender bartender = new Bartender(gameState, bar, scanner::nextLine);
            bartender.beginPlay();
        }
    }
}
