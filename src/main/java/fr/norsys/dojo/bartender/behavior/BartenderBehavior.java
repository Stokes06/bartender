package fr.norsys.dojo.bartender.behavior;

import fr.norsys.dojo.bartender.model.Bar;
import fr.norsys.dojo.bartender.model.Bartender;
import fr.norsys.dojo.bartender.CommunicationInterface;
import fr.norsys.dojo.bartender.behavior.node.LeafNode;
import fr.norsys.dojo.bartender.behavior.node.SelectorNode;
import fr.norsys.dojo.bartender.game.GameState;
import fr.norsys.dojo.bartender.model.menu.DrinkType;
import fr.norsys.dojo.bartender.behavior.service.BirthdateBehavior;

public abstract class BartenderBehavior {

    protected final Bartender bartender;
    protected SelectorNode behaviorTree;

    protected final CommunicationInterface communicationInterface;
    private final GameState gameState;

    protected BirthdateBehavior birthdateBehavior = (a, b) -> {};

    BartenderBehavior(Bartender bartender, CommunicationInterface communicationInterface, GameState gameState) {
        this.bartender = bartender;
        this.communicationInterface = communicationInterface;
        this.gameState = gameState;
    }

    public abstract void beginPlay();

    public void listenCommand() {
        this.behaviorTree.run();
    }

    protected void initBehaviorTree() {

        behaviorTree = new SelectorNode(communicationInterface);

        DrinkToNodeMapper drinkToNodeMapper = new DrinkToNodeMapper(birthdateBehavior,
                gameState,
                bartender::stopService,
                communicationInterface);

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
