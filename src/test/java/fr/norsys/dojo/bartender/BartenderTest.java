package fr.norsys.dojo.bartender;

import fr.norsys.dojo.bartender.game.GameState;
import fr.norsys.dojo.bartender.model.Bartender;
import fr.norsys.dojo.bartender.model.PlayerBank;
import fr.norsys.dojo.bartender.model.Bar;
import fr.norsys.dojo.bartender.model.PlayerInformation;
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
        final PlayerBank infiniteAmountPlayerBank = new PlayerBank(Double.MAX_VALUE);
        bartender = new Bartender(GameState.makeGameState(infiniteAmountPlayerBank, new PlayerInformation()), Bar.makeBar(), orders::poll);
        // set up output written from System.out::println
        bartenderResponsesStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bartenderResponsesStream));
    }

    @Test
    void shouldServeJuice() {

        orders.add("juice");
        orders.add("banana");
        orders.add("nothing");
        bartender.beginPlay();

        assertThat(bartenderResponsesStream.toString())
                .contains("you can choose between")
                .contains("Bartender gives you a banana juice")
                .contains("Goodbye buddy");
    }

    @Test
    void shouldServeBeerIfLegal() {

        String birthDate = generateBirthDate(true);

        orders.add("beer");
        orders.add(birthDate);
        orders.add("nothing");
        bartender.beginPlay();

        assertThat(bartenderResponsesStream.toString())
                .contains("I would need to see an ID please, what is your birthdate ? (yyyy-mm-dd)")
                .contains("Bartender gives you a beer, drink with moderation !")
                .contains("Goodbye buddy");

    }

    @Test
    void shouldNotServeBeerIfMinor() {

        String birthDate = generateBirthDate(false);

        orders.add("beer");
        orders.add(birthDate);
        orders.add("nothing");
        bartender.beginPlay();

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
        bartender.beginPlay();

        assertThat(bartenderResponsesStream.toString())
                .contains("Can't read your ID son");

    }

    @Test
    void shouldAskTwoMoreTimeOnJuiceIfNotUnderstood() {
        orders.add("juice");
        orders.add("bad");
        orders.add("bad");
        orders.add("bad");
        orders.add("bad");
        orders.add("bad");
        orders.add("nothing");
        bartender.beginPlay();

        assertThat(bartenderResponsesStream.toString())
                .contains("you can choose between beer or red wine or virgin bloody marry or soda or juice or nothing" + System.lineSeparator() +
                        "you can choose between papaya or pomegranate or banana or cucumber" + System.lineSeparator() +
                        "you can choose between papaya or pomegranate or banana or cucumber" + System.lineSeparator() +
                        "you can choose between papaya or pomegranate or banana or cucumber" + System.lineSeparator() +
                        "you can choose between beer or red wine or virgin bloody marry or soda or juice or nothing");

    }
}