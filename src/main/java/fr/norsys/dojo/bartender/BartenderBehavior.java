package fr.norsys.dojo.bartender;

import fr.norsys.dojo.bartender.behavior.node.LeafNode;
import fr.norsys.dojo.bartender.behavior.node.SelectorNode;
import fr.norsys.dojo.bartender.menu.DrinkType;
import fr.norsys.dojo.bartender.process.birthday.BirthdateBehavior;

public abstract class BartenderBehavior {

    protected final Bartender bartender;
    protected SelectorNode behaviorTree;

    protected final CommunicationInterface communicationInterface;

    protected BirthdateBehavior birthdateBehavior = (a, b) -> {};

    BartenderBehavior(Bartender bartender, CommunicationInterface communicationInterface) {
        this.bartender = bartender;
        this.communicationInterface = communicationInterface;
    }

    protected abstract void beginPlay();

    public void listenCommand() {
        this.behaviorTree.run();
    }

    protected void initBehaviorTree() {

        behaviorTree = new SelectorNode(communicationInterface);

        DrinkToNodeMapper drinkToNodeMapper = new DrinkToNodeMapper(birthdateBehavior, communicationInterface);

        final Bar bar = this.bartender.getBar();

        bar.getDrinks().stream()
                .filter(drink -> drink.getDrinkType() != DrinkType.JUICE)
                .map(drinkToNodeMapper::mapToLeafNode)
                .forEach(behaviorTree::addNode);


        final SelectorNode juicesNode = createJuiceSelectorWithLeafNodes(drinkToNodeMapper, bar);

        behaviorTree
                .addNode(
                        juicesNode
                )
                .addNode(new LeafNode(communicationInterface, "nothing", this.bartender::stopService));

    }


    private SelectorNode createJuiceSelectorWithLeafNodes(DrinkToNodeMapper drinkToNodeMapper, Bar bar) {
        final SelectorNode juiceSelectorNode = new SelectorNode(communicationInterface, "juice", 3);

        bar.getDrinks().stream()
                .filter(drink -> drink.getDrinkType() == DrinkType.JUICE)
                .map(drinkToNodeMapper::mapToLeafNode)
                .forEach(juiceSelectorNode::addNode);
        return juiceSelectorNode;
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
