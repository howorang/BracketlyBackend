package edu.bracketly.backend.model;

import edu.bracketly.backend.model.bracket.DoubleEliminationBracket;
import edu.bracketly.backend.model.bracket.Player;
import edu.bracketly.backend.utlis.MathUtils;
import org.junit.Test;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class DoubleEliminationBracketTests {

    @Test
    public void lessOrEqualPowerOfTwoTest() {
        int number = 9;
        int expected = 8;

        int result = MathUtils.lessOrEqualPowerOfTwo(number);

        assertEquals(expected, result);
    }

    @Test
    public void smallestGreaterPowerOfTwo() {
        int number = 27;
        int expected = 32;

        int result = MathUtils.nextGreaterPowerOfTwo(number);
        assertEquals(expected, result);
    }

    @Test
    public void smallestGreaterPowerOfTwoWhenEqual() {
        int number = 32;
        int expected = 32;

        int result = MathUtils.nextGreaterPowerOfTwo(number);
        assertEquals(expected, result);
    }

    @Test
    public void perfectBracketGenerationTest() {

        DoubleEliminationBracket bracket = new DoubleEliminationBracket(16);
        System.out.println("done");
    }
}
