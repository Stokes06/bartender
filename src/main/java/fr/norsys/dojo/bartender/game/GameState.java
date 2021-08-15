package fr.norsys.dojo.bartender.game;

import fr.norsys.dojo.bartender.model.PlayerBank;
import fr.norsys.dojo.bartender.model.PlayerInformation;

public class GameState {

    private PlayerBank playerBank;
    private PlayerInformation playerInformation;
    private boolean isPlaying;
    private int orderedAlcoholsCount = 0;

    public GameState(PlayerBank playerBank, PlayerInformation playerInformation) {
        this.playerBank = playerBank;
        this.playerInformation = playerInformation;
    }

    public static GameState makeGameState(PlayerBank playerBank, PlayerInformation playerInformation) {
        final GameState gameState = new GameState(playerBank, playerInformation);
        gameState.beginPlay();
        return gameState;
    }

    void beginPlay() {
        this.isPlaying = true;
    }

    public void stopGame() {
        this.isPlaying = false;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public PlayerBank getPlayerBank() {
        return playerBank;
    }

    public PlayerInformation getPlayerInformation() {
        return playerInformation;
    }

    public void incrementAlcoholCounter() {
        ++orderedAlcoholsCount;
    }

    public int getOrderedAlcoholsCount() {
        return orderedAlcoholsCount;
    }
}
