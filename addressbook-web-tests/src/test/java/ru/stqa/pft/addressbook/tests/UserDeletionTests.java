package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.List;

public class UserDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions () {
        app.getNavigationHelper().gotoHomePage();
        if (!app.getContactHelper().isThereAUser()) {
            app.getNavigationHelper().gotoAddNewPage();
            app.getContactHelper().createUser(new UserData("Kitty", "Cat", "meow", "3472737", "kittycat@test.com", "test1"));
        }
    }

    @Test
    public void testUserDeletion() throws Exception {
        List<UserData> before = app.getContactHelper().getUserList();
        app.getContactHelper().selectUser(before.size() - 1);
        app.getContactHelper().deleteSelectedUser();
        app.getNavigationHelper().closeAlert();
        app.getNavigationHelper().gotoHomePage();
        List<UserData> after = app.getContactHelper().getUserList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);

    }
}