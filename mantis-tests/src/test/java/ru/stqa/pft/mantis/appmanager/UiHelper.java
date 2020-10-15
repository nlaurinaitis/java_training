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

}
