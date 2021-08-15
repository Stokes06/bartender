package fr.norsys.dojo.bartender.behavior.decorator;

import fr.norsys.dojo.bartender.game.GameState;

public class FreeGiftDecorator implements BehaviorTreeDecorator{

    private final GameState gameState;

    public FreeGiftDecorator(GameState gameState) {

        this.gameState = gameState;
    }
    @Override
    public boolean test() {
        if (gameState.isOfferNextNonAlcoholicDrink()) {
            gameState.setNextNonAlcoholicDrinkIsFree(true);
            gameState.setOfferNextNonAlcoholicDrink(false);
        } else {
            gameState.setNextNonAlcoholicDrinkIsFree(false);
        }
        return true;
    }
}
