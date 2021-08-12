package fr.norsys.dojo.bartender.process;

import fr.norsys.dojo.bartender.CommunicationInterface;
import fr.norsys.dojo.bartender.process.birthday.BirthdateBehavior;

import static fr.norsys.dojo.bartender.menu.OrderChoice.VIRGIN_BLOODY_MARY;

public class VirginBloodyMarryProcess extends AgeRestrictedProcess {
    public VirginBloodyMarryProcess(CommunicationInterface communicationInterface, BirthdateBehavior birthdateBehavior) {
        super(communicationInterface, birthdateBehavior);
        this.requiredAge = 14;
        this.product = VIRGIN_BLOODY_MARY;
    }
}
