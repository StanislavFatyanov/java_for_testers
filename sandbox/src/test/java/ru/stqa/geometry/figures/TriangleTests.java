package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {

    @Test
    void canCalculatePerimeter(){
        var s = new Triangle(3.0, 4.0, 5.0);
        double result = s.trianglePerimeter();
        Assertions.assertEquals(12, result);
    }

    @Test
    void canCalculateArea(){
        Assertions.assertEquals(6.0, new Triangle(3.0, 4.0, 5.0).triangleArea());
    }
}
