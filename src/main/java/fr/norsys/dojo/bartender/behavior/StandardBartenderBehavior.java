package fr.norsys.dojo.bartender.behavior;

import fr.norsys.dojo.bartender.CommunicationInterface;
import fr.norsys.dojo.bartender.behavior.service.KickOutAlcoholLimitBehavior;
import fr.norsys.dojo.bartender.behavior.service.StandardAlcoholLimitBehavior;
import fr.norsys.dojo.bartender.behavior.service.WishHappyBirthday;
import fr.norsys.dojo.bartender.game.GameState;
import fr.norsys.dojo.bartender.model.Bartender;

public class StandardBartenderBehavior extends BartenderBehavior {

    public StandardBartenderBehavior(Bartender bartender,
                                     CommunicationInterface commandInterface,
                                     GameState gameState) {
        super(bartender, commandInterface, gameState);
    }

    @Override
    public void beginPlay() {
        birthdateBehavior = new WishHappyBirthday(gameState);
        alcoholLimitBehavior = new StandardAlcoholLimitBehavior(gameState, this::setKickOutAlcoholBehavior);

        initBehaviorTree();

    }

    private void setKickOutAlcoholBehavior() {
        System.out.println("You have been drinking enough alcohol for today !");
        this.alcoholLimitBehavior = new KickOutAlcoholLimitBehavior(gameState, this::kickOut);
    }

    private void kickOut() {
        System.out.println("You don't listen, you will have to leave now !");
        this.bartender.stopService();
    }


    @Override
    public void bye() {
        System.out.println("Goodbye buddy");
    }


}
