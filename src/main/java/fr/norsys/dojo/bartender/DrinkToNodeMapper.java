package fr.norsys.dojo.bartender;

import fr.norsys.dojo.bartender.behavior.decorator.AgeRestrictedDecorator;
import fr.norsys.dojo.bartender.behavior.decorator.BehaviorTreeDecorator;
import fr.norsys.dojo.bartender.behavior.node.LeafNode;
import fr.norsys.dojo.bartender.menu.Drink;
import fr.norsys.dojo.bartender.menu.DrinkType;
import fr.norsys.dojo.bartender.process.birthday.BirthdateBehavior;

import java.util.ArrayList;
import java.util.List;

public class DrinkToNodeMapper {

    private final BirthdateBehavior birthdateBehavior;
    private final CommunicationInterface communicationInterface;

    public DrinkToNodeMapper(BirthdateBehavior birthdateBehavior, CommunicationInterface communicationInterface) {

        this.birthdateBehavior = birthdateBehavior;
        this.communicationInterface = communicationInterface;
    }

    public LeafNode map(Drink drink) {
        final LeafNode.Builder builder = LeafNode.builder()
                .communicationInterface(communicationInterface)
                .commandProcess(() -> System.out.println(getMessage(drink)))
                .routingKey(drink.getLabel());

        if (drink.containsAlcohol()) {
            builder.addDecorator(new AgeRestrictedDecorator(communicationInterface, birthdateBehavior, drink.getRequiredAge(), drink.getLabel()));
        }

        return builder.build();
    }

    private String getMessage(Drink drink) {
        String message = "Bartender gives you a " + drink.getLabel() ;
        if (drink.containsAlcohol()) {
            message += ", drink with moderation!";
        }
        return message;
    }
}
