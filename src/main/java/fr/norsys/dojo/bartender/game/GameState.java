package fr.norsys.dojo.bartender.game;

import fr.norsys.dojo.bartender.model.PlayerBank;
import fr.norsys.dojo.bartender.model.PlayerInformation;

public class GameState {

    private final PlayerBank playerBank;
    private final PlayerInformation playerInformation;
    private boolean isPlaying;
    private int orderedAlcoholsCount = 0;
    private int retryAfterBartenderSaidNo = 0;

    private boolean offerNextNonAlcoholicDrink = false;
    private boolean nextNonAlcoholicDrinkIsFree = false;

    public GameState(PlayerBank playerBank, PlayerInformation playerInformation) {
        this.playerBank = playerBank;
        this.playerInformation = playerInformation;
    }

    public static GameState makeGameState(PlayerBank playerBank, PlayerInformation playerInformation) {
        final GameState gameState = new GameState(playerBank, playerInformation);
        gameState.beginPlay();
        return gameState;
    }

    void beginPlay() {
        this.isPlaying = true;
    }

    public void stopGame() {
        this.isPlaying = false;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public PlayerBank getPlayerBank() {
        return playerBank;
    }

    public PlayerInformation getPlayerInformation() {
        return playerInformation;
    }

    public void incrementAlcoholCounter() {
        ++orderedAlcoholsCount;
    }

    public int getOrderedAlcoholsCount() {
        return orderedAlcoholsCount;
    }

    public int getRetryAfterBartenderSaidNo() {
        return retryAfterBartenderSaidNo;
    }

    public void incrementRetryAfterBartender() {
        ++retryAfterBartenderSaidNo;
    }

    public boolean isOfferNextNonAlcoholicDrink() {
        return offerNextNonAlcoholicDrink;
    }

    public void setOfferNextNonAlcoholicDrink(boolean offerNextNonAlcoholicDrink) {
        this.offerNextNonAlcoholicDrink = offerNextNonAlcoholicDrink;
    }

    public boolean isNextNonAlcoholicDrinkIsFree() {
        return nextNonAlcoholicDrinkIsFree;
    }

    public void setNextNonAlcoholicDrinkIsFree(boolean nextNonAlcoholicDrinkIsFree) {
        this.nextNonAlcoholicDrinkIsFree = nextNonAlcoholicDrinkIsFree;
    }
}
