package edu.bracketly.backend.model;

import edu.bracketly.backend.utlis.MathUtils;
import org.junit.Test;

import java.util.List;
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

        List<Player> players = IntStream.range(1,17).mapToObj(p -> new Player(1L,"", false)).collect(Collectors.toList());

        DoubleEliminationBracket bracket = new DoubleEliminationBracket(players);
        System.out.println("shit");
    }
}
