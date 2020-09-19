package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.Users;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void selectUserById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void deleteSelectedUser() {
        click(By.xpath("(//input[@value='Delete'])"));
    }

    public void initUserModificationById(int id) {
        wd.findElement(By.cssSelector("a[href='edit.php?id=" + id + "']")).click();
    }

    public void submitUserModification() {
        click(By.xpath("(//input[@name='update'])[2]"));
    }

    public void create(UserData user) {
        fillUserForm(user, true);
        submitUserCreation();
        returnToHomePage();
    }

    public void modify(UserData user) {
        initUserModificationById(user.getId());
        fillUserForm(user, false);
        submitUserModification();
        returnToHomePage();
    }

    public void delete(UserData user) {
        selectUserById(user.getId());
        deleteSelectedUser();
        closeAlert();
    }

    public boolean isThereAUser() {
      return isElementPresent(By.name("selected[]"));
    }

    public int getUserCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public Users all() {
        Users users = new Users();
        List<WebElement> elements = wd.findElements(By.cssSelector("tr[name = 'entry']"));
        for(WebElement element : elements) {
            String firstName = element.findElement(By.cssSelector("td:nth-child(3)")).getText();
            String lastName = element.findElement(By.cssSelector("td:nth-child(2)")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            users.add(new UserData().withId(id).withFirstName(firstName).withLastName(lastName));
        }
        return users;
    }

    public void closeAlert() {
        wd.switchTo().alert().accept();
    }
}
