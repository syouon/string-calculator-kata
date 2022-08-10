package com.exalt.company;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class StringCalculatorKataTest {

    @Test
    public void should_return_zero_when_argument_is_null() {
        // Given
        var stringCalculator = new StringCalculatorKata();

        // When
        int result = stringCalculator.add(null);

        // Then
        assertThat(result).isZero();
    }

    @Test
    public void should_return_zero_when_argument_is_blank() {
        // Given
        var stringCalculator = new StringCalculatorKata();

        // When
        int result = stringCalculator.add("  ");

        // Then
        assertThat(result).isZero();
    }

    @Test
    public void should_return_the_number_when_argument_has_only_one_number() {
        // Given
        var stringCalculator = new StringCalculatorKata();

        // When
        int result = stringCalculator.add("1");

        // Then
        assertThat(result).isEqualTo(1);
    }

    @Test
    public void should_throw_an_exception_when_argument_contains_not_only_number() {
        // Given
        var stringCalculator = new StringCalculatorKata();

        // When / Then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> stringCalculator.add("abc"));
    }

    @Test
    public void should_return_the_sum_of_the_two_numbers_separated_by_comma() {
        // Given
        var stringCalculator = new StringCalculatorKata();

        // When
        int result = stringCalculator.add("1,2");

        // Then
        assertThat(result).isEqualTo(3);
    }

    @Test
    public void should_return_the_sum_of_numbers_separated_by_comma() {
        // Given
        var stringCalculator = new StringCalculatorKata();

        // When
        int result = stringCalculator.add("1,2,3,4,5");

        // Then
        assertThat(result).isEqualTo(15);
    }

    @Test
    public void should_return_the_sum_of_numbers_separated_by_either_comma_or_new_line() {
        // Given
        var stringCalculator = new StringCalculatorKata();

        // When
        int result = stringCalculator.add("1\n2,3");

        // Then
        assertThat(result).isEqualTo(6);
    }

    @Test
    public void should_return_the_sum_of_numbers_separated_a_chosen_separator() {
        // Given
        var stringCalculator = new StringCalculatorKata();

        // When
        int result = stringCalculator.add("//;\n1;2;3");

        // Then
        assertThat(result).isEqualTo(6);
    }

    @Test
    public void should_throw_exception_when_negative_numbersç_are_provided() {
        // Given
        var stringCalculator = new StringCalculatorKata();

        // When / Then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> stringCalculator.add("//;\n-1;2;-3"))
                .withMessage("negatives not allowed: -1 -3");
    }
}