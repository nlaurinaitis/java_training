package ru.stqa.training.sandbox;

public class Cat {
    public static void main(String[] args) {
        meow("world");
        meow("user");
        meow("Anastasiia");

        double l = 5;
        System.out.println("Площадь квадрата со стороной " + l + " равна " + area(l) + ".");

        double a = 4;
        double b = 6;
        System.out.println("Площадь прямоугольника со сторонами " + a + " и " + b + " равна " + area(a, b) + ".");

    }

    public static void meow(String somebody) {
        System.out.println("Meow, " + somebody + "!");
    }

    public static double area(double len) {
        return len * len;
    }

    public static double area(double a, double b) {
        return a * b;
    }
}