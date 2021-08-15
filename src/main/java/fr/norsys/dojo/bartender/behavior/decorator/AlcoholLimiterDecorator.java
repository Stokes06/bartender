package fr.norsys.dojo.bartender.behavior.decorator;

import fr.norsys.dojo.bartender.behavior.CommandProcess;
import fr.norsys.dojo.bartender.game.GameState;

public class AlcoholLimiterDecorator implements BehaviorTreeDecorator{

    private static final int MAX_ALCOHOL_ORDERS = 10;
    private static final int MAX_RETRY_AFTER_BARTENDER_SAID_NO = 3;
    private final GameState gameState;
    private final CommandProcess exitGameProcess;

    public AlcoholLimiterDecorator(GameState gameState, CommandProcess exitGameProcess) {

        this.gameState = gameState;
        this.exitGameProcess = exitGameProcess;
    }


    @Override
    public boolean test() {
        final int alcoholsCount = gameState.getOrderedAlcoholsCount();
        /* TODO : dynamically change the bartender behavior when > 10 alcohol so we don't have all this complexity
        * -> create two alcohol behavior
        * pass the behavior in parameter
        * 
        * pass in parameter a method reference to change the behavior : bartender.setAlcoholBehavior(new DoNotServeAlcoholAndKickIfInsist())
        * this decorator will become super simple
        */
        if (drinkToMuchAlcohol(alcoholsCount)) {
            if(askTooMuchAfterBartenderSaidNo(gameState.getRetryAfterBartenderSaidNo())){
                System.out.println("You don't listen, you will have to leave now !");
                exitGameProcess.process();
            } else {
                System.out.println("You have been drinking enough alcohol for today !");
                gameState.incrementRetryAfterBartender();
            }
            return false;
        }
        gameState.incrementAlcoholCounter();
        return true;
    }

    private boolean askTooMuchAfterBartenderSaidNo(int retryAfterBartenderSaidNo) {
        return retryAfterBartenderSaidNo >= AlcoholLimiterDecorator.MAX_RETRY_AFTER_BARTENDER_SAID_NO;
    }

    private boolean drinkToMuchAlcohol(int alcoholsCount) {
        return alcoholsCount >= MAX_ALCOHOL_ORDERS;
    }
}
