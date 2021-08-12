package fr.norsys.dojo.bartender.process;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayDeque;
import java.util.Queue;

import static java.time.format.DateTimeFormatter.ISO_DATE;
import static org.assertj.core.api.Assertions.assertThat;

class VirginBloodyMarryProcessTest {

    Queue<String> orders;
    VirginBloodyMarryProcess virginBloodyMarryProcess;
    private ByteArrayOutputStream resultStream;

    @BeforeEach
    void setUp() {
        orders = new ArrayDeque<>();
        // set up output written from System.out::println
        resultStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(resultStream));
        virginBloodyMarryProcess = new VirginBloodyMarryProcess(orders::poll, (a, b) -> {});
    }

    @Test
    void shouldNotServeIfUnder14yo() {
        final String birthDate = LocalDate.now().minusYears(13).format(ISO_DATE);
        orders.add(birthDate);
        virginBloodyMarryProcess.process();

        assertThat(resultStream.toString())
                .doesNotContain("Bartender gives you a virgin bloody marry, drink in moderation !")
                .contains("You can't have a virgin bloody marry kiddo");
    }

    @Test
    void shouldServeIfOver14yo() {
        final String birthDate = LocalDate.now().minusYears(15).format(ISO_DATE);
        orders.add(birthDate);
        virginBloodyMarryProcess.process();

        assertThat(resultStream.toString())
                .contains("Bartender gives you a virgin bloody marry, drink in moderation !")
                .doesNotContain("You can't have a virgin bloody marry kiddo");
    }
}