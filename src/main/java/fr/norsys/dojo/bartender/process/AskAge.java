package fr.norsys.dojo.bartender.process;

import fr.norsys.dojo.bartender.CommunicationInterface;
import fr.norsys.dojo.bartender.process.birthday.BirthdateBehavior;

import java.time.LocalDate;

import static java.time.format.DateTimeFormatter.ISO_DATE;

class AskAge {

    private final CommunicationInterface commandInterface;

    private final BirthdateBehavior birthdateBehavior;

    public AskAge(CommunicationInterface commandInterface, BirthdateBehavior birthdateBehavior) {
        this.commandInterface = commandInterface;
        this.birthdateBehavior = birthdateBehavior;
    }

    public boolean isOlderThanAtDate(int askAge, LocalDate today) {
        System.out.println("I would need to see an ID please, what is your birthdate ? (yyyy-mm-dd)");
        final String birthDate = commandInterface.listenCommand();

        LocalDate birth = LocalDate.parse(birthDate, ISO_DATE);

        birthdateBehavior.tryToWish(birth, today);

        return birth.plusYears(askAge).isBefore(today);
    }
}
