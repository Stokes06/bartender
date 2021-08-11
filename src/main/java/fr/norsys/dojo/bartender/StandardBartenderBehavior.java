package fr.norsys.dojo.bartender;

import fr.norsys.dojo.bartender.process.BeerProcess;

import java.util.HashMap;
import java.util.stream.Collectors;

import static fr.norsys.dojo.bartender.OrderChoice.*;

public class StandardBartenderBehavior extends BartenderBehavior {

    private String optionList;

    StandardBartenderBehavior(Bartender bartender, CommunicationInterface commandInterface) {
        super(bartender, commandInterface);
    }

    @Override
    protected void initProcessesMap() {

        commandProcessMap = new HashMap<>();

        commandProcessMap.put(JUICE, () -> System.out.println("Bartender gives you a nice juice, refreshing !"));
        commandProcessMap.put(BEER, new BeerProcess(this.communicationInterface));
        commandProcessMap.put(SODA, () -> System.out.println("Bartender gives you soda !"));
        commandProcessMap.put(NOTHING, this.bartender::stopService);

        optionList = createOptionList();
    }

    @Override
    public void options() {
        System.out.println("What you want to drink : " + optionList + " ? Or nothing if you want to go");
    }

    @Override
    public void bye() {
        System.out.println("Goodbye buddy");
    }

    private String createOptionList(){
        return commandProcessMap.keySet()
                .stream()
                .filter(OrderChoice::isMenuChoice)
                .map(OrderChoice::getOrderChoiceName)
                .sorted()
                .collect(Collectors.joining(" or "));

    }


}
