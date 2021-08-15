package fr.norsys.dojo.bartender.behavior.decorator;

import fr.norsys.dojo.bartender.game.GameState;

public class AlcoholLimiterDecorator implements BehaviorTreeDecorator{

    private static final int MAX_ALCOHOL_ORDERS = 10;
    private final GameState gameState;

    public AlcoholLimiterDecorator(GameState gameState) {

        this.gameState = gameState;
    }


    @Override
    public boolean test() {
        final int alcoholsCount = gameState.getOrderedAlcoholsCount();
        if (alcoholsCount >= MAX_ALCOHOL_ORDERS) {
            System.out.println("You have been drinking enough alcohol for today !");
            return false;
        }
        gameState.incrementAlcoholCounter();
        return true;
    }
}
