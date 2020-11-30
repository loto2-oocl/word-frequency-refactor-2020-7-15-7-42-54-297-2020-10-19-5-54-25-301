import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class WordFrequencyGame {
    private static final String WHITE_SPACE_REGEX = "\\s+";
    private static final String NEW_LINE_DELIMITER = "\n";
    private static final String CALCULATE_ERROR_MESSAGE = "Calculate Error";

    public String getResult(String inputString) {
        if (inputString.split(WHITE_SPACE_REGEX).length == 1) {
            return inputString + " 1";
        } else {
            try {
                //split the input string with 1 to n pieces of spaces
                String[] words = inputString.split(WHITE_SPACE_REGEX);

                List<Input> inputList = new ArrayList<>();
                for (String word : words) {
                    Input input = new Input(word, 1);
                    inputList.add(input);
                }

                //get the map for the next step of sizing the same word
                Map<String, List<Input>> wordListMap = getWordListMap(inputList);

                List<Input> list = new ArrayList<>();
                for (Map.Entry<String, List<Input>> entry : wordListMap.entrySet()) {
                    list.add(new Input(entry.getKey(), entry.getValue().size()));
                }

                list.sort((word1, word2) -> word2.getWordCount() - word1.getWordCount());

                StringJoiner wordFrequencyResult = new StringJoiner(NEW_LINE_DELIMITER);
                for (Input input : list) {
                    wordFrequencyResult.add(input.getValue() + " " + input.getWordCount());
                }
                return wordFrequencyResult.toString();
            } catch (Exception exception) {
                return CALCULATE_ERROR_MESSAGE;
            }
        }
    }

    private Map<String, List<Input>> getWordListMap(List<Input> inputList) {
        Map<String, List<Input>> wordListMap = new HashMap<>();
        for (Input input : inputList) {
            //       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!wordListMap.containsKey(input.getValue())) {
                ArrayList arr = new ArrayList<>();
                arr.add(input);
                wordListMap.put(input.getValue(), arr);
            } else {
                wordListMap.get(input.getValue()).add(input);
            }
        }
        return wordListMap;
    }
}
