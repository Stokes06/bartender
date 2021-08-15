package fr.norsys.dojo.bartender.behavior.service;

import fr.norsys.dojo.bartender.game.GameState;

import java.time.LocalDate;

public class WishHappyBirthday implements BirthdateBehavior {

    private final GameState gameState;

    public WishHappyBirthday(GameState gameState) {

        this.gameState = gameState;
    }

    @Override
    public void tryToWish(LocalDate birthDate, LocalDate todayDate) {
        if (isBirthdayToday(birthDate, todayDate)) {
            System.out.println("Happy birthday ! Your next drink is free if non alcoholic !");
            gameState.setOfferNextNonAlcoholicDrink(true);
        }
    }

    private boolean isBirthdayToday(LocalDate birthDate, LocalDate todayDate) {
        return birthDate.getMonthValue() == todayDate.getMonthValue() && birthDate.getDayOfMonth() == todayDate.getDayOfMonth();
    }
}
