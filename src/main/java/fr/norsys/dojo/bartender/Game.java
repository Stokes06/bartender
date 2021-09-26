package fr.norsys.dojo.bartender;

import fr.norsys.dojo.bartender.communication.CommunicationInput;
import fr.norsys.dojo.bartender.communication.CommunicationOutput;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Game {

    private final CommunicationOutput voice;
    private final CommunicationInput communicationInput;

    public Game(CommunicationOutput voice, CommunicationInput communicationInput) {
        this.voice = voice;
        this.communicationInput = communicationInput;
    }

    public void run() {
        while (true) {
            voice.say("What you want to drink : beer or juice ? Or nothing if you want to go");

            String input = communicationInput.getNextInput();
            if (input.equalsIgnoreCase("nothing")) {
                voice.say("Goodbye buddy");
                System.exit(0);
            }

            if (input.equalsIgnoreCase("juice")){
                voice.say("Bartender gives you a nice juice, refreshing !");
                continue;
            }

            if (input.equalsIgnoreCase("beer")){
                voice.say("I would need to see an ID please, what is your birthdate ? (yyyy-mm-dd)");
                String birthDate = communicationInput.getNextInput();
                try {
                    LocalDate birth = LocalDate.parse(birthDate, DateTimeFormatter.ISO_DATE);
                    if (birth.plusYears(18).isBefore(LocalDate.now())){
                        voice.say("Bartender gives you a beer, drink in moderation !");
                    } else {
                        voice.say("You can't have a beer kiddo");
                    }
                } catch (Exception e){
                    voice.say("Can't read your ID son");
                }
            }
        }

    }
}
