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

    public void manageUsersPage() {
        click(By.cssSelector("a[href='/mantisbt-2.24.2/manage_overview_page.php']"));
        click(By.cssSelector("a[href='/mantisbt-2.24.2/manage_user_page.php']"));
    }
}
