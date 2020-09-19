package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.Comparator;
import java.util.List;

public class UserModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions () {
        app.getNavigationHelper().gotoHomePage();
        if (!app.getContactHelper().isThereAUser()) {
            app.getNavigationHelper().gotoAddNewPage();
            app.getContactHelper().createUser(new UserData("Kitty", "Cat", "meow", "3472737", "kittycat@test.com", "test1"));
        }
    }

    @Test
    public void testUserModification() throws Exception {
        List<UserData> before = app.getContactHelper().getUserList();
        int index = before.size() - 1;
        UserData user = new UserData(before.get(index).getId(), "Kitty1", "Cat1", "meow1", "3472737","kittycat1@test.com", null);
        app.getContactHelper().modifyUser(index, user);
        List<UserData> after = app.getContactHelper().getUserList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(user);
        Comparator<? super UserData> byId = (u1, u2) -> Integer.compare(u1.getId(), u2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
