package fr.norsys.dojo.bartender;

import java.util.Map;

abstract class BartenderBehavior {

    protected final Bartender bartender;

    protected final CommunicationInterface communicationInterface;

    protected Map<String, CommandProcess> commandProcessMap;

    BartenderBehavior(Bartender bartender, CommunicationInterface communicationInterface) {
        this.bartender = bartender;
        this.communicationInterface = communicationInterface;
        this.initProcessesMap();
    }

    protected abstract void initProcessesMap();

    public void behave() {
        String input = communicationInterface.listenCommand().toLowerCase();
        if (commandProcessMap.containsKey(input)) {
            commandProcessMap.get(input).process();
        }
    }

    public abstract void options();
    public abstract void bye();
}
