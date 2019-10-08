package pbt;

import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.Combinators;
import net.jqwik.api.providers.ArbitraryProvider;
import net.jqwik.api.providers.TypeUsage;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Set;

import static java.time.temporal.ChronoUnit.DAYS;

public class CustomerArbitraryProvider implements ArbitraryProvider {

    private static final LocalDate OLDEST_DAY_OF_BIRTH = LocalDate.of(1980, 1, 1);
    private static final LocalDate START_OF_BUSINESS = LocalDate.of(2010, 1, 1);

    @Override
    public boolean canProvideFor(TypeUsage targetType) {
        return targetType.isOfType(Customer.class);
    }

    @Override
    public Set<Arbitrary<?>> provideFor(TypeUsage targetType, SubtypeProvider subtypeProvider) {
        Arbitrary<String> names = Arbitraries.strings().alpha().ofMinLength(3).ofMaxLength(21);
        Arbitrary<LocalDate> datesWhenJoined = datesFromRange(START_OF_BUSINESS, LocalDate.now());
        Arbitrary<LocalDate> birthday = datesFromRange(OLDEST_DAY_OF_BIRTH, LocalDate.now().minusYears(18));
        return Collections.singleton(Combinators.combine(names, datesWhenJoined, birthday).as(Customer::new));
    }

    private Arbitrary<LocalDate> datesFromRange(LocalDate minDate, LocalDate maxDate) {
        long range = DAYS.between(minDate, maxDate);
        return Arbitraries.longs().between(0, range).map(n -> minDate.plus(n, DAYS));
    }
}
