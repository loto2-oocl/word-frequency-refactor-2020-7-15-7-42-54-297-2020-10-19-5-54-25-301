import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class WordFrequencyGame {
    private static final String WHITE_SPACE_REGEX = "\\s+";
    private static final String NEW_LINE_DELIMITER = "\n";
    private static final String CALCULATE_ERROR_MESSAGE = "Calculate Error";

    public String getResult(String sentence) {
        try {
            List<WordFrequency> list = calculateWordFrequency(sentence);

            list.sort((word1, word2) -> word2.getCount() - word1.getCount());

            return buildWordFrequencyResult(list);
        } catch (Exception exception) {
            return CALCULATE_ERROR_MESSAGE;
        }
    }

    private String buildWordFrequencyResult(List<WordFrequency> wordFrequencyList) {
        StringJoiner wordFrequencyResult = new StringJoiner(NEW_LINE_DELIMITER);
        for (WordFrequency wordFrequency : wordFrequencyList) {
            wordFrequencyResult.add(buildWordFrequencyLine(wordFrequency));
        }
        return wordFrequencyResult.toString();
    }

    private List<WordFrequency> calculateWordFrequency(String sentence) {
        List<String> words = Arrays.asList(sentence.split(WHITE_SPACE_REGEX));
        HashSet<String> distinctWords = new HashSet<>(words);

        return distinctWords.stream()
            .map(word -> new WordFrequency(word, Collections.frequency(words, word)))
            .collect(Collectors.toList());
    }

    private String buildWordFrequencyLine(WordFrequency wordFrequency) {
        return String.format("%s %d", wordFrequency.getWord(), wordFrequency.getCount());
    }
}
