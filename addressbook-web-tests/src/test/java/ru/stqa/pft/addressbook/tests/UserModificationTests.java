package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.Comparator;
import java.util.List;

public class UserModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions () {
        app.goTo().homePage();
        if (app.user().list().size() == 0) {
            app.goTo().addNew();
            app.user().create(new UserData("Kitty", "Cat", "meow", "3472737", "kittycat@test.com", "test1"));
        }
    }

    @Test
    public void testUserModification() throws Exception {
        List<UserData> before = app.user().list();
        int index = before.size() - 1;
        UserData user = new UserData(before.get(index).getId(), "Kitty1", "Cat1", "meow1", "3472737","kittycat1@test.com", null);
        app.user().modify(index, user);
        List<UserData> after = app.user().list();
        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(user);
        Comparator<? super UserData> byId = (u1, u2) -> Integer.compare(u1.getId(), u2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
