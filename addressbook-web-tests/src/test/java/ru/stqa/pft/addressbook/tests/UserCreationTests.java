package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

public class UserCreationTests extends TestBase {

  @Test
  public void testUserCreation() throws Exception {
    app.gotoAddNewPage();
    app.fillUserForm(new UserData("Kitty", "Cat", "meow", "3472737", "kittycat@test.com"));
    app.submitUserCreation();
    app.returnToHomePage();
  }

}
