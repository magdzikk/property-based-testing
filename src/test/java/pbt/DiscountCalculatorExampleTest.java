package pbt;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

class DiscountCalculatorExampleTest {
    private DiscountCalculator calculator = new DiscountCalculator();

    private static final LocalDate NOW = LocalDate.of(2019, 9, 1);

    @Test
    void shouldCalculate10percentDiscount() {
        LocalDate now = LocalDate.of(2019, 9, 1);
        LocalDate dateOfBirth = LocalDate.of(1980, 1, 20);
        Customer customer = new Customer("Sue Smith", now.minusYears(1), dateOfBirth);

        BigDecimal discount = calculator.calculateDiscount(customer, now);

        Assertions.assertThat(discount).isEqualTo(new BigDecimal("0.1"));
    }

    @Test
    void shouldCalculate20percentDiscount() {
        Customer customer = new Customer("Sue Smith", NOW.minusYears(2), LocalDate.of(1980, 1, 20));

        BigDecimal discount = calculator.calculateDiscount(customer, NOW);

        Assertions.assertThat(discount).isEqualTo(new BigDecimal("0.2"));
    }

    @Test
    void shouldCalculate20percentDiscountFor3Years() {
        Customer customer = new Customer("Sue Smith", NOW.minusYears(3), LocalDate.of(1980, 1, 20));

        BigDecimal discount = calculator.calculateDiscount(customer, NOW);

        Assertions.assertThat(discount).isEqualTo(new BigDecimal("0.2"));
    }

    @Test
    void shouldCalculateNoDiscount() {
        Customer customer = new Customer("Sue Smith", NOW, LocalDate.of(1980, 1, 20));

        BigDecimal discount = calculator.calculateDiscount(customer, NOW);

        Assertions.assertThat(discount).isEqualTo(new BigDecimal("0"));
    }

    @Test
    void shouldCalculateBirthdayDiscountFor1YearMember() {
        Customer customer = new Customer("Sue Smith", NOW.minusYears(1), NOW.withYear(1980));

        BigDecimal discount = calculator.calculateDiscount(customer, NOW);

        Assertions.assertThat(discount).isEqualTo(new BigDecimal("0.3"));
    }
}
