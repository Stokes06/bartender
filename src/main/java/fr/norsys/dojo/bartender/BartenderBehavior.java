package fr.norsys.dojo.bartender;

import java.util.Map;

import static fr.norsys.dojo.bartender.OrderChoice.BAD_CHOICE;

abstract class BartenderBehavior {

    protected final Bartender bartender;

    protected final CommunicationInterface communicationInterface;

    protected Map<OrderChoice, CommandProcess> commandProcessMap;

    BartenderBehavior(Bartender bartender, CommunicationInterface communicationInterface) {
        this.bartender = bartender;
        this.communicationInterface = communicationInterface;
        this.initProcessesMap();
    }

    protected abstract void initProcessesMap();

    public void behave() {
        String input = communicationInterface.listenCommand();
        final OrderChoice choice = OrderChoice.getFromString(input);
        if (choice != BAD_CHOICE) {
            commandProcessMap.get(choice).process();
        }
    }

    public abstract void options();
    public abstract void bye();
}
