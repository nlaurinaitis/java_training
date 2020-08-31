package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class UserDeletionTests extends TestBase {
    @Test
    public void testUserDeletion() throws Exception {
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().selectUser();
        app.getContactHelper().deleteSelectedUser();
        app.getNavigationHelper().closeAlert();
    }
}