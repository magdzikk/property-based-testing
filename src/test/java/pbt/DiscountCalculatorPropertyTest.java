package pbt;

import net.jqwik.api.Assume;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;

import java.math.BigDecimal;

class DiscountCalculatorPropertyTest {

    private DiscountCalculator calculator = new DiscountCalculator();

    @Property
    boolean discountShouldNotBeOver30percent(@ForAll Customer customer) {
        BigDecimal discount = calculator.calculateDiscount(customer);
        return discount.compareTo(new BigDecimal("0.3")) <= 0;
    }

    @Property
    boolean discountShouldBeProportionalToMembershipYears(@ForAll Customer olderCustomer, @ForAll Customer newerCustomer) {
        Assume.that(olderCustomer.getJoinedAt().isBefore(newerCustomer.getJoinedAt()));

        BigDecimal discountForOlderCustomer = calculator.calculateDiscount(olderCustomer);
        BigDecimal discountForNewerCustomer = calculator.calculateDiscount(newerCustomer);
        return discountForOlderCustomer.compareTo(discountForNewerCustomer) >= 0;
    }

}