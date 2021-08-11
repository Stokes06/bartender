package fr.norsys.dojo.bartender;

public class Bartender {

    private boolean isServing = true;

    private final BartenderBehavior bartenderBehavior;

    public Bartender(CommunicationInterface commandInterface) {
        this.bartenderBehavior = new StandardBartenderBehavior(this, commandInterface);
    }

    public void waitForCommands() {

        while (isServing) {
            this.bartenderBehavior.options();
            this.bartenderBehavior.behave();
        }
        this.bartenderBehavior.bye();
    }

    public void stopService() {
        isServing = false;
    }
}
