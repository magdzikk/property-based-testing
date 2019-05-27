package pbt;

import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;

//examples from https://jqwik.net/docs/current/user-guide.html
class ArbitrariesTest {


    @Property
    boolean concatenatingStringWithInt(
        @ForAll("shortStrings") String aShortString,
        @ForAll("10 to 99") int aNumber
    ) {
        String concatenated = aShortString + aNumber;
        return concatenated.length() > 2 && concatenated.length() < 11;
    }

    @Provide
    Arbitrary<String> shortStrings() {
        return Arbitraries.strings().withCharRange('a', 'z')
            .ofMinLength(1).ofMaxLength(8);
    }

    @Provide("10 to 99")
    Arbitrary<Integer> numbers() {
        return Arbitraries.integers().between(10, 99);
    }
}
