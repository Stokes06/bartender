package fr.norsys.dojo.bartender.model;

import fr.norsys.dojo.bartender.CommunicationInterface;
import fr.norsys.dojo.bartender.behavior.BartenderBehavior;
import fr.norsys.dojo.bartender.behavior.StandardBartenderBehavior;
import fr.norsys.dojo.bartender.game.GameState;
import fr.norsys.dojo.bartender.model.Bar;

public class Bartender {

    private final GameState gameState;
    private final BartenderBehavior bartenderBehavior;
    private final Bar bar;

    public Bartender(GameState gameState,
                     Bar bar,
                     CommunicationInterface commandInterface) {
        this.gameState = gameState;
        this.bar = bar;
        this.bartenderBehavior = new StandardBartenderBehavior(this, commandInterface, gameState);
    }

    public void beginPlay() {

        this.bartenderBehavior.beginPlay();

        while (gameState.isPlaying()) {
            this.bartenderBehavior.listenCommand();
        }
        this.bartenderBehavior.bye();
    }

    public void stopService() {
        this.gameState.stopGame();
    }

    public Bar getBar() {
        return bar;
    }
}
