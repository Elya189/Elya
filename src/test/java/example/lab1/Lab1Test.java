package example.lab1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Lab1Test {

    @Test
    void from10to2() {
        assertEquals("100100101", Lab1.from10to2(293));

        assertEquals("1001110001000", Lab1.from10to2(5000));

    }

    @Test
    void from2to10() {
        assertEquals(293, Lab1.from2to10("100100101"));

        assertEquals(5000, Lab1.from2to10("1001110001000"));
    }
}