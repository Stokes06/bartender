package fr.norsys.dojo.bartender;

import fr.norsys.dojo.bartender.game.GameState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayDeque;
import java.util.Queue;

import static fr.norsys.dojo.bartender.TestHelper.generateBirthDate;
import static org.assertj.core.api.Assertions.assertThat;

class BartenderTest {

    Queue<String> orders;

    Bartender bartender;

    ByteArrayOutputStream bartenderResponsesStream;

    @BeforeEach
    void setUp() {
        orders = new ArrayDeque<>();
        bartender = new Bartender(GameState.makeGameState(), orders::poll);
        // set up output written from System.out::println
        bartenderResponsesStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bartenderResponsesStream));
    }

    @Test
    void shouldServeJuice() {

        orders.add("juice");
        orders.add("banana");
        orders.add("nothing");
        bartender.waitForCommands();

        assertThat(bartenderResponsesStream.toString())
                .contains("What you want to drink")
                .contains("Bartender gives you a banana juice")
                .contains("Goodbye buddy");
    }

    @Test
    void shouldServeBeerIfLegal() {

        String birthDate = generateBirthDate(true);

        orders.add("beer");
        orders.add(birthDate);
        orders.add("nothing");
        bartender.waitForCommands();

        assertThat(bartenderResponsesStream.toString())
                .contains("I would need to see an ID please, what is your birthdate ? (yyyy-mm-dd)")
                .contains("Bartender gives you a beer, drink in moderation !")
                .contains("Goodbye buddy");

    }

    @Test
    void shouldNotServeBeerIfMinor() {

        String birthDate = generateBirthDate(false);

        orders.add("beer");
        orders.add(birthDate);
        orders.add("nothing");
        bartender.waitForCommands();

        assertThat(bartenderResponsesStream.toString())
                .contains("I would need to see an ID please, what is your birthdate ? (yyyy-mm-dd)")
                .contains("You can't have a beer kiddo")
                .contains("Goodbye buddy");

    }

    @Test
    void shouldAlertIfCantReadBirthDate() {
        orders.add("beer");
        orders.add("bad-date");
        orders.add("nothing");
        bartender.waitForCommands();

        assertThat(bartenderResponsesStream.toString())
                .contains("Can't read your ID son");

    }
}