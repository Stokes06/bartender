package fr.norsys.dojo.bartender.process;

import fr.norsys.dojo.bartender.CommunicationInterface;
import fr.norsys.dojo.bartender.process.birthday.WishBehavior;
import fr.norsys.dojo.bartender.process.birthday.WishHappyBirthday;

import java.time.LocalDate;

import static java.time.LocalDate.now;
import static java.time.format.DateTimeFormatter.ISO_DATE;

class AskAge {

    private final CommunicationInterface commandInterface;

    private WishBehavior wishBehavior;

    public AskAge(CommunicationInterface commandInterface, boolean wishHappyBirthday) {
        this.commandInterface = commandInterface;
        initWishBehavior(wishHappyBirthday);
    }

    private void initWishBehavior(boolean wishHappyBirthday) {
        if (wishHappyBirthday) {
            wishBehavior = new WishHappyBirthday();
        } else {
            wishBehavior = (a, b) -> {};
        }
    }

    public boolean askAge(int askAge, LocalDate today){
        System.out.println("I would need to see an ID please, what is your birthdate ? (yyyy-mm-dd)");
        final String birthDate = commandInterface.listenCommand();

        LocalDate birth = LocalDate.parse(birthDate, ISO_DATE);

        wishBehavior.tryToWish(birth, today);

        return birth.plusYears(askAge).isBefore(today);
    }
}
