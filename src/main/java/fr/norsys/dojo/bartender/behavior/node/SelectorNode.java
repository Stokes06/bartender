package fr.norsys.dojo.bartender.behavior.node;

import fr.norsys.dojo.bartender.CommunicationInterface;
import fr.norsys.dojo.bartender.behavior.decorator.BehaviorTreeDecorator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SelectorNode extends BehaviorNode {

    List<BehaviorNode> children = new ArrayList<>();

    private int maxAttempts = 1;

    public SelectorNode(CommunicationInterface communicationInterface) {
        this(communicationInterface, "root");
    }

    public SelectorNode(CommunicationInterface communicationInterface, String routingKey) {
        this(communicationInterface, routingKey, new ArrayList<>());
    }

    public SelectorNode(CommunicationInterface communicationInterface, String routingKey, int maxAttempts) {
        this(communicationInterface, routingKey, new ArrayList<>());
        this.maxAttempts = maxAttempts;
    }

    public SelectorNode(CommunicationInterface communicationInterface, String routingKey, List<BehaviorTreeDecorator> decorators) {
        super(communicationInterface, routingKey, decorators);
    }

    public SelectorNode addNode(BehaviorNode node) {
        this.children.add(node);
        return this;
    }

    @Override
    void onGranted() {
        String choices = getChoices();

        Optional<BehaviorNode> maybeNode = Optional.empty();

        int attempts = 0;
        while (attempts++ < maxAttempts && maybeNode.isEmpty()) {
            maybeNode = getNodeFromCommand(choices);
        }

        maybeNode.ifPresent(BehaviorNode::run);
    }

    private Optional<BehaviorNode> getNodeFromCommand(String choices) {
        System.out.println("you can choose between " + choices);
        final String command = communicationInterface.listenCommand();

        return children.stream()
                .filter(node -> node.routingKey.equalsIgnoreCase(command))
                .findAny();
    }

    private String getChoices() {
        return children.stream()
                .map(BehaviorNode::getRoutingKey)
                .collect(Collectors.joining(" or "));
    }
}