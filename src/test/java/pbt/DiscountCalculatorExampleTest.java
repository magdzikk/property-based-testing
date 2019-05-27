package pbt;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.YEARS;

class DiscountCalculatorExampleTest {
    private DiscountCalculator calculator = new DiscountCalculator();

    @Test
    void shouldCalculate10percentDiscount() {
        Customer customer = new Customer("Susan Smith", LocalDate.now().minus(1, YEARS));

        BigDecimal discount = calculator.calculateDiscount(customer);

        Assertions.assertThat(discount).isEqualTo(new BigDecimal("0.1"));
    }

    @Test
    void shouldCalculate20percentDiscount() {
        Customer customer = new Customer("Susan Smith", LocalDate.now().minus(2, YEARS));

        BigDecimal discount = calculator.calculateDiscount(customer);

        Assertions.assertThat(discount).isEqualTo(new BigDecimal("0.2"));
    }

    @Test
    void shouldCalculate30percentDiscount() {
        Customer customer = new Customer("Susan Smith", LocalDate.now().minus(3, YEARS));

        BigDecimal discount = calculator.calculateDiscount(customer);

        Assertions.assertThat(discount).isEqualTo(new BigDecimal("0.3"));
    }

    @Test
    void shouldCalculate30percentDiscountFor4Years() {
        Customer customer = new Customer("Susan Smith", LocalDate.now().minus(4, YEARS));

        BigDecimal discount = calculator.calculateDiscount(customer);

        Assertions.assertThat(discount).isEqualTo(new BigDecimal("0.3"));
    }

    @Test
    void shouldCalculateNoDiscount() {
        Customer customer = new Customer("Susan Smith", LocalDate.now());

        BigDecimal discount = calculator.calculateDiscount(customer);

        Assertions.assertThat(discount).isEqualTo(new BigDecimal("0"));
    }
}