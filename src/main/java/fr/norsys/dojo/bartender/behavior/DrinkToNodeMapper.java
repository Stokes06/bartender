package fr.norsys.dojo.bartender.behavior;

import fr.norsys.dojo.bartender.CommunicationInterface;
import fr.norsys.dojo.bartender.behavior.decorator.AgeRestrictedDecorator;
import fr.norsys.dojo.bartender.behavior.decorator.AlcoholLimiterDecorator;
import fr.norsys.dojo.bartender.behavior.decorator.CashTransactionDecorator;
import fr.norsys.dojo.bartender.behavior.node.LeafNode;
import fr.norsys.dojo.bartender.behavior.service.BirthdateBehavior;
import fr.norsys.dojo.bartender.game.GameState;
import fr.norsys.dojo.bartender.model.menu.Drink;

public class DrinkToNodeMapper {

    private final BirthdateBehavior birthdateBehavior;
    private final GameState gameState;
    private final BartenderBehavior bartenderBehavior;
    private final CommunicationInterface communicationInterface;

    public DrinkToNodeMapper(GameState gameState,
                             BartenderBehavior bartenderBehavior,
                             CommunicationInterface communicationInterface) {

        this.birthdateBehavior = bartenderBehavior.getWishBehavior();
        this.gameState = gameState;
        this.bartenderBehavior = bartenderBehavior;
        this.communicationInterface = communicationInterface;
    }

    public LeafNode mapToLeafNode(Drink drink) {
        final LeafNode.Builder builder = LeafNode.builder()
                .communicationInterface(communicationInterface)
                .commandProcess(() -> System.out.println(getMessage(drink)))
                .routingKey(drink.getLabel());

        if (drink.containsAlcohol()) {
            builder.addDecorator(new AgeRestrictedDecorator(communicationInterface,
                    birthdateBehavior,
                    gameState.getPlayerInformation(),
                    drink));
            builder.addDecorator(new AlcoholLimiterDecorator(bartenderBehavior));
        }

        builder.addDecorator(new CashTransactionDecorator(gameState, drink));

        return builder.build();
    }

    private String getMessage(Drink drink) {
        String message = "Bartender gives you a " + drink.getLabel();
        if (drink.containsAlcohol()) {
            message += ", drink with moderation !";
        } else if (drink.isJuice()) {
            message += " juice";
        }
        return message;
    }
}
