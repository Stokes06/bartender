package fr.norsys.dojo.bartender;

import fr.norsys.dojo.bartender.process.BeerProcess;

import java.util.HashMap;

public class StandardBartenderBehavior extends BartenderBehavior {


    StandardBartenderBehavior(Bartender bartender, CommunicationInterface commandInterface) {
        super(bartender, commandInterface);
    }

    @Override
    protected void initProcessesMap() {

        commandProcessMap = new HashMap<>();

        commandProcessMap.put("juice", () -> System.out.println("Bartender gives you a nice juice, refreshing !"));
        commandProcessMap.put("beer", new BeerProcess(this.communicationInterface));
        commandProcessMap.put("nothing", this.bartender::stopService);
    }

    @Override
    public void options() {
        System.out.println("What you want to drink : beer or juice ? Or nothing if you want to go");
    }

    @Override
    public void bye() {
        System.out.println("Goodbye buddy");
    }


}
