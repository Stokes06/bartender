package fr.norsys.dojo.bartender.process;

import fr.norsys.dojo.bartender.BartenderBehavior;
import fr.norsys.dojo.bartender.CommandProcess;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class BeerProcess implements CommandProcess {

    private final AskAge askForAge;

    public BeerProcess(BartenderBehavior bartenderBehavior) {
        askForAge = new AskAge(bartenderBehavior.getCommunicationInterface(), bartenderBehavior.getWishBehavior());
    }

    @Override
    public void process() {
        try {
            final boolean isLegal = askForAge.askAge(18, LocalDate.now());
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
