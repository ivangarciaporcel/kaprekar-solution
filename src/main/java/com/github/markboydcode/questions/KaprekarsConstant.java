package com.github.markboydcode.questions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Calculates for any four digit number the number of iterations it takes to arrive at Kaprekar's constant of 6174 by
 * the following steps:
 *
 * 1) Take any four digit number (whose digits are not all identical).
 * 2) Rearrange the string of digits to form the largest and smallest 4-digit numbers possible.
 * 3) Take these two numbers and subtract the smaller number from the larger.
 * 4) Use the number you obtain and repeat the above process until you arrive at Kaprekar's constant of 6174.
 * 5) Return the count of the number of iterations it took to arrive at the constant.
 *
 * @author boydmr
 */
public class KaprekarsConstant {
    static final String TOO_LOW = "the value is less than or equal to zero";
    static final String TOO_HIGH = "the value is greater than 9999";
    static final String NO_VARIANCE = "the value contains digits that are all identical";

    private static final int THE_CONSTANT = 6174;
    private static final int DIGITS_NUMBER = 4;

    /**
     * @param fourDigitNumber the number to be tested
     * @return the number of iterations to arrive at Kaprekar's constant.
     * @throws IllegalArgumentException with one of three messages: "the value is greater than 9999",
     *                                  "the value is less than or equal to zero", or "the value contains digits that are all identical".
     */
    public int calculateIterations(int fourDigitNumber) {
        if (fourDigitNumber <= 0) {
            throw new IllegalArgumentException(TOO_LOW);
        } else if (fourDigitNumber > 9999) {
            throw new IllegalArgumentException(TOO_HIGH);
        }

        // your implementation here
        int actualNumber = fourDigitNumber;
        int nIterations = 0;
        while (actualNumber != THE_CONSTANT) {
            List<Integer> numberAsList = asList(actualNumber);
            Collections.sort(numberAsList);
            int smallest = getSmallest(numberAsList);
            int largest = getLargest(numberAsList);
            actualNumber = largest - smallest;
            nIterations++;
        }
        return nIterations;
    }

    private List<Integer> asList(int number) {
        List<Integer> list = new ArrayList<>();
        int n = number;
        while (n != 0) {
            list.add(n % 10);
            n = n / 10;
        }
        for (int i = 1; i <= DIGITS_NUMBER - list.size(); i++) {
            list.add(0);
        }
        return list;
    }

    private int getSmallest(List<Integer> list) {
        int result = 0;
        for (Integer integer : list) {
            result = 10 * result + integer;
        }
        return result;
    }

    private int getLargest(List<Integer> list) {
        int result = 0;
        for (int i = list.size() - 1; i >= 0; i--) {
            result = 10 * result + list.get(i);
        }
        return result;
    }
}
