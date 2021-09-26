package fr.norsys.dojo.bartender;

import fr.norsys.dojo.bartender.communication.CommunicationInput;
import fr.norsys.dojo.bartender.communication.CommunicationOutput;

import java.util.Scanner;


public class Main {

    public static void main(String[] args){

        try (Scanner scanner = new Scanner(System.in)) {
            CommunicationInput communicationInput = scanner::nextLine;
            CommunicationOutput voice = System.out::println;
            new Game(voice, communicationInput).run();
        }
    }
}
