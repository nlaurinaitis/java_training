package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.HashSet;
import java.util.List;

public class UserCreationTests extends TestBase {

  @Test
  public void testUserCreation() throws Exception {
    List<UserData> before = app.getContactHelper().getUserList();
    app.getNavigationHelper().gotoAddNewPage();
    UserData user = new UserData("Kitty", "Cat", "meow", "3472737", "kittycat@test.com", "test1");
    app.getContactHelper().createUser(user);
    List<UserData> after = app.getContactHelper().getUserList();
    Assert.assertEquals(after.size(), before.size() + 1);

    int max = 0;
    for (UserData u : after) {
      if (u.getId() > max) {
        max = u.getId();
      }
    }
    user.setId(max);
    before.add(user);
    Assert.assertEquals (new HashSet<Object>(before), new HashSet<Object>(after));
  }
}
