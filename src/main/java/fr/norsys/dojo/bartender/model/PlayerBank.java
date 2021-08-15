package fr.norsys.dojo.bartender.model;

import fr.norsys.dojo.bartender.payment.CashAmountRounder;

public class PlayerBank {

    private double playerCash;

    public PlayerBank(double playerCash) {
        this.playerCash = playerCash;
    }

    /**
     *
     * @param amountToPay
     * @return true only if payment succeeded
     */
    public boolean pay(double amountToPay) {
        if (amountToPay > playerCash) {
            return false;
        }
        playerCash -= amountToPay;
        playerCash = CashAmountRounder.roundToTwoDecimal(playerCash);
        System.out.println("remaining amount " + playerCash);
        return true;
    }

    public double getPlayerCash() {
        return playerCash;
    }
}
