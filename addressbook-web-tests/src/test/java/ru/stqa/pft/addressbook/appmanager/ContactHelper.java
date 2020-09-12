package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.UserData;

public class ContactHelper extends BaseHelper {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void fillUserForm(UserData userData, boolean creation) {
        type(By.name("firstname"), userData.getFirstName());
        type(By.name("lastname"), userData.getLastName());
        type(By.name("nickname"), userData.getNickname());
        type(By.name("home"), userData.getHomeNumber());
        type(By.name("email"), userData.getEmail());

        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(userData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }


    public void submitUserCreation() {
        click(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void returnToHomePage() {
        click(By.linkText("home page"));
    }

    public void selectUser(int index) {
        wd.findElements(By.name("selected[]")).get (index).click();
    }

    public void deleteSelectedUser() {
        click(By.xpath("(//input[@value='Delete'])"));
    }

    public void initUserModification(int index) {
        wd.findElements(By.xpath("//img[@alt='Edit']")).get (index).click();
    }

    public void submitUserModification() {
        click(By.xpath("(//input[@name='update'])[2]"));
    }

    public void createUser(UserData user) {
        fillUserForm(user, true);
        submitUserCreation();
        returnToHomePage();
    }

    public boolean isThereAUser() {
      return isElementPresent(By.name("selected[]"));
    }

    public int getUserCount() {
        return wd.findElements(By.name("selected[]")).size();
    }
}
