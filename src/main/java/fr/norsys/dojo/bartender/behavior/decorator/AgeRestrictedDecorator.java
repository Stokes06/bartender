package fr.norsys.dojo.bartender.behavior.decorator;

import fr.norsys.dojo.bartender.CommunicationInterface;
import fr.norsys.dojo.bartender.process.birthday.BirthdateBehavior;

import java.time.LocalDate;

import static java.time.format.DateTimeFormatter.ISO_DATE;

public class AgeRestrictedDecorator implements BehaviorTreeDecorator {
    private final CommunicationInterface communicationInterface;
    private final BirthdateBehavior birthdateBehavior;
    private final int requiredAge;
    private final String drink;

    public AgeRestrictedDecorator(CommunicationInterface communicationInterface,
                                  BirthdateBehavior birthdateBehavior,
                                  int requiredAge, String drink) {
        this.communicationInterface = communicationInterface;
        this.birthdateBehavior = birthdateBehavior;
        this.requiredAge = requiredAge;
        this.drink = drink;
    }

    public boolean test() {
        try {
            final LocalDate birthDate = askBirthDate();

            final LocalDate today = LocalDate.now();
            birthdateBehavior.tryToWish(birthDate, today);

            final boolean test = !birthDate.plusYears(requiredAge).isAfter(today);
            if (!test) {
                System.out.printf("You can't have a %s kiddo %n", drink);
            }
            return test;
        } catch (Exception ex) {
            System.out.println("Can't read your ID son");
        }
        return false;
    }

    private LocalDate askBirthDate() {
        System.out.println("I would need to see an ID please, what is your birthdate ? (yyyy-mm-dd)");
        final String birthDate = communicationInterface.listenCommand();
        return LocalDate.parse(birthDate, ISO_DATE);
    }
}
