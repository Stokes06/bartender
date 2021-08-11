package fr.norsys.dojo.bartender;

import fr.norsys.dojo.bartender.process.BeerProcess;

import java.util.EnumMap;
import java.util.stream.Collectors;

import static fr.norsys.dojo.bartender.OrderChoice.*;

public class StandardBartenderBehavior extends BartenderBehavior {

    private String memoOptionList;

    StandardBartenderBehavior(Bartender bartender, CommunicationInterface commandInterface) {
        super(bartender, commandInterface);
    }

    @Override
    protected void initProcessesMap() {
        wishHappyBirthday = true;

        commandProcessMap = new EnumMap<>(OrderChoice.class);

        commandProcessMap.put(JUICE, () -> System.out.println("Bartender gives you a nice juice, refreshing !"));
        commandProcessMap.put(BEER, new BeerProcess(this));
        commandProcessMap.put(SODA, () -> System.out.println("Bartender gives you soda !"));
        commandProcessMap.put(NOTHING, this.bartender::stopService);

        memoOptionList = createOptionList();
    }

    @Override
    public void suggestOptions() {
        System.out.println("What you want to drink : " + memoOptionList + " ? Or nothing if you want to go");
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
