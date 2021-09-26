package fr.norsys.dojo.bartender;

import fr.norsys.dojo.bartender.communication.CommunicationInput;
import fr.norsys.dojo.bartender.communication.CommunicationOutput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import static org.assertj.core.api.Assertions.assertThat;


// TODO run with coverage to see missing test cases
// TODO when you covered every lines, then write a test for a new feature and then only modify Game class code (TDD)
// Navigate to Game class with CTRL + SHIFT + T in both ways
class GameTest {

    private final List< String > messages = new ArrayList<>();
    private final Queue< String > input = new ArrayDeque<>();
    private Game game;

    @BeforeEach
    void setUp() {
        // the voice will 'talk' directly to the messages List
        final CommunicationOutput voice = messages::add;
        // the input will come from the input Queue
        // poll Retrieves and removes the head of a queue
        final CommunicationInput input = this.input::poll;
        // A new game will be created for every test method
        game = new Game(voice, input);
    }

    @Test
    void shouldServeJuiceWhenAsked() {
        // GIVEN
        // Start with asking for a juice
        input.add("juice");
        // And Then ask to leave the game (don't forget it or the test will run forever)
        input.add("nothing");

        // WHEN
        game.run();

        // print messages, uncomment to debug
        // messages.forEach(System.out::println);

        // TODO visit https://assertj.github.io/doc/ for  documentation
        assertThat(messages)
                .hasSize(4)
                .containsOnlyOnce("Bartender gives you a nice juice, refreshing !");

        // Expecting first message to start with 'What you want to drink'
        assertThat(messages).first().satisfies(s -> assertThat(s).startsWith("What you want to drink"));
        // Expecting last message to be 'Goodbye buddy'
        assertThat(messages).last().satisfies(s -> assertThat(s).isEqualTo("Goodbye buddy"));

    }

    @Test
    void testToRename() {
        // TODO write test cases for beer
        input.add("....");
        input.add("nothing"); // do not forget to stop with 'nothing'

        // WHEN
        game.run();

        // THEN
        // TODO assertions
        assertThat(messages)
                .isNotEmpty();
    }
}