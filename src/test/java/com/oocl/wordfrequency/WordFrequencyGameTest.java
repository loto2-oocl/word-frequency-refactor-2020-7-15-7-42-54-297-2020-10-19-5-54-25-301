package com.oocl.wordfrequency;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WordFrequencyGameTest {

    @Test
    public void should_get_the_1_when_get_result_given_the() {
        //Given
        String inputStr = "the";
        String expectResult = "the 1";
        validate_Input_words_process_to_expected_word(inputStr, expectResult);
    }

    @Test
    public void should_return_the_1_is_1_when_get_result_given_the_is() {
        //Given
        String inputStr = "the is";
        String expectResult = "the 1\nis 1";
        validate_Input_words_process_to_expected_word(inputStr, expectResult);
    }

    @Test
    public void should_return_the_1_is_1_when_get_result_given_the_is_with_special_spaces() {
        //Given
        String inputStr = "the      is";
        String expectResult = "the 1\nis 1";
        validate_Input_words_process_to_expected_word(inputStr, expectResult);
    }

    @Test
    public void should_return_the_1_is_1_when_get_result_given_the_is_with_special_enter() {
        //Given
        String inputStr = "the   \n   is";
        String expectResult = "the 1\nis 1";
        validate_Input_words_process_to_expected_word(inputStr, expectResult);
    }

    @Test
    public void should_return_the_2_is_1_when_get_result_given_the_the_is() {
        //Given
        String inputStr = "the the is";
        String expectResult = "the 2\nis 1";
        validate_Input_words_process_to_expected_word(inputStr, expectResult);
    }

    @Test
    public void should_return_is_2_the_1_which_sort_with_count_descending_when_get_result_given_the_is_is() {
        //Given
        String inputStr = "the is is";
        String expectResult = "is 2\nthe 1";
        validate_Input_words_process_to_expected_word(inputStr, expectResult);
    }

    private void validate_Input_words_process_to_expected_word(String inputStr, String expectResult) {
        WordFrequencyGame game = new WordFrequencyGame();
        //When
        String actual = game.getResult(inputStr);
        //Then
        assertEquals(expectResult, actual);
    }
}
