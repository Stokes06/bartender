package fr.norsys.dojo.bartender.behavior.decorator;

import fr.norsys.dojo.bartender.CommunicationInterface;
import fr.norsys.dojo.bartender.behavior.service.BirthdateBehavior;
import fr.norsys.dojo.bartender.model.PlayerInformation;
import fr.norsys.dojo.bartender.model.menu.Drink;

import java.time.LocalDate;
import java.util.Optional;

import static java.time.format.DateTimeFormatter.ISO_DATE;

public class AgeRestrictedDecorator implements BehaviorTreeDecorator {
    private final CommunicationInterface communicationInterface;
    private final BirthdateBehavior birthdateBehavior;
    private final PlayerInformation playerInformation;
    private final Drink drink;

    public AgeRestrictedDecorator(CommunicationInterface communicationInterface,
                                  BirthdateBehavior birthdateBehavior,
                                  PlayerInformation playerInformation,
                                  Drink drink) {
        this.communicationInterface = communicationInterface;
        this.birthdateBehavior = birthdateBehavior;
        this.playerInformation = playerInformation;
        this.drink = drink;
    }

    public boolean test() {
        try {
            final LocalDate birthDate = Optional.ofNullable(playerInformation.getBirthDate())
                    .orElseGet(this::getBirthDateAndTryWishBirthDay);

            final boolean test = !birthDate.plusYears(drink.getRequiredAge()).isAfter(LocalDate.now());
            if (!test) {
                System.out.printf("You can't have a %s kiddo %n", drink.getLabel());
            }
            return test;
        } catch (Exception ex) {
            System.out.println("Can't read your ID son");
        }
        return false;
    }

    private LocalDate getBirthDateAndTryWishBirthDay() {
        final LocalDate birthDate = getBirthDate();
        playerInformation.setBirthDate(birthDate);

        final LocalDate today = LocalDate.now();
        birthdateBehavior.tryToWish(birthDate, today);

        return birthDate;
    }

    private LocalDate getBirthDate() {
        System.out.println("I would need to see an ID please, what is your birthdate ? (yyyy-mm-dd)");
        final String birthDate = communicationInterface.listenCommand();
        return LocalDate.parse(birthDate, ISO_DATE);
    }
}
