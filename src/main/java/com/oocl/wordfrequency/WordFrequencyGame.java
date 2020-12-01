package com.oocl.wordfrequency;

import com.oocl.wordfrequency.exception.CalculateErrorException;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class WordFrequencyGame {
    private static final String WHITE_SPACE_REGEX = "\\s+";
    private static final String NEW_LINE_DELIMITER = "\n";

    public String getResult(String sentence) throws CalculateErrorException {
        try {
            List<WordFrequency> wordFrequencyList = calculateWordFrequency(sentence);
            List<WordFrequency> sortedWordFrequencyList = sortWordFrequencyListByCountDescending(wordFrequencyList);
            return buildWordFrequencyResult(sortedWordFrequencyList);
        } catch (Exception exception) {
            throw new CalculateErrorException();
        }
    }

    private List<WordFrequency> sortWordFrequencyListByCountDescending(List<WordFrequency> wordFrequencyList) {
        return wordFrequencyList.stream()
            .sorted(Comparator.comparingInt(WordFrequency::getCount).reversed())
            .collect(Collectors.toList());
    }

    private String buildWordFrequencyResult(List<WordFrequency> wordFrequencyList) {
        return wordFrequencyList.stream()
            .map(WordFrequency::buildWordFrequencyLine)
            .collect(Collectors.joining(NEW_LINE_DELIMITER));
    }

    public List<WordFrequency> calculateWordFrequency(String sentence) {
        List<String> words = Arrays.asList(sentence.split(WHITE_SPACE_REGEX));

        return words.stream()
            .distinct()
            .map(word -> new WordFrequency(word, Collections.frequency(words, word)))
            .collect(Collectors.toList());
    }
}
