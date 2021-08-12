package fr.norsys.dojo.bartender.game;

import java.time.LocalDateTime;

public class GameState {

    private LocalDateTime startDateTime;

    private boolean isPlaying;

    public static GameState makeGameState() {
        final GameState gameState = new GameState();
        gameState.beginPlay();
        return gameState;
    }

    void beginPlay() {
        this.startDateTime = LocalDateTime.now();
        this.isPlaying = true;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void stopGame() {
        this.isPlaying = false;
    }

    public boolean isPlaying() {
        return isPlaying;
    }
}
