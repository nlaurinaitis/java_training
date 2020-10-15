package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends BaseHelper {

    public NavigationHelper(ApplicationManager app) {
        super(app);
    }


    public void logout() {
        click(By.cssSelector("a[href='/mantisbt-2.24.2/logout_page.php']"));
    }
}
