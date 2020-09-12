package ru.stqa.pft.addressbook.tests;

        import org.testng.Assert;
        import org.testng.annotations.Test;
        import ru.stqa.pft.addressbook.model.UserData;

public class UserDeletionTests extends TestBase {
    @Test
    public void testUserDeletion() throws Exception {
        app.getNavigationHelper().gotoHomePage();
        if (! app.getContactHelper().isThereAUser()) {
            app.getNavigationHelper().gotoAddNewPage();
            app.getContactHelper().createUser(new UserData("Kitty", "Cat", "meow", "3472737", "kittycat@test.com", "test1"));
        }
        int before = app.getContactHelper().getUserCount();
        app.getContactHelper().selectUser();
        app.getContactHelper().deleteSelectedUser();
        app.getNavigationHelper().closeAlert();
        app.getNavigationHelper().gotoHomePage();
        int after = app.getContactHelper().getUserCount();
        Assert.assertEquals(after, before - 1);
    }
}