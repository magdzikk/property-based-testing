package pbt;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.AlphaChars;
import net.jqwik.api.constraints.BigRange;
import net.jqwik.api.constraints.Scale;

import java.math.BigDecimal;
import java.util.Map;

class LetterCounterPropertyTest {

    private LetterCounter counter = new LetterCounter();

    @Property
    boolean sumOfAllOccurrencesShouldEqualWordLength(@ForAll @AlphaChars String sentence) {
        //    boolean sumOfAllOccurrencesShouldEqualWordLength(@ForAll String sentence) {
        Map<Character, Integer> occurrences = counter.countOccurrences(sentence);
        int sum = occurrences.values().stream().mapToInt(n -> n).sum();

        return sum == sentence.length();
    }

    @Property
    boolean absoluteValueOfAllNumbersIsPositive(@ForAll int anInteger) {
        return Math.abs(anInteger) >= 0;
    }

    @Property
    void someProperty(@ForAll @BigRange(min = "1", max = "99.99") @Scale(2) BigDecimal price) {
        System.out.println(price);
    }

}