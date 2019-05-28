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
        Map<Character, Integer> occurrences = counter.countOccurrences(sentence);
        int sum = occurrences.values().stream().mapToInt(n -> n).sum();

        return sum == sentence.length();
    }
}