package fr.norsys.dojo.bartender.behavior;

import fr.norsys.dojo.bartender.model.Bartender;
import fr.norsys.dojo.bartender.CommunicationInterface;
import fr.norsys.dojo.bartender.game.GameState;
import fr.norsys.dojo.bartender.behavior.service.WishHappyBirthday;

public class StandardBartenderBehavior extends BartenderBehavior {

    public StandardBartenderBehavior(Bartender bartender,
                                     CommunicationInterface commandInterface,
                                     GameState gameState) {
        super(bartender, commandInterface, gameState);
    }

    @Override
    public void beginPlay() {
        birthdateBehavior = new WishHappyBirthday(gameState);

        initBehaviorTree();

    }



    @Override
    public void bye() {
        System.out.println("Goodbye buddy");
    }


}
