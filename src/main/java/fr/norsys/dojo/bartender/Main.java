package fr.norsys.dojo.bartender;

import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        try (var scanner = new Scanner(System.in)) {
            final Bartender bartender = new Bartender(scanner::nextLine);

            bartender.waitForCommands();
        }
    }
}
