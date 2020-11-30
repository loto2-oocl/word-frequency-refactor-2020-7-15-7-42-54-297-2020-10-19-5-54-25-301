import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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

    private String buildWordFrequencyResult(List<WordFrequency> list) {
        StringJoiner wordFrequencyResult = new StringJoiner(NEW_LINE_DELIMITER);
        for (WordFrequency wordFrequency : list) {
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

    private Map<String, List<WordFrequency>> getWordListMap(List<WordFrequency> wordFrequencyList) {
        Map<String, List<WordFrequency>> wordListMap = new HashMap<>();
        for (WordFrequency wordFrequency : wordFrequencyList) {
            //       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!wordListMap.containsKey(wordFrequency.getWord())) {
                ArrayList arr = new ArrayList<>();
                arr.add(wordFrequency);
                wordListMap.put(wordFrequency.getWord(), arr);
            } else {
                wordListMap.get(wordFrequency.getWord()).add(wordFrequency);
            }
        }
        return wordListMap;
    }
}
