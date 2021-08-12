package fr.norsys.dojo.bartender.process;

import fr.norsys.dojo.bartender.CommunicationInterface;
import fr.norsys.dojo.bartender.process.birthday.BirthdateBehavior;

import static fr.norsys.dojo.bartender.menu.OrderChoice.RED_WINE;

public class RedWineProcess extends AgeRestrictedProcess {
    public RedWineProcess(CommunicationInterface communicationInterface, BirthdateBehavior birthdateBehavior) {
        super(communicationInterface, birthdateBehavior);
        this.product = RED_WINE;
    }
}
