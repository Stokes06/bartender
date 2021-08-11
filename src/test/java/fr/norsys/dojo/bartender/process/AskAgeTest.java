package fr.norsys.dojo.bartender.process;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static java.time.format.DateTimeFormatter.ISO_DATE;
import static org.assertj.core.api.Assertions.*;

class AskAgeTest {

    @Test
    void shouldWishBirthDay() {

        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        LocalDate now = LocalDate.now();
        final AskAge askAge = new AskAge(
                () -> now
                        .minusYears(12)
                        .format(ISO_DATE),
                true);

        final boolean isOver18 = askAge.askAge(18, now);

        assertThat(isOver18).isFalse();
        assertThat(out.toString()).contains("Happy birthday !");
    }

    @Test
    void shouldNotWishBirthDayBecauseOfBehavior() {

        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        LocalDate now = LocalDate.now();
        final AskAge askAge = new AskAge(
                () -> now
                        .minusYears(12)
                        .format(ISO_DATE),
                false);

        final boolean isOver18 = askAge.askAge(18, now);

        assertThat(isOver18).isFalse();
        assertThat(out.toString()).doesNotContain("Happy birthday !");
    }

    @Test
    void shouldNotWishBirthDayBecauseOfDate() {

        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        LocalDate now = LocalDate.now();
        final AskAge askAge = new AskAge(
                () -> now
                        .minusYears(50)
                        .minus(15, ChronoUnit.DAYS)
                        .format(ISO_DATE),
                false);

        final boolean isOver18 = askAge.askAge(18, now);

        assertThat(isOver18).isTrue();
        assertThat(out.toString()).doesNotContain("Happy birthday !");
    }
}