package fr.norsys.dojo.bartender.behavior.service;

import fr.norsys.dojo.bartender.game.GameState;

public class FreeGiftService implements BehaviorTreeService {

    private final GameState gameState;

    public FreeGiftService(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public void execute() {
        if (gameState.isOfferNextNonAlcoholicDrink()) {
            gameState.setNextNonAlcoholicDrinkIsFree(true);
            gameState.setOfferNextNonAlcoholicDrink(false);
        } else {
            gameState.setNextNonAlcoholicDrinkIsFree(false);
        }
    }
}
