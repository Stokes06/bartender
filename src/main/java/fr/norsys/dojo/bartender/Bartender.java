package fr.norsys.dojo.bartender;

import fr.norsys.dojo.bartender.game.GameState;

public class Bartender {

    private final GameState gameState;
    private final BartenderBehavior bartenderBehavior;

    public Bartender(GameState gameState, CommunicationInterface commandInterface) {
        this.gameState = gameState;
        this.bartenderBehavior = new StandardBartenderBehavior(this, commandInterface);
    }

    public void waitForCommands() {

        while (gameState.isPlaying()) {
            this.bartenderBehavior.suggestOptions();
            this.bartenderBehavior.react();
        }
        this.bartenderBehavior.bye();
    }

    public void stopService() {
        this.gameState.stopGame();
    }

    public GameState getGameState() {
        return gameState;
    }
}
