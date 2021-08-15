package fr.norsys.dojo.bartender.model;

import java.time.LocalDate;

public class PlayerInformation {

    private LocalDate birthDate;

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
