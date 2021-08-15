package fr.norsys.dojo.bartender.game;

import fr.norsys.dojo.bartender.model.PlayerBank;

public class GameState {

    private PlayerBank playerBank;
    private boolean isPlaying;

    public static GameState makeGameState(PlayerBank playerBank) {
        final GameState gameState = new GameState();
        gameState.playerBank = playerBank;
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
}
