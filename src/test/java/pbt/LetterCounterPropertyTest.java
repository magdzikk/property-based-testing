package pbt;

import net.jqwik.api.Assume;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.AlphaChars;

import java.util.Map;

class LetterCounterPropertyTest {

    private LetterCounter counter = new LetterCounter();

    @Property
    boolean sumOfAllOccurrencesShouldEqualWordLength(@ForAll @AlphaChars String sentence) {
        Map<Character, Integer> occurrences = counter.countOccurrences(sentence);
        int sum = occurrences.values().stream().mapToInt(n -> n).sum();

        return sum == sentence.length();
    }

    @Property
    boolean mapShouldNeverBeNull(@ForAll String sentence) {
        Map<Character, Integer> occurrences = counter.countOccurrences(sentence);

        return occurrences != null;
    }

    @Property
    boolean nonEmptyMapForNonEmptySentence(@ForAll String sentence) {
        Assume.that(sentence.length() > 0);

        Map<Character, Integer> occurrences = counter.countOccurrences(sentence);

        return occurrences.size() > 0;
    }
}
