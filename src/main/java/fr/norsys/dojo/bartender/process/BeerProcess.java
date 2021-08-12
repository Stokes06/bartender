package fr.norsys.dojo.bartender.process;

import fr.norsys.dojo.bartender.CommunicationInterface;
import fr.norsys.dojo.bartender.process.birthday.BirthdateBehavior;

import static fr.norsys.dojo.bartender.menu.OrderChoice.BEER;

public class BeerProcess extends AgeRestrictedProcess {

    public BeerProcess(CommunicationInterface communicationInterface, BirthdateBehavior birthdateBehavior) {
        super(communicationInterface, birthdateBehavior);
        this.product = BEER;
    }
}
