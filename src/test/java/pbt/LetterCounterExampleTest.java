package pbt;

import com.google.common.collect.ImmutableMap;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

class LetterCounterExampleTest {

    private LetterCounter counter = new LetterCounter();

    @Test
    void shouldCountOneOccurrence() {
        Map<Character, Integer> map = counter.countOccurrences("Banana bread");

        Assertions.assertThat(map.get('B')).isEqualTo(1);
    }

    @Test
    void shouldCountMultipleOccurrences() {
        Map<Character, Integer> map = counter.countOccurrences("Banana bread");

        Assertions.assertThat(map.get('a')).isEqualTo(4);
    }

    @Test
    void shouldHaveNoEntryForNoOccurrence() {
        Map<Character, Integer> map = counter.countOccurrences("Banana bread");

        Assertions.assertThat(map.get('Q')).isNull();
    }


    @Test
    void shouldCountOccurrencesForEachLetter() {
        Map<Character, Integer> map = counter.countOccurrences("Banana bread");

        ImmutableMap<Character, Integer> expectedMap = ImmutableMap.<Character, Integer>builder()
            .put('B', 1)
            .put('a', 4)
            .put('n', 1)
            .put(' ', 1)
            .put('b', 1)
            .put('d', 1)
            .build();
        Assertions.assertThat(map).isEqualTo(expectedMap);
    }

}