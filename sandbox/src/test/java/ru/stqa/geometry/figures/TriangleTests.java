package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {

    @Test
    void canCalculatePerimeter() {
        var s = new Triangle(3.0, 4.0, 5.0);
        double result = s.trianglePerimeter();
        Assertions.assertEquals(12, result);
    }

    @Test
    void canCalculateArea() {
        Assertions.assertEquals(6.0, new Triangle(3.0, 4.0, 5.0).triangleArea());
    }

    @Test
    void cannotCreateTriangleWithNegativeSide() {
        try {
            new Triangle(-3.0, 4.0, 5.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            // OK
        }
        try {
            new Triangle(3.0, -4.0, 5.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            // OK
        }
        try {
            new Triangle(3.0, 4.0, -5.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            // OK
        }
    }

    @Test
    void cannotCreateTriangkeWithNotValidSides(){
        try {
            new Triangle(1.0, 3.0, 5.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            // OK
        }
        try {
            new Triangle(1.0, -4.0, 2.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            // OK
        }
        try {
            new Triangle(6.0, 3.0, 2.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            // OK
        }
    }

    @Test
    void testEquality1(){
        var a = 2;
        var b = 3;
        var c = 4;
        var triangle = new Triangle(a, b, c);
        var triangle1 = new Triangle(a, b, c);
        Assertions.assertEquals(triangle, triangle1);
    }
    @Test
    void testEquality2(){
        var a = 2;
        var b = 3;
        var c = 4;
        var triangle = new Triangle(b, a, c);
        var triangle1 = new Triangle(b, c, a);
        Assertions.assertEquals(triangle, triangle1);
    }

    @Test
    void testEquality3(){
        var a = 2;
        var b = 3;
        var c = 4;
        var triangle = new Triangle(a, b, c);
        var triangle1 = new Triangle(c, a, b);
        Assertions.assertEquals(triangle, triangle1);
    }

    @Test
    void testEquality4(){
        var a = 2;
        var b = 3;
        var c = 4;
        var triangle = new Triangle(a, b, c);
        var triangle1 = new Triangle(a, c, b);
        Assertions.assertEquals(triangle, triangle1);
    }

    @Test
    void testEquality5(){
        var a = 2;
        var b = 3;
        var c = 4;
        var triangle = new Triangle(a, b, c);
        var triangle1 = new Triangle(b, a, c);
        Assertions.assertEquals(triangle, triangle1);
    }

    @Test
    void testEquality6(){
        var a = 2;
        var b = 3;
        var c = 4;
        var triangle = new Triangle(a, b, c);
        var triangle1 = new Triangle(c, b, a);
        Assertions.assertEquals(triangle, triangle1);
    }
}
