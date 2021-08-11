package fr.norsys.dojo.bartender.process.birthday;

import java.time.LocalDate;

public interface WishBehavior {

    void tryToWish(LocalDate birthDate, LocalDate todayDate);
}
