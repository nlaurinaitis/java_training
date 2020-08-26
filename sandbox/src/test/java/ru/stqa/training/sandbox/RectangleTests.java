package ru.stqa.training.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class RectangleTests {
    @Test
    public void testArea() {
        Rectangle s = new Rectangle(5, 6);
        Assert.assertEquals(s.area(), 30.0);
    }
}
