package pbt;

import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.Assume;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;

import java.math.BigDecimal;
import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

class DiscountCalculatorPropertyTest {

    private DiscountCalculator calculator = new DiscountCalculator();

    @Property
    boolean discountShouldNotBeOver30percent(@ForAll Customer customer, @ForAll("futureDate") LocalDate now) {
        BigDecimal discount = calculator.calculateDiscount(customer, now);
        return discount.compareTo(new BigDecimal("0.3")) <= 0;
    }

    @Property
    boolean discountShouldBeProportionalToMembershipYears(@ForAll Customer olderCustomer, @ForAll Customer newerCustomer, @ForAll("futureDate") LocalDate now) {
        Assume.that(isNotBirthday(olderCustomer, now));
        Assume.that(isNotBirthday(newerCustomer, now));
        Assume.that(olderCustomer.getJoinedAt().isBefore(newerCustomer.getJoinedAt()));

        BigDecimal discountForOlderCustomer = calculator.calculateDiscount(olderCustomer, now);
        BigDecimal discountForNewerCustomer = calculator.calculateDiscount(newerCustomer, now);
        return discountForOlderCustomer.compareTo(discountForNewerCustomer) >= 0;
    }

    @Property
    boolean birthdayDiscountShouldBeHighest(@ForAll Customer customer, @ForAll Customer birthdayCustomer) {
        LocalDate now = birthdayCustomer.getDateOfBirth().withYear(2019);
        Assume.that(sameDayOfMonth(birthdayCustomer.getDateOfBirth(), now));
        Assume.that(isNotBirthday(customer, now));

        BigDecimal ordinaryDiscount = calculator.calculateDiscount(customer, now);
        BigDecimal birthdayDiscount = calculator.calculateDiscount(birthdayCustomer, now);
        return birthdayDiscount.compareTo(ordinaryDiscount) >= 0;
    }

    private boolean sameDayOfMonth(LocalDate date1, LocalDate date2) {
        return date1.getDayOfMonth() == date2.getDayOfMonth();
    }

    private boolean isNotBirthday(Customer customer, LocalDate now) {
        return customer.getDateOfBirth().getMonthValue() != now.getMonthValue() || customer.getDateOfBirth().getDayOfMonth() != now.getDayOfMonth();
    }

    private Arbitrary<LocalDate> datesFromRange(LocalDate minDate, LocalDate maxDate) {
        long range = DAYS.between(minDate, maxDate);
        return Arbitraries.longs().between(0, range).map(n -> minDate.plus(n, DAYS));
    }

    @Provide
    private Arbitrary<LocalDate> futureDate() {
        return datesFromRange(LocalDate.now(), LocalDate.of(2200, 1, 1));
    }
}
