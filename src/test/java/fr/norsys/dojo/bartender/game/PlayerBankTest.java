package fr.norsys.dojo.bartender.game;

import fr.norsys.dojo.bartender.model.PlayerBank;
import fr.norsys.dojo.bartender.payment.Money;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerBankTest {

    @Test
    void shouldReturnTrueIfPaymentSuccess() {
        final PlayerBank playerBank = new PlayerBank(Money.of(100.0));
        final boolean paymentSucceed = playerBank.pay(Money.of(2.0));
        assertThat(paymentSucceed).isTrue();
        assertThat(playerBank.getPlayerCash()).isEqualTo(Money.of(98.0));
    }

    @Test
    void shouldReturnFalseIfPaymentFailed() {
        final PlayerBank playerBank = new PlayerBank(Money.of(100.0));
        final boolean paymentSucceed = playerBank.pay(Money.of(101.0));
        assertThat(paymentSucceed).isFalse();
        assertThat(playerBank.getPlayerCash()).isEqualTo(Money.of(100.0));
    }

}