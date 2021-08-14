package fr.norsys.dojo.bartender.behavior.node;

import fr.norsys.dojo.bartender.CommunicationInterface;
import fr.norsys.dojo.bartender.behavior.decorator.BehaviorTreeDecorator;
import fr.norsys.dojo.bartender.process.CommandProcess;

import java.util.ArrayList;
import java.util.List;

public class LeafNode extends BehaviorNode {

    private final CommandProcess commandProcess;

    public LeafNode(CommunicationInterface communicationInterface,
                    String routingKey,
                    CommandProcess commandProcess) {
        this(communicationInterface, routingKey, commandProcess, new ArrayList<>());
    }

    public LeafNode(CommunicationInterface communicationInterface,
                    String routingKey,
                    CommandProcess commandProcess,
                    List<BehaviorTreeDecorator> decorators) {
        super(communicationInterface, routingKey, decorators);
        this.commandProcess = commandProcess;
    }


    @Override
    void onGranted() {
        this.commandProcess.process();
    }

    public static LeafNode.Builder builder(){
        return new Builder();
    }

    public static class Builder {
        private String routingKey;
        private final List<BehaviorTreeDecorator> decorators = new ArrayList<>();
        private CommandProcess commandProcess;
        private CommunicationInterface communicationInterface;

        public Builder addDecorator(BehaviorTreeDecorator decorator) {
            this.decorators.add(decorator);
            return this;
        }

        public Builder routingKey(String routingKey) {
            this.routingKey = routingKey;
            return this;
        }

        public Builder commandProcess(CommandProcess commandProcess){
            this.commandProcess = commandProcess;
            return this;
        }

        public Builder communicationInterface(CommunicationInterface communicationInterface) {
            this.communicationInterface = communicationInterface;
            return this;
        }

        public LeafNode build() {
            return new LeafNode(communicationInterface, routingKey, commandProcess, decorators);
        }
    }
}