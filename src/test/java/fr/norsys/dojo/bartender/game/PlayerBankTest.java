package fr.norsys.dojo.bartender.game;

import fr.norsys.dojo.bartender.model.PlayerBank;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class PlayerBankTest {

    @Test
    void shouldReturnTrueIfPaymentSuccess() {
        final PlayerBank playerBank = new PlayerBank(100.0);
        final boolean paymentSucceed = playerBank.pay(2.0);
        assertThat(paymentSucceed).isTrue();
        assertThat(playerBank.getPlayerCash()).isEqualTo(98.0);
    }

    @Test
    void shouldReturnFalseIfPaymentFailed() {
        final PlayerBank playerBank = new PlayerBank(100.0);
        final boolean paymentSucceed = playerBank.pay(101.0);
        assertThat(paymentSucceed).isFalse();
        assertThat(playerBank.getPlayerCash()).isEqualTo(100.0);
    }

}