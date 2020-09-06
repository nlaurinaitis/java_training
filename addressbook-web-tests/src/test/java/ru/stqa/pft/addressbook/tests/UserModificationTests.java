package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

public class UserModificationTests extends TestBase {
    @Test
    public void testUserModification() throws Exception {
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().initUserModification();
        app.getContactHelper().fillUserForm(new UserData("Kitty1", "Cat1", "meow1", "3472737", "kittycat1@test.com", null), false);
        app.getContactHelper().submitUserModification();
        app.getContactHelper().returnToHomePage();
    }
}
