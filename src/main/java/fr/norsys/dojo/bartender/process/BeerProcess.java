package fr.norsys.dojo.bartender.process;

import fr.norsys.dojo.bartender.CommunicationInterface;
import fr.norsys.dojo.bartender.process.birthday.BirthdateBehavior;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class BeerProcess implements CommandProcess {

    private final AskAge askForAge;

    public BeerProcess(CommunicationInterface communicationInterface, BirthdateBehavior birthdateBehavior) {
        askForAge = new AskAge(communicationInterface, birthdateBehavior);
    }

    @Override
    public void process() {
        try {
            final boolean isLegal = askForAge.isOlderThanAtDate(18, LocalDate.now());
            if (isLegal) {
                System.out.println("Bartender gives you a beer, drink in moderation !");
            } else {
                System.out.println("You can't have a beer kiddo");
            }
        }catch (DateTimeParseException ex) {
            System.out.println("Can't read your ID son");
        }

    }

}
