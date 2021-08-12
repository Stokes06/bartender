package fr.norsys.dojo.bartender.process;

import fr.norsys.dojo.bartender.CommunicationInterface;
import fr.norsys.dojo.bartender.menu.JuiceChoice;

import static fr.norsys.dojo.bartender.menu.JuiceChoice.BAD_CHOICE;

public class JuiceProcess implements CommandProcess {
    private final CommunicationInterface communicationInterface;

    private static final short MAX_ATTEMPT = 3;

    public JuiceProcess(CommunicationInterface communicationInterface) {
        this.communicationInterface = communicationInterface;
    }

    @Override
    public void process() {
        JuiceChoice juiceChoice = BAD_CHOICE;
        short remainingAttempt = MAX_ATTEMPT;
        while (juiceChoice == BAD_CHOICE && remainingAttempt-- > 0) {
            juiceChoice = askJuice();
        }

        if (juiceChoice != BAD_CHOICE) {
            System.out.printf("Bartender gives you a %s juice%n", juiceChoice.getJuiceName());
        }

    }

    private JuiceChoice askJuice() {
        System.out.println("4 types of juices : Papaya, Pomegranate, Banana, Cucumber");
        final String juice = communicationInterface.listenCommand();
        return JuiceChoice.getFromString(juice);
    }
}
