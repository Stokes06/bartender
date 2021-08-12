package fr.norsys.dojo.bartender;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TestHelper {
    public static String generateBirthDate(boolean isLegal) {
        LocalDate now = LocalDate.now();
        LocalDate birthDate = isLegal ?
                now.minusYears(19) :
                now.minusYears(14);
        return birthDate.format(DateTimeFormatter.ISO_DATE);
    }
}
