package fr.norsys.dojo.bartender.model;

import fr.norsys.dojo.bartender.payment.Money;

public class PlayerBank {

    private Money playerCash;

    public PlayerBank(Money playerCash) {
        this.playerCash = playerCash;
    }

    /**
     * @param amountToPay amount to pay
     * @return true only if payment succeeded
     */
    public boolean pay(Money amountToPay) {
        if (playerCash.compareTo(amountToPay) < 0) {
            return false;
        }
        playerCash = playerCash.subtract(amountToPay);
        System.out.println("Payment received : remaining amount " + playerCash);
        return true;
    }

    public Money getPlayerCash() {
        return playerCash;
    }
}
