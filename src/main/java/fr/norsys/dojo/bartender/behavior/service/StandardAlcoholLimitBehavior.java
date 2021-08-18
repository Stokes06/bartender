package fr.norsys.dojo.bartender.behavior.service;

import fr.norsys.dojo.bartender.behavior.CommandProcess;
import fr.norsys.dojo.bartender.game.GameState;

public class StandardAlcoholLimitBehavior implements AlcoholLimitBehavior {

    private static final int MAX_ALCOHOL_ORDERS = 10;

    private final GameState gameState;
    private final CommandProcess onTooMuchAlcoholOrder;

    public StandardAlcoholLimitBehavior(GameState gameState, CommandProcess onTooMuchAlcoholOrder) {

        this.gameState = gameState;
        this.onTooMuchAlcoholOrder = onTooMuchAlcoholOrder;
    }

    @Override
    public boolean canOrderAlcohol() {
        final boolean tooMuch = drinkTooMuchAlcohol(gameState.getOrderedAlcoholsCount());
        if (tooMuch) {
            onTooMuchAlcoholOrder.process();
        } else {
            gameState.incrementAlcoholCounter();
        }
        return !tooMuch;
    }


    private boolean drinkTooMuchAlcohol(int alcoholsCount) {
        return alcoholsCount >= MAX_ALCOHOL_ORDERS;
    }
}
