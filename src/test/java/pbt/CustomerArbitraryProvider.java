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

    @Override
    public boolean canProvideFor(TypeUsage targetType) {
        return targetType.isOfType(Customer.class);
    }

    @Override
    public Set<Arbitrary<?>> provideFor(TypeUsage targetType, SubtypeProvider subtypeProvider) {
            Arbitrary<String> names = Arbitraries.strings().alpha().ofMinLength(3).ofMaxLength(21);
            LocalDate oldestPossibleDate = LocalDate.of(2014, 2, 25);
            Arbitrary<LocalDate> datesWhenJoined = datesFromRange(oldestPossibleDate, LocalDate.now());
            return Collections.singleton(Combinators.combine(names, datesWhenJoined).as(Customer::new));
    }

    private Arbitrary<LocalDate> datesFromRange(LocalDate minDate, LocalDate maxDate) {
        long range = DAYS.between(minDate, maxDate);
        return Arbitraries.longs().between(0, range).map(n -> minDate.plus(n, DAYS));
    }
}
