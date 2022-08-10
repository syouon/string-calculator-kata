package com.exalt.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class StringCalculatorKata {

    private static final Pattern DELIMITER_PATTERN = Pattern.compile("^//(?<delimiter>\\D)\n(?<numberPart>.+)$");
    private static final String DEFAULT_DELIMITERS = "[,\n]";

    int add(String numbers) {
        if (numbers == null || numbers.isBlank()) {
            return 0;
        }

        String delimiter = DEFAULT_DELIMITERS;
        String numberPart = numbers;

        Matcher matcher = DELIMITER_PATTERN.matcher(numbers);
        if (matcher.matches()) {
            delimiter = matcher.group("delimiter");
            numberPart = matcher.group("numberPart");
        }

        List<Integer> numbersAsList = splitToNumbers(numberPart, delimiter);

        checkForNegativeNumbers(numbersAsList);

        return numbersAsList.stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    private List<Integer> splitToNumbers(String expression, String delimiter) {
        try {
            return Arrays.stream(expression.split(delimiter))
                    .map(Integer::parseInt)
                    .collect(toList());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Cannot convert to integer: " + e.getMessage(), e);
        }
    }

    private void checkForNegativeNumbers(Collection<Integer> numbers) {
        List<Integer> negativeNumbers = new ArrayList<>();

        numbers.stream()
                .filter(number -> number < 0)
                .forEach(negativeNumbers::add);

        if (!negativeNumbers.isEmpty()) {
            throw new IllegalArgumentException("negatives not allowed: " + negativeNumbers.stream()
                    .map(Object::toString)
                    .collect(joining(" ")));
        }
    }
}
