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

import static org.assertj.core.api.Assertions.assertThat;

class LimitedPlayerCashTest {

    Queue<String> orders;

    Bartender bartender;

    ByteArrayOutputStream bartenderResponsesStream;

    @BeforeEach
    void setUp() {
        orders = new ArrayDeque<>();
        final PlayerBank infiniteAmountPlayerBank = new PlayerBank(10.0);
        bartender = new Bartender(GameState.makeGameState(infiniteAmountPlayerBank, new PlayerInformation()), Bar.makeBar(), orders::poll);
        // set up output written from System.out::println
        bartenderResponsesStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bartenderResponsesStream));
    }

    @Test
    void canAfford2Sodas() {
        orders.add("soda");
        orders.add("soda");
        orders.add("nothing");

        bartender.beginPlay();

        assertThat(bartenderResponsesStream.toString())
                .contains("Bartender gives you a soda")
                .doesNotContain("You are short on cash");
    }

    @Test
    void cantAffordOnly3Sodas() {
        orders.add("soda");
        orders.add("soda");
        orders.add("soda");
        orders.add("nothing");

        bartender.beginPlay();

        assertThat(bartenderResponsesStream.toString())
                .contains("Bartender gives you a soda")
                .contains("You are short on cash");
    }

}
