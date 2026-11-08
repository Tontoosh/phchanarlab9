package mn.edu.must.sqat;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ScholarshipCalculatorTest {

    private final ScholarshipCalculator calculator = new ScholarshipCalculator();

    // Хэсэг A: хэвийн урсгалын таван тест.
    @Test
    void letterGradeReturnsAFor95() {
        assertEquals("A", calculator.letterGrade(95));
    }

    @Test
    void gpaAveragesTwoGrades() {
        assertEquals(3.5, calculator.gpa(new double[]{95, 85}), 1e-9);
    }

    @Test
    void fullScholarshipForHighGpa() {
        assertEquals(100, calculator.scholarship(3.9, 16, false));
    }

    @Test
    void partialScholarshipForGoodGpa() {
        assertEquals(70, calculator.scholarship(3.5, 12, false));
    }

    @Test
    void basicScholarshipForEligibleGpa() {
        assertEquals(50, calculator.scholarship(3.1, 10, false));
    }
}
