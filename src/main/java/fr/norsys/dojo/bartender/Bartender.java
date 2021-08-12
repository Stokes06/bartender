package fr.norsys.dojo.bartender;

import fr.norsys.dojo.bartender.game.GameState;

public class Bartender {

    // todo Bartender holds availables products here with price, vat, name
    //  and behavior can use it to construct behavior map
    // List<Product> products = List.of(new Product(BEER, 4.5, 18.82)...)

    // maybe create a "Bar" class instead of list

    private final GameState gameState;
    private final BartenderBehavior bartenderBehavior;

    public Bartender(GameState gameState, CommunicationInterface commandInterface) {
        this.gameState = gameState;
        this.bartenderBehavior = new StandardBartenderBehavior(this, commandInterface);
    }

    public void beginPlay() {

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
