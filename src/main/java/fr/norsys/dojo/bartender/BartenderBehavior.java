package fr.norsys.dojo.bartender;

import fr.norsys.dojo.bartender.behavior.node.SelectorNode;
import fr.norsys.dojo.bartender.menu.OrderChoice;
import fr.norsys.dojo.bartender.process.CommandProcess;
import fr.norsys.dojo.bartender.process.birthday.BirthdateBehavior;

import java.util.Map;

public abstract class BartenderBehavior {

    protected final Bartender bartender;
    protected SelectorNode behaviorTree;

    protected final CommunicationInterface communicationInterface;

    protected Map<OrderChoice, CommandProcess> commandProcessMap;

    protected BirthdateBehavior birthdateBehavior = (a, b) -> {
    };

    BartenderBehavior(Bartender bartender, CommunicationInterface communicationInterface) {
        this.bartender = bartender;
        this.communicationInterface = communicationInterface;
        beginPlay();
    }

    protected abstract void beginPlay();

    public void listenCommand() {
//        String input = communicationInterface.listenCommand();
//        final OrderChoice choice = OrderChoice.getFromString(input);
//        commandProcessMap.get(choice).onGranted();
        this.behaviorTree.run();
    }

    public abstract void bye();

    public Bartender getBartender() {
        return bartender;
    }

    public CommunicationInterface getCommunicationInterface() {
        return communicationInterface;
    }

    public BirthdateBehavior getWishBehavior() {
        return birthdateBehavior;
    }
}
