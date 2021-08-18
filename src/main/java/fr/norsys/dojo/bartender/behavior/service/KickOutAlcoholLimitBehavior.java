package fr.norsys.dojo.bartender.behavior.service;

import fr.norsys.dojo.bartender.behavior.CommandProcess;
import fr.norsys.dojo.bartender.game.GameState;

public class KickOutAlcoholLimitBehavior implements AlcoholLimitBehavior {

    private static final int MAX_RETRY_AFTER_BARTENDER_SAID_NO = 2;

    private final GameState gameState;
    private final CommandProcess onTooMuchAlcoholOrder;

    public KickOutAlcoholLimitBehavior(GameState gameState, CommandProcess onTooMuchAlcoholOrder) {

        this.gameState = gameState;
        this.onTooMuchAlcoholOrder = onTooMuchAlcoholOrder;
    }

    @Override
    public boolean canOrderAlcohol() {
        final boolean tooMuch = askTooMuchAfterBartenderSaidNo(gameState.getRetryAfterBartenderSaidNo());
        if (tooMuch) {
            onTooMuchAlcoholOrder.process();
        } else {
            System.out.println("Stop asking for alcohol, you are already drunk");
            gameState.incrementRetryAfterBartender();
        }
        return false;
    }

    private boolean askTooMuchAfterBartenderSaidNo(int retryAfterBartenderSaidNo) {
        return retryAfterBartenderSaidNo >= MAX_RETRY_AFTER_BARTENDER_SAID_NO;
    }

}
