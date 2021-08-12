package fr.norsys.dojo.bartender.process;

import fr.norsys.dojo.bartender.menu.JuiceChoice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayDeque;
import java.util.Queue;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;

class JuiceProcessTest {

    Queue<String> orders;
    private ByteArrayOutputStream resultStream;
    JuiceProcess juiceProcess;

    @BeforeEach
    void setUp() {
        orders = new ArrayDeque<>();
        // set up output written from System.out::println
        resultStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(resultStream));
        juiceProcess = new JuiceProcess(orders::poll);
    }

    @ParameterizedTest
    @EnumSource(JuiceChoice.class)
    void shouldServeEveryJuiceAtFirstAttempt(JuiceChoice juiceChoice) {
        if (juiceChoice == JuiceChoice.BAD_CHOICE) {
            return;
        }
        orders.add(juiceChoice.getJuiceName());
        juiceProcess.process();

        assertThat(resultStream.toString())
                .contains("4 types of juices : Papaya, Pomegranate, Banana, Cucumber")
                .contains(format("Bartender gives you a %s juice", juiceChoice.getJuiceName()));
    }

    @Test
    void shouldServeJuiceOnSecondAttempt() {
        orders.add("bad");
        orders.add("papaya");
        juiceProcess.process();

        assertThat(resultStream.toString())
                .contains("4 types of juices : Papaya, Pomegranate, Banana, Cucumber")
                .containsOnlyOnce("Bartender gives you a papaya juice");
    }

    @Test
    void shouldServeJuiceOnThirdAttempt() {
        orders.add("bad");
        orders.add("bad");
        orders.add("papaya");
        juiceProcess.process();

        assertThat(resultStream.toString())
                .contains("4 types of juices : Papaya, Pomegranate, Banana, Cucumber")
                .containsOnlyOnce("Bartender gives you a papaya juice");
    }

    @Test
    void shouldNotServeJuiceOnFourthAttempt() {
        orders.add("bad");
        orders.add("bad");
        orders.add("bad");
        orders.add("papaya");
        juiceProcess.process();

        assertThat(resultStream.toString())
                .contains("4 types of juices : Papaya, Pomegranate, Banana, Cucumber")
                .doesNotContain("Bartender gives you");
    }

}