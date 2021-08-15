package fr.norsys.dojo.bartender.behavior.service;

import java.time.LocalDate;

public interface BirthdateBehavior {

    void tryToWish(LocalDate birthDate, LocalDate todayDate);
}
