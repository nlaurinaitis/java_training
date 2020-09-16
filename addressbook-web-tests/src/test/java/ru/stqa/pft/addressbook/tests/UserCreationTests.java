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
    UserData user = new UserData("Harry", "Potter", "meow1", "1234567", "hp@test.com", "test2");
    app.getContactHelper().createUser(user);
    List<UserData> after = app.getContactHelper().getUserList();
    Assert.assertEquals(after.size(), before.size() + 1);

    user.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    before.add(user);
    Assert.assertEquals (new HashSet<Object>(before), new HashSet<Object>(after));
  }
}
