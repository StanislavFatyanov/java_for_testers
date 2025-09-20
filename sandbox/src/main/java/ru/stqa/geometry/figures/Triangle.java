package ru.stqa.geometry.figures;

public record Triangle(double a, double b, double c) {

    public Triangle{
        if (a < 0 || b < 0 || c < 0){
            throw new IllegalArgumentException("Сторона треугольника не должна быть отрицательной");
        }
        if ((a + b) < c || (a + c) < b || (b + c) < a){
            throw new IllegalArgumentException("Cумма двух любых сторон должна быть не меньше третьей стороны");
        }
    }


    public static void printTriangePerimeter(Triangle s) {
        var text = String.format("Периметр треугольника со сторонами %f и %f и %f = %f", s.a, s.b, s.c, s.trianglePerimeter());
        System.out.println(text);
    }

    public double trianglePerimeter() {
        return this.a + this.b + this.c;
    }

    public static void printTriangeArea(Triangle s) {
        var text = String.format("Площадь треугольника со сторонами %f и %f и %f = %f", s.a, s.b, s.c, s.triangleArea());
        System.out.println(text);
    }

    public double triangleArea() {
        double s = trianglePerimeter() / 2;
        return Math.sqrt(s * (s - this.a) * (s - this.b) * (s - this.c));
    }
}