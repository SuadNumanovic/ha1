package htw.berlin.prog2.ha1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Retro calculator")
class CalculatorTest {

    @Test
    @DisplayName("should display result after adding two positive multi-digit numbers")
    void testPositiveAddition() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(2);
        calc.pressDigitKey(0);
        calc.pressBinaryOperationKey("+");
        calc.pressDigitKey(2);
        calc.pressDigitKey(0);
        calc.pressEqualsKey();

        String expected = "40";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should display result after getting the square root of two")
    void testSquareRoot() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(2);
        calc.pressUnaryOperationKey("√");

        String expected = "1.41421356";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should display error when dividing by zero")
    void testDivisionByZero() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(7);
        calc.pressBinaryOperationKey("/");
        calc.pressDigitKey(0);
        calc.pressEqualsKey();

        String expected = "Error";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should display error when drawing the square root of a negative number")
    void testSquareRootOfNegative() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(7);
        calc.pressNegativeKey();
        calc.pressUnaryOperationKey("√");

        String expected = "Error";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should not allow multiple decimal dots")
    void testMultipleDecimalDots() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(1);
        calc.pressDotKey();
        calc.pressDigitKey(7);
        calc.pressDotKey();
        calc.pressDigitKey(8);

        String expected = "1.78";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }


    //TODO hier weitere Tests erstellen
    @Test
    @DisplayName("should display result after subtracting two positive multi-digit numbers")
    void testPositiveSubtraction() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(2);
        calc.pressDigitKey(0);              // 20
        calc.pressBinaryOperationKey("-");
        calc.pressDigitKey(1);
        calc.pressDigitKey(5);              // 15
        calc.pressEqualsKey();              // 20 - 15 = 5

        String expected = "5";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }
    @Test
    @DisplayName("should follow multiplication before addition")
    void testOperatorPrecedence() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(2);              // 2
        calc.pressBinaryOperationKey("+");  // +
        calc.pressDigitKey(3);              // 3
        calc.pressBinaryOperationKey("x");  // x
        calc.pressDigitKey(4);              // 4
        calc.pressEqualsKey();              // =

        String expected = "14";             // Weil: 3 x 4 = 12, dann 2 + 12 = 14
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }
    @Test
    @DisplayName("should repeat the last operation when equals is pressed multiple times")
    void testRepeatEqualsKey() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(2);              // 2
        calc.pressBinaryOperationKey("+");  // +
        calc.pressDigitKey(3);              // 3
        calc.pressEqualsKey();              // = → 5
        calc.pressEqualsKey();              // = → 8 (5 + 3)
        calc.pressEqualsKey();              // = → 11 (8 + 3)

        String expected = "11";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }
    @Test
    @DisplayName("should display Error when inverting zero")
    void testInverseOfZero() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(0);              // Bildschirm: 0
        calc.pressUnaryOperationKey("1/x"); // Rechnet 1 / 0 → sollte "Error" sein

        String expected = "Error";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

}


