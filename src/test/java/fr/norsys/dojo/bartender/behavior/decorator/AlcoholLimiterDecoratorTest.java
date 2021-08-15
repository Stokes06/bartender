package fr.norsys.dojo.bartender.behavior.decorator;

import fr.norsys.dojo.bartender.game.GameState;
import fr.norsys.dojo.bartender.model.PlayerBank;
import fr.norsys.dojo.bartender.model.PlayerInformation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;

class AlcoholLimiterDecoratorTest {

    private AlcoholLimiterDecorator alcoholLimiterDecorator;
    private GameState gameState;

    @BeforeEach
    void setUp() {
        gameState = GameState.makeGameState(
                new PlayerBank(Double.MAX_VALUE),
                new PlayerInformation()
        );

        alcoholLimiterDecorator = new AlcoholLimiterDecorator(gameState);
    }

    @Test
    void shouldIncrementAlcoholCountIfNotMax() {

        final boolean test = alcoholLimiterDecorator.test();

        assertThat(test).isTrue();
        assertThat(gameState.getOrderedAlcoholsCount()).isEqualTo(1);
    }

    @Test
    void shouldStopAtMax() {

        IntStream.range(0, 10).forEach(ignored -> alcoholLimiterDecorator.test());

        final boolean test = alcoholLimiterDecorator.test();

        assertThat(test).isFalse();
        assertThat(gameState.getOrderedAlcoholsCount()).isEqualTo(10);
    }

}