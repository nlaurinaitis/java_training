package ru.stqa.training.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {
    @Test
    public void testDistance() {
        Point p1 = new Point(1, 2);
        Point p2 = new Point(3, 2);
        Assert.assertEquals(p1.distance(p2), 2.0);
    }

    @Test
    public void testDistance1() {
        Point p1 = new Point(-2, 1);
        Point p2 = new Point(7, 1);
        Assert.assertEquals(p1.distance(p2), 9.0);
    }

    @Test
    public void testDistance2() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(-2, -7);
        Assert.assertEquals(p1.distance(p2), 7.280109889280518);
    }
}
