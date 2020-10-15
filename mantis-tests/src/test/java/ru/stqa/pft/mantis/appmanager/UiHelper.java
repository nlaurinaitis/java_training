package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class UiHelper extends BaseHelper {

    public UiHelper (ApplicationManager app) {
        super(app);
    }

    public void uiLogin (String username, String password) {
        wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
        type(By.name("username"), username);
        click(By.xpath("//input[@value='Login']"));
        type(By.name("password"), password);
        click(By.xpath("//input[@value='Login']"));
    }

    public void logout() {
        click(By.cssSelector("a[href='/mantisbt-2.24.2/logout_page.php']"));
    }

    public void resetPwd (int userId) {
        click(By.cssSelector("a[href='/mantisbt-2.24.2/manage_overview_page.php']"));
        click(By.cssSelector("a[href='/mantisbt-2.24.2/manage_user_page.php']"));
        click(By.cssSelector("a[href='manage_user_edit_page.php?user_id=" + userId + "']"));
        click(By.cssSelector("input[value='Reset Password']"));
    }
}
