package fr.norsys.dojo.bartender;

import fr.norsys.dojo.bartender.process.birthday.WishHappyBirthday;

public class StandardBartenderBehavior extends BartenderBehavior {

    StandardBartenderBehavior(Bartender bartender,
                              CommunicationInterface commandInterface) {
        super(bartender, commandInterface);
    }

    @Override
    protected void beginPlay() {
        birthdateBehavior = new WishHappyBirthday();

        initBehaviorTree();

    }



    @Override
    public void bye() {
        System.out.println("Goodbye buddy");
    }


}
