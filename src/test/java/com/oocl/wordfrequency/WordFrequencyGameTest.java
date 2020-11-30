package com.oocl.wordfrequency;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WordFrequencyGameTest {

    @Test
    void should_return_the_1_when_get_result_given_sentence_the() {
        //Given
        String sentence = "the";

        //When
        String actual = new WordFrequencyGame().getResult(sentence);

        //Then
        assertEquals("the 1", actual);
    }

    @Test
    void should_return_the_1_is_1_when_get_result_given_the_is() {
        //Given
        String sentence = "the is";

        //When
        String actual = new WordFrequencyGame().getResult(sentence);

        //Then
        assertEquals("the 1\nis 1", actual);
    }

    @Test
    void should_return_the_1_is_1_when_get_result_given_the_is_with_special_spaces() {
        //Given

        String sentence = "the      is";
        //When
        String actual = new WordFrequencyGame().getResult(sentence);

        //Then
        assertEquals("the 1\nis 1", actual);
    }

    @Test
    void should_return_the_1_is_1_when_get_result_given_the_is_with_special_enter() {
        //Given
        String sentence = "the   \n   is";

        //When
        String actual = new WordFrequencyGame().getResult(sentence);

        //Then
        assertEquals("the 1\nis 1", actual);
    }

    @Test
    void should_return_the_2_is_1_when_get_result_given_the_the_is() {
        //Given
        String sentence = "the the is";

        //When
        String actual = new WordFrequencyGame().getResult(sentence);

        //Then
        assertEquals("the 2\nis 1", actual);
    }

    @Test
    void should_return_is_2_the_1_which_sort_with_count_descending_when_get_result_given_the_is_is() {
        //Given
        String sentence = "the is is";

        //When
        String actual = new WordFrequencyGame().getResult(sentence);

        //Then
        assertEquals("is 2\nthe 1", actual);
    }

    private void validate_Input_words_process_to_expected_word(String sentence, String expected) {
        WordFrequencyGame game = new WordFrequencyGame();
        //When
        String actual = game.getResult(sentence);
        //Then
        assertEquals(expected, actual);
    }
}
