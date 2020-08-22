package ru.stqa.training.sandbox;

public class Cat {
    public static void main(String[] args) {
        meow("world");
        meow("user");
        meow("Anastasiia");

        Square s = new Square(5);
        System.out.println("Площадь квадрата со стороной " + s.l + " равна " + s.area() + ".");

        Rectangle r = new Rectangle(4, 6);
        System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " равна " + r.area() + ".");

    }

    public static void meow(String somebody) {
        System.out.println("Meow, " + somebody + "!");
    }
}