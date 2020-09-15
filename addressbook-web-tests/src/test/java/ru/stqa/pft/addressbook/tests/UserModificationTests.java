package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.HashSet;
import java.util.List;

public class UserModificationTests extends TestBase {
    @Test
    public void testUserModification() throws Exception {
        app.getNavigationHelper().gotoHomePage();
        if (! app.getContactHelper().isThereAUser()) {
            app.getNavigationHelper().gotoAddNewPage();
            app.getContactHelper().createUser(new UserData("Kitty", "Cat", "meow", "3472737", "kittycat@test.com", "test1"));
        }
        List<UserData> before = app.getContactHelper().getUserList();
        app.getContactHelper().initUserModification(before.size() - 1);
        UserData user = new UserData(before.get(before.size() - 1).getId(), "Kitty1", "Cat1", "meow1", "3472737",
                "kittycat1" +
                "@test.com", null);
        app.getContactHelper().fillUserForm(user, false);
        app.getContactHelper().submitUserModification();
        app.getContactHelper().returnToHomePage();
        List<UserData> after = app.getContactHelper().getUserList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(user);
        Assert.assertEquals (new HashSet<Object>(before), new HashSet<Object>(after));
    }
}
