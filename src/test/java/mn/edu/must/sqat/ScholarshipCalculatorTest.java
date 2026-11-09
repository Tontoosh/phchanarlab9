package mn.edu.must.sqat;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    // Хэсэг B: алдааны замууд болон үлдсэн салаанууд.
    @Test
    void letterGradeRejectsNegativeScore() {
        assertThrows(IllegalArgumentException.class, () -> calculator.letterGrade(-1));
    }

    @Test
    void letterGradeRejectsScoreAbove100() {
        assertThrows(IllegalArgumentException.class, () -> calculator.letterGrade(101));
    }

    @Test
    void gpaUsesAllLetterGrades() {
        assertEquals(2.0, calculator.gpa(new double[]{95, 85, 75, 65, 55}), 1e-9);
    }

    @Test
    void gpaRejectsNullScores() {
        assertThrows(IllegalArgumentException.class, () -> calculator.gpa(null));
    }

    @Test
    void gpaRejectsEmptyScores() {
        assertThrows(IllegalArgumentException.class, () -> calculator.gpa(new double[]{}));
    }

    @Test
    void scholarshipRejectsNegativeGpa() {
        assertThrows(IllegalArgumentException.class,
                () -> calculator.scholarship(-0.1, 15, false));
    }

    @Test
    void scholarshipRejectsGpaAboveFour() {
        assertThrows(IllegalArgumentException.class,
                () -> calculator.scholarship(4.1, 15, false));
    }

    @Test
    void scholarshipRejectsNegativeCredits() {
        assertThrows(IllegalArgumentException.class,
                () -> calculator.scholarship(3.9, -1, false));
    }

    @Test
    void scholarshipReturnsZeroForViolation() {
        assertEquals(0, calculator.scholarship(3.9, 16, true));
    }

    @Test
    void fullScholarshipRequiresEnoughCredits() {
        assertEquals(70, calculator.scholarship(3.9, 14, false));
    }

    @Test
    void partialScholarshipRequiresEnoughCredits() {
        assertEquals(50, calculator.scholarship(3.5, 11, false));
    }

    @Test
    void scholarshipReturnsZeroForLowGpa() {
        assertEquals(0, calculator.scholarship(2.9, 16, false));
    }

    // Хэсэг C: давталтыг нэг болон олон удаа гүйцэтгэх зааг.
    @Test
    void gpaAveragesSingleGrade() {
        assertEquals(4.0, calculator.gpa(new double[]{95}), 1e-9);
    }

    @Test
    void gpaAveragesSeveralGrades() {
        assertEquals(2.5, calculator.gpa(new double[]{95, 85, 75, 65}), 1e-9);
    }

    // Хэсэг D: спецификацийн зааг утгууд.
    @Test
    void letterGradeReturnsDAt60() {
        assertEquals("D", calculator.letterGrade(60));
    }

    @Test
    void letterGradeReturnsFAt59Point99() {
        assertEquals("F", calculator.letterGrade(59.99));
    }

    @Test
    void letterGradeReturnsDAt69Point99() {
        assertEquals("D", calculator.letterGrade(69.99));
    }

    @Test
    void letterGradeReturnsFAt0() {
        assertEquals("F", calculator.letterGrade(0));
    }

    @Test
    void letterGradeReturnsAAt100() {
        assertEquals("A", calculator.letterGrade(100));
    }

    @Test
    void letterGradeReturnsBAt89Point99() {
        assertEquals("B", calculator.letterGrade(89.99));
    }

    @Test
    void letterGradeReturnsAAt90() {
        assertEquals("A", calculator.letterGrade(90));
    }
}
