package fr.norsys.dojo.bartender.behavior.decorator;

import fr.norsys.dojo.bartender.payment.VatApplier;
import fr.norsys.dojo.bartender.game.GameState;
import fr.norsys.dojo.bartender.model.PlayerBank;
import fr.norsys.dojo.bartender.model.menu.Drink;

public class CashTransactionDecorator implements BehaviorTreeDecorator {

    private final PlayerBank playerBank;
    private final Drink drink;
    private final GameState gameState;
    private final VatApplier vatApplier;

    public CashTransactionDecorator(GameState gameState, Drink drink) {

        this.gameState = gameState;
        this.playerBank = gameState.getPlayerBank();
        this.drink = drink;
        vatApplier = new VatApplier();
    }

    @Override
    public boolean test() {
        final double vatPrice = vatApplier.getVatPrice(drink);
        final boolean paymentSucceed = playerBank.pay(vatPrice);
        if (!paymentSucceed) {
            System.out.println("You are short on cash");
        }
        return paymentSucceed;
    }
}
