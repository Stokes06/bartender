package fr.norsys.dojo.bartender.behavior.node;

import fr.norsys.dojo.bartender.CommunicationInterface;
import fr.norsys.dojo.bartender.behavior.decorator.BehaviorTreeDecorator;

import java.util.ArrayList;
import java.util.List;

public abstract class BehaviorNode {

    protected final CommunicationInterface communicationInterface;

    protected final String routingKey;

    protected final List<BehaviorTreeDecorator> decorators;

    BehaviorNode(CommunicationInterface communicationInterface, String routingKey) {

        this(communicationInterface, routingKey, new ArrayList<>());
    }

    BehaviorNode(CommunicationInterface communicationInterface, String routingKey, List<BehaviorTreeDecorator> decorators) {

        this.communicationInterface = communicationInterface;
        this.routingKey = routingKey;
        this.decorators = decorators;
    }


    public void run() {
        final boolean granted = decorators.stream().allMatch(BehaviorTreeDecorator::test);
        if (granted) {
            onGranted();
        }
    }

    abstract void onGranted();

    public String getRoutingKey() {
        return routingKey;
    }
}
