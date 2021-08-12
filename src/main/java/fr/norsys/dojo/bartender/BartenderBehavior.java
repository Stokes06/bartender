package fr.norsys.dojo.bartender;

import fr.norsys.dojo.bartender.process.birthday.WishBehavior;

import java.util.Map;

import static fr.norsys.dojo.bartender.OrderChoice.BAD_CHOICE;

public abstract class BartenderBehavior {

    protected final Bartender bartender;

    protected final CommunicationInterface communicationInterface;

    protected Map<OrderChoice, CommandProcess> commandProcessMap;

    protected WishBehavior wishBehavior = (a, b) -> {};

    BartenderBehavior(Bartender bartender, CommunicationInterface communicationInterface) {
        this.bartender = bartender;
        this.communicationInterface = communicationInterface;
        onPlay();
    }

    protected abstract void onPlay();

    public void react() {
        String input = communicationInterface.listenCommand();
        final OrderChoice choice = OrderChoice.getFromString(input);
        if (choice != BAD_CHOICE) {
            commandProcessMap.get(choice).process();
        }
    }

    public abstract void suggestOptions();
    public abstract void bye();

    public Bartender getBartender() {
        return bartender;
    }

    public CommunicationInterface getCommunicationInterface() {
        return communicationInterface;
    }

    public WishBehavior getWishBehavior() {
        return wishBehavior;
    }
}
