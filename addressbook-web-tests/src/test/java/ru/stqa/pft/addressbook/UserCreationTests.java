package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class UserCreationTests extends TestBase {

  @Test
  public void testUserCreation() throws Exception {
    gotoAddNewPage();
    fillUserForm(new UserData("Kitty", "Cat", "meow", "3472737", "kittycat@test.com"));
    submitUserCreation();
    returnToHomePage();
  }

}
