package ru.stqa.training.sandbox;

public class Point {

    //attributes of the Point class
    public double x;
    public double y;

    //Point object with two attributes
    public Point(double x, double y) {
        //attribute (with a link to an object) and variable (declared as an argument of a function)
        this.x = x;
        this.y = y;
    }

    //function returning distance b/w Point1 and Point2
    public double distance(Point p2) {
        return Math.sqrt((p2.x - this.x) * (p2.x - this.x) + (p2.y - this.y) * (p2.y - this.y));
    }
}