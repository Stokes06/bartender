package fr.norsys.dojo.bartender.process;

import fr.norsys.dojo.bartender.CommunicationInterface;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class AskAge {

    CommunicationInterface commandInterface;

    public AskAge(CommunicationInterface commandInterface) {
        this.commandInterface = commandInterface;
    }

    public boolean askAge(int askAge){
        System.out.println("I would need to see an ID please, what is your birthdate ? (yyyy-mm-dd)");
        final String birthDate = commandInterface.listenCommand();

        LocalDate birth = LocalDate.parse(birthDate, DateTimeFormatter.ISO_DATE);
        return birth.plusYears(askAge).isBefore(LocalDate.now());
    }
}
