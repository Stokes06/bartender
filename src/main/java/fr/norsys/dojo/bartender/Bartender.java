package fr.norsys.dojo.bartender;

import fr.norsys.dojo.bartender.game.GameState;
import fr.norsys.dojo.bartender.menu.Drink;
import fr.norsys.dojo.bartender.menu.DrinkType;

public class Bartender {

    private final GameState gameState;
    private final BartenderBehavior bartenderBehavior;
    private final Bar bar;

    public Bartender(GameState gameState,
                     Bar bar,
                     CommunicationInterface commandInterface) {
        this.gameState = gameState;
        this.bar = bar;
        this.bartenderBehavior = new StandardBartenderBehavior(this, commandInterface);
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

    public GameState getGameState() {
        return gameState;
    }

    public Bar getBar() {
        return bar;
    }
}
