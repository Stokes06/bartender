package fr.norsys.dojo.bartender.game;

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
        System.out.println("remaining amount " + playerCash);
        return true;
    }

    public double getPlayerCash() {
        return playerCash;
    }
}
