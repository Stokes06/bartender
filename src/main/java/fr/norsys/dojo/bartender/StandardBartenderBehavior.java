package fr.norsys.dojo.bartender;

import fr.norsys.dojo.bartender.behavior.node.LeafNode;
import fr.norsys.dojo.bartender.behavior.node.SelectorNode;
import fr.norsys.dojo.bartender.menu.DrinkType;
import fr.norsys.dojo.bartender.process.birthday.WishHappyBirthday;

public class StandardBartenderBehavior extends BartenderBehavior {

    StandardBartenderBehavior(Bartender bartender,
                              CommunicationInterface commandInterface) {
        super(bartender, commandInterface);
    }

    @Override
    protected void beginPlay() {
        birthdateBehavior = new WishHappyBirthday();

        initMenuCommands();

    }

    private void initMenuCommands() {

        behaviorTree = new SelectorNode(communicationInterface);

        DrinkToNodeMapper drinkToNodeMapper = new DrinkToNodeMapper(birthdateBehavior, communicationInterface);

        final Bar bar = this.bartender.getBar();

        bar.getDrinks().stream()
                .filter(drink -> drink.getDrinkType() != DrinkType.JUICE)
                .map(drinkToNodeMapper::map)
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
                .map(drinkToNodeMapper::map)
                .forEach(juiceSelectorNode::addNode);
        return juiceSelectorNode;
    }

    @Override
    public void bye() {
        System.out.println("Goodbye buddy");
    }


}
