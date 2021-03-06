package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.GroupData;

public class NavigationHelper extends BaseHelper {

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void groupPage() {
        if (isElementPresent(By.tagName("h1"))
                && wd.findElement(By.tagName("h1")).getText().equals("Groups")
                && isElementPresent(By.name("new"))) {
            return;
        } else {
            click(By.linkText("groups"));
        }
    }

/*    public void gotoGroupPage() {
        if (! isElementPresent(By.tagName("h1"))
                || ! wd.findElement(By.tagName("h1")).getText().equals("Groups")
                || ! isElementPresent(By.name("new"))) {
            click(By.linkText("groups"));
        }
    }*/

    public void addNew() {
        click(By.linkText("add new"));
    }

    public void homePage() {
        if (isElementPresent(By.id("maontable"))) {
            return;
        } else {
            click(By.linkText("home"));
        }
    }
}
