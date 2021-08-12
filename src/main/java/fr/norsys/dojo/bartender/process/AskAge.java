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

    public LocalDate getBirthDateAndTryToWishBirthday(LocalDate today) {
        LocalDate birth = askBirthDate();

        birthdateBehavior.tryToWish(birth, today);

        return birth;
    }

    private LocalDate askBirthDate() {
        System.out.println("I would need to see an ID please, what is your birthdate ? (yyyy-mm-dd)");
        final String birthDate = commandInterface.listenCommand();

        return LocalDate.parse(birthDate, ISO_DATE);
    }
}
