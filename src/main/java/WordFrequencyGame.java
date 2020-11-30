import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class WordFrequencyGame {
    private static final String WHITE_SPACE_REGEX = "\\s+";
    private static final String NEW_LINE_DELIMITER = "\n";
    private static final String CALCULATE_ERROR_MESSAGE = "Calculate Error";

    public String getResult(String sentence) {
        try {
            List<WordFrequency> list = calculateWordFrequency(sentence);

            list.sort((word1, word2) -> word2.getCount() - word1.getCount());

            StringJoiner wordFrequencyResult = new StringJoiner(NEW_LINE_DELIMITER);
            for (WordFrequency wordFrequency : list) {
                wordFrequencyResult.add(buildWordFrequencyLine(wordFrequency));
            }
            return wordFrequencyResult.toString();
        } catch (Exception exception) {
            return CALCULATE_ERROR_MESSAGE;
        }
    }

    private List<WordFrequency> calculateWordFrequency(String sentence) {
        //split the input string with 1 to n pieces of spaces
        String[] words = sentence.split(WHITE_SPACE_REGEX);

        List<WordFrequency> wordFrequencyList = new ArrayList<>();
        for (String word : words) {
            WordFrequency wordFrequency = new WordFrequency(word, 1);
            wordFrequencyList.add(wordFrequency);
        }

        //get the map for the next step of sizing the same word
        Map<String, List<WordFrequency>> wordListMap = getWordListMap(wordFrequencyList);

        List<WordFrequency> list = new ArrayList<>();
        for (Map.Entry<String, List<WordFrequency>> entry : wordListMap.entrySet()) {
            list.add(new WordFrequency(entry.getKey(), entry.getValue().size()));
        }
        return list;
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
