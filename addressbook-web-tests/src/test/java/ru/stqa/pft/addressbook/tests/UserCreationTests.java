package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.Comparator;
import java.util.List;

public class UserCreationTests extends TestBase {

    @Test
    public void testUserCreation() throws Exception {
        app.getNavigationHelper().gotoHomePage();
        List<UserData> before = app.getContactHelper().getUserList();
        app.getNavigationHelper().gotoAddNewPage();
        UserData user = new UserData("Harry", "Potter", "meow1", "1234567", "hp@test.com", "test1");
        app.getContactHelper().createUser(user);
        List<UserData> after = app.getContactHelper().getUserList();
        Assert.assertEquals(after.size(), before.size() + 1);

        Comparator<? super UserData> byId = (u1, u2) -> Integer.compare(u1.getId(), u2.getId());
        before.sort(byId);
        after.sort(byId);
        before.add(user);
        Assert.assertEquals(before, after);
    }
}
