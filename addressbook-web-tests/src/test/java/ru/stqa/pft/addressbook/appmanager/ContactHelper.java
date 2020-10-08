package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.Users;

import java.security.acl.Group;
import java.util.List;

public class ContactHelper extends BaseHelper {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void fillUserForm(UserData userData, boolean creation) {
        type(By.name("firstname"), userData.getFirstName());
        type(By.name("lastname"), userData.getLastName());
        type(By.name("nickname"), userData.getNickname());
        type(By.name("address"), userData.getAddress());
        type(By.name("home"), userData.getHomeNumber());
        type(By.name("mobile"), userData.getMobNumber());
        type(By.name("work"), userData.getWorkNumber());
        type(By.name("email"), userData.getEmail());
        type(By.name("email2"), userData.getEmail2());
        type(By.name("email3"), userData.getEmail3());
        attach(By.name("photo"), userData.getPhoto());

        if (creation) {
            if (userData.getGroups().size() > 0) {
                Assert.assertTrue(userData.getGroups().size() == 1);
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(userData.getGroups().iterator().next().getName());
            }
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
        userCache = null;
        returnToHomePage();
    }

    public void modify(UserData user) {
        initUserModificationById(user.getId());
        fillUserForm(user, false);
        submitUserModification();
        userCache = null;
        returnToHomePage();
    }

    public void delete(UserData user) {
        selectUserById(user.getId());
        deleteSelectedUser();
        closeAlert();
        userCache = null;
    }

    public boolean isThereAUser() {
      return isElementPresent(By.name("selected[]"));
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public Users userCache = null;

    public Users all() {
        if (userCache != null) {
            return new Users (userCache);
        }
        userCache = new Users();
        List<WebElement> elements = wd.findElements(By.cssSelector("tr[name = 'entry']"));
        for(WebElement element : elements) {
            String firstName = element.findElement(By.cssSelector("td:nth-child(3)")).getText();
            String lastName = element.findElement(By.cssSelector("td:nth-child(2)")).getText();
            String address = element.findElement(By.cssSelector("td:nth-child(4)")).getText();
            String allPhones = element.findElement(By.cssSelector("td:nth-child(6)")).getText();
            String allEmails = element.findElement(By.cssSelector("td:nth-child(5)")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            userCache.add(new UserData().withId(id).withFirstName(firstName).withLastName(lastName).withAddress(address)
            .withAllPhones(allPhones).withAllEmails(allEmails));
        }
        return new Users (userCache);
    }

    public void closeAlert() {
        wd.switchTo().alert().accept();
    }

    public UserData infoFromEditForm(UserData user) {
        initUserModificationById(user.getId());
        String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String homeNumber = wd.findElement(By.name("home")).getAttribute("value");
        String mobNumber = wd.findElement(By.name("mobile")).getAttribute("value");
        String workNumber = wd.findElement(By.name("work")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        wd.navigate().back();
        return new UserData().withId(user.getId()).withFirstName(firstName).withLastName(lastName).withAddress(address)
                .withHomeNumber(homeNumber).withMobNumber(mobNumber).withWorkNumber(workNumber)
                .withEmail(email).withEmail2(email2).withEmail3(email3);
    }

    public void addToGroup(UserData user, GroupData group) {
        selectUserById(user.getId());
        addUserToGroup(user, group);
    }

    public void addUserToGroup(UserData user, GroupData group) {
        new Select(wd.findElement(By.name("to_group"))).selectByVisibleText(group.getName());
        click(By.xpath("(//input[@value='Add to'])"));
    }

    public void removeFromGroup(UserData user) {
        selectGroupOfUser(user);
        selectUserById(user.getId());
        click(By.xpath("(//input[@name='remove'])"));
    }

    public void selectGroupOfUser(UserData user) {
        new Select(wd.findElement(By.name("group"))).selectByVisibleText(user.getGroups().iterator().next().getName());
    }

    public void selectAllGroupsOnHomePage() {
        new Select(wd.findElement(By.name("group"))).selectByVisibleText("[all]");
    }
}
