package fr.norsys.dojo.bartender;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("What you want to drink : beer or juice ? Or nothing if you want to go");

                String input = scanner.next();
                if (input.equalsIgnoreCase("nothing")) {
                    System.out.println("Goodbye buddy");
                    System.exit(0);
                }

                if (input.equalsIgnoreCase("juice")){
                    System.out.println("Bartender gives you a nice juice, refreshing !");
                    continue;
                }

                if (input.equalsIgnoreCase("beer")){
                    System.out.println("I would need to see an ID please, what is your birthdate ? (yyyy-mm-dd)");
                    String birthDate = scanner.next();
                    try {
                        LocalDate birth = LocalDate.parse(birthDate, DateTimeFormatter.ISO_DATE);
                        if (birth.plusYears(18).isBefore(LocalDate.now())){
                            System.out.println("Bartender gives you a beer, drink in moderation !");
                        } else {
                            System.out.println("You can't have a beer kiddo");
                        }
                    } catch (Exception e){
                        System.out.println("Can't read your ID son");
                    }
                }
            }
        }
    }
}
