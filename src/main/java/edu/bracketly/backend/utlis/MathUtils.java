package edu.bracketly.backend.utlis;

import static java.lang.Math.*;

public final class MathUtils {
    private MathUtils() {}

    public static int lessOrEqualPowerOfTwo(int number) {
        int result = 1;
        while (true) {
            int pow = result << 1;
            if (pow > number) {
               break;
            }
            result = pow;
        }
        return result;
    }

    public static int nextGreaterPowerOfTwo(int number) {
        int position = (int) ceil(log(number)/ log(2));
        return (int) pow(2, position);
    }

    public static double log2(int x) {
        return log(x)/log(2);
    }
}
