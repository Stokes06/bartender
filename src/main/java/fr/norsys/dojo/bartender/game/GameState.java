package fr.norsys.dojo.bartender.game;

public class GameState {

    private boolean isPlaying;

    public static GameState makeGameState() {
        final GameState gameState = new GameState();
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
}
