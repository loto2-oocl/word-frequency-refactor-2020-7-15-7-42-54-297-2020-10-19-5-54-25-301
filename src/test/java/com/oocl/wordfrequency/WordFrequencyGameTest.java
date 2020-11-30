package com.oocl.wordfrequency;

import com.oocl.wordfrequency.exception.CalculateErrorException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class WordFrequencyGameTest {

    @Test
    void should_return_the_1_when_get_result_given_sentence_the() throws CalculateErrorException {
        //Given
        String sentence = "the";

        //When
        String actual = new WordFrequencyGame().getResult(sentence);

        //Then
        assertEquals("the 1", actual);
    }

    @Test
    void should_return_the_1_is_1_when_get_result_given_the_is() throws CalculateErrorException {
        //Given
        String sentence = "the is";

        //When
        String actual = new WordFrequencyGame().getResult(sentence);

        //Then
        assertEquals("the 1\nis 1", actual);
    }

    @Test
    void should_return_the_1_is_1_when_get_result_given_the_is_with_special_spaces() throws CalculateErrorException {
        //Given

        String sentence = "the      is";
        //When
        String actual = new WordFrequencyGame().getResult(sentence);

        //Then
        assertEquals("the 1\nis 1", actual);
    }

    @Test
    void should_return_the_1_is_1_when_get_result_given_the_is_with_special_enter() throws CalculateErrorException {
        //Given
        String sentence = "the   \n   is";

        //When
        String actual = new WordFrequencyGame().getResult(sentence);

        //Then
        assertEquals("the 1\nis 1", actual);
    }

    @Test
    void should_return_the_2_is_1_when_get_result_given_the_the_is() throws CalculateErrorException {
        //Given
        String sentence = "the the is";

        //When
        String actual = new WordFrequencyGame().getResult(sentence);

        //Then
        assertEquals("the 2\nis 1", actual);
    }

    @Test
    void should_return_is_2_the_1_which_sort_with_count_descending_when_get_result_given_the_is_is() throws CalculateErrorException {
        //Given
        String sentence = "the is is";

        //When
        String actual = new WordFrequencyGame().getResult(sentence);

        //Then
        assertEquals("is 2\nthe 1", actual);
    }

    @Test
    void should_throw_calculate_error_exception_when_get_result_given_a_word_frequency_game_with_error() throws CalculateErrorException {
        //Given
        WordFrequencyGame game = Mockito.mock(WordFrequencyGame.class);
        when(game.calculateWordFrequency(Mockito.anyString())).thenThrow(NullPointerException.class);
        when(game.getResult(Mockito.anyString())).thenCallRealMethod();

        //Then
        assertThrows(
            CalculateErrorException.class,
            () -> {
                //When
                game.getResult(Mockito.anyString());
            },
            "Calculate Error"
        );
    }
}
