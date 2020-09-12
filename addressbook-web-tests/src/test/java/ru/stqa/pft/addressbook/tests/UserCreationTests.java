package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

public class UserCreationTests extends TestBase {

  @Test
  public void testUserCreation() throws Exception {
    int before = app.getContactHelper().getUserCount();
    app.getNavigationHelper().gotoAddNewPage();
    app.getContactHelper().createUser(new UserData("Kitty", "Cat", "meow", "3472737", "kittycat@test.com", "test1"));
    int after = app.getContactHelper().getUserCount();
    Assert.assertEquals(after, before + 1);
  }
}
