package fr.norsys.dojo.bartender.process.birthday;

import java.time.LocalDate;

public interface BirthdateBehavior {

    void tryToWish(LocalDate birthDate, LocalDate todayDate);
}
