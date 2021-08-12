package fr.norsys.dojo.bartender.process;

import fr.norsys.dojo.bartender.CommunicationInterface;
import fr.norsys.dojo.bartender.menu.OrderChoice;
import fr.norsys.dojo.bartender.process.birthday.BirthdateBehavior;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static fr.norsys.dojo.bartender.menu.OrderChoice.BAD_CHOICE;
import static java.lang.String.format;

public abstract class AgeRestrictedProcess implements CommandProcess {

    private final AskAge askForAge;

    protected int requiredAge = 18;

    protected OrderChoice product = BAD_CHOICE;

    protected AgeRestrictedProcess(CommunicationInterface communicationInterface,
                                   BirthdateBehavior birthdateBehavior) {
        askForAge = new AskAge(communicationInterface, birthdateBehavior);
    }

    @Override
    public void process() {
        try {
            final boolean isAuthorized = askForAge.isOlderThanAtDate(requiredAge, LocalDate.now());
            if (isAuthorized) {
                System.out.println(getSuccessMessage());
            } else {
                System.out.println(getForbiddenMessage());
            }
        } catch (DateTimeParseException ex) {
            System.out.println("Can't read your ID son");
        }
    }

    protected String getSuccessMessage() {
        return format("Bartender gives you a %s, drink in moderation !", product);
    }

    protected String getForbiddenMessage() {
        return format("You can't have a %s kiddo", product);
    }

}
