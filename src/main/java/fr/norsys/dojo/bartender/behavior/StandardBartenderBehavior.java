package fr.norsys.dojo.bartender.behavior;

import fr.norsys.dojo.bartender.Bartender;
import fr.norsys.dojo.bartender.CommunicationInterface;
import fr.norsys.dojo.bartender.game.GameState;
import fr.norsys.dojo.bartender.process.birthday.WishHappyBirthday;

public class StandardBartenderBehavior extends BartenderBehavior {

    public StandardBartenderBehavior(Bartender bartender,
                                     CommunicationInterface commandInterface,
                                     GameState gameState) {
        super(bartender, commandInterface, gameState);
    }

    @Override
    public void beginPlay() {
        birthdateBehavior = new WishHappyBirthday();

        initBehaviorTree();

    }



    @Override
    public void bye() {
        System.out.println("Goodbye buddy");
    }


}
