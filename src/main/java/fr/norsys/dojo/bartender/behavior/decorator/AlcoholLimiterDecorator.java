package fr.norsys.dojo.bartender.behavior.decorator;

import fr.norsys.dojo.bartender.behavior.BartenderBehavior;

public class AlcoholLimiterDecorator implements BehaviorTreeDecorator{

    private final BartenderBehavior bartenderBehavior;

    public AlcoholLimiterDecorator(BartenderBehavior bartenderBehavior) {

        this.bartenderBehavior = bartenderBehavior;
    }


    @Override
    public boolean test() {
        return bartenderBehavior.getAlcoholLimitBehavior().canOrderAlcohol();
    }
}
