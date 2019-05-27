package pbt;

import java.math.BigDecimal;
import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.YEARS;

class DiscountCalculator {

    BigDecimal calculateDiscount(Customer customer) {
        long yearsOfMembership = YEARS.between(customer.getJoinedAt(), LocalDate.now());
        switch ((int) yearsOfMembership) {
//            case 0: return new BigDecimal("0");
            case 1: return new BigDecimal("0.1");
            case 2: return new BigDecimal("0.2");
            default: return new BigDecimal("0.3");
        }
    }
}

