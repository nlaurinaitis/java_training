package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.List;

public class UserCreationTests extends TestBase {

  @Test
  public void testUserCreation() throws Exception {
    List<UserData> before = app.getContactHelper().getUserList();
    app.getNavigationHelper().gotoAddNewPage();
    app.getContactHelper().createUser(new UserData("Kitty", "Cat", "meow", "3472737", "kittycat@test.com", "test1"));
    List<UserData> after = app.getContactHelper().getUserList();
    Assert.assertEquals(after.size(), before.size() + 1);
  }
}
