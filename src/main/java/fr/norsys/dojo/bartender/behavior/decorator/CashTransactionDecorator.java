package fr.norsys.dojo.bartender.behavior.decorator;

import fr.norsys.dojo.bartender.game.GameState;
import fr.norsys.dojo.bartender.game.PlayerBank;
import fr.norsys.dojo.bartender.menu.Drink;

public class CashTransactionDecorator implements BehaviorTreeDecorator {

    private final PlayerBank playerBank;
    private final Drink drink;
    private final GameState gameState;

    public CashTransactionDecorator(GameState gameState, Drink drink) {

        this.gameState = gameState;
        this.playerBank = gameState.getPlayerBank();
        this.drink = drink;
    }

    @Override
    public boolean test() {
        final boolean paymentSucceed = playerBank.pay(drink.getPrice());
        if (!paymentSucceed) {
            System.out.println("You are short on cash");
        }
        return paymentSucceed;
    }
}
