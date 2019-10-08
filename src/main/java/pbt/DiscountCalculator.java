package pbt;

import java.math.BigDecimal;
import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.YEARS;

class DiscountCalculator {

    BigDecimal calculateDiscount(Customer customer, LocalDate now) {
        LocalDate birthdayThisYear = LocalDate.of(now.getYear(), customer.getDateOfBirth().getMonthValue(), customer.getDateOfBirth().getDayOfMonth());
        if (now.isEqual(birthdayThisYear)) {
            return new BigDecimal("0.3");
        }

        long yearsOfMembership = YEARS.between(customer.getJoinedAt(), now);
        switch ((int) yearsOfMembership) {
            case 0:
                return new BigDecimal("0");
            case 1:
                return new BigDecimal("0.1");
            default:
                return new BigDecimal("0.2");
        }
    }

    BigDecimal calculateDiscount_correct(Customer customer, LocalDate now) {
        if (now.getMonthValue() == customer.getDateOfBirth().getMonthValue() && now.getDayOfMonth() == customer.getDateOfBirth().getDayOfMonth()) {
            return new BigDecimal("0.3");
        }

        long yearsOfMembership = YEARS.between(customer.getJoinedAt(), now);
        switch ((int) yearsOfMembership) {
            case 0:
                return new BigDecimal("0");
            case 1:
                return new BigDecimal("0.1");
            default:
                return new BigDecimal("0.2");
        }
    }
}

