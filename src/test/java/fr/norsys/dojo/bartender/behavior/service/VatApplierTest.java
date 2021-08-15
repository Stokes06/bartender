package fr.norsys.dojo.bartender.behavior.service;

import fr.norsys.dojo.bartender.model.menu.Drink;
import fr.norsys.dojo.bartender.model.menu.DrinkType;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class VatApplierTest {

    private final VatApplier vatApplier = new VatApplier();

    @Test
    void test() {
        final Drink juice = new Drink(DrinkType.JUICE, "cucumber", 1.0);

        // considering 18.82% VAT tax
        assertThat(vatApplier.getVatPrice(juice)).isEqualTo(1.19);
    }

}