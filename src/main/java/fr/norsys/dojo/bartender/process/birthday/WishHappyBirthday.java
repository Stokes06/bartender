package fr.norsys.dojo.bartender.process.birthday;

import java.time.LocalDate;

public class WishHappyBirthday implements BirthdateBehavior {
    @Override
    public void tryToWish(LocalDate birthDate, LocalDate todayDate) {
        if (isBirthdayToday(birthDate, todayDate)) {
            System.out.println("Happy birthday !");
        }
    }

    private boolean isBirthdayToday(LocalDate birthDate, LocalDate todayDate) {
        return birthDate.getMonthValue() == todayDate.getMonthValue() && birthDate.getDayOfMonth() == todayDate.getDayOfMonth();
    }
}
