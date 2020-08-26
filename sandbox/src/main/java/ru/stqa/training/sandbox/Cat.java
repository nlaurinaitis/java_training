package ru.stqa.training.sandbox;

public class Cat {
    public static void main(String[] args) {
/*        meow("world");
        meow("user");
        meow("Anastasiia");

        Square s = new Square(5);
        System.out.println("Площадь квадрата со стороной " + s.l + " равна " + s.area() + ".");

        Rectangle r = new Rectangle(4, 6);
        System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " равна " + r.area() + ".");*/

        Point p1 = new Point(1, 2);
        Point p2 = new Point(3, 2);
        System.out.println("Расстояние между точками с координатами " + p1.x + ";" + p1.y + " и " + p2.x + ";" + p2.y + " равняется " + p1.distance(p2) + ".");

    }

/*    public static void meow(String somebody) {
        System.out.println("Meow, " + somebody + "!");
    }*/

}