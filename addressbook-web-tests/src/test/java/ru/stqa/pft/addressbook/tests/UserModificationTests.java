package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

public class UserModificationTests extends TestBase {
    @Test
    public void testUserModification() throws Exception {
        app.getNavigationHelper().gotoHomePage();
        if (! app.getContactHelper().isThereAUser()) {
            app.getNavigationHelper().gotoAddNewPage();
            app.getContactHelper().createUser(new UserData("Kitty", "Cat", "meow", "3472737", "kittycat@test.com", "test1"));
        }
        app.getContactHelper().initUserModification();
        app.getContactHelper().fillUserForm(new UserData("Kitty1", "Cat1", "meow1", "3472737", "kittycat1@test.com", null), false);
        app.getContactHelper().submitUserModification();
        app.getContactHelper().returnToHomePage();
    }
}
