package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class PasswordChangeTests extends TestBase {

        @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }


    public void ensurePreconditions() throws IOException, MessagingException {
        long now = System.currentTimeMillis();
        String user = String.format("user%s", now);
        String password = "password";
        String email = String.format("user%s@localhost.localdomain", now);
        app.registration().start(user, email);
        List<MailMessage> mailMessages = app.mail().waitForMail(2, 20000);
        String confirmationLink = findConfirmationLinkPrecond(mailMessages, email);
        app.registration().finish(confirmationLink, password);
        app.goTo().logout();
    }

    @Test
    public void testPasswordChange() throws IOException, MessagingException {
        UserData user = app.db().users().iterator().next();
        String username = user.getUsername();
        String email = user.getEmail();
        String newPassword = "password1";;
        app.ui().uiLogin("administrator", "root");
        app.goTo().manageUsersPage();


    }

    private String findConfirmationLinkPrecond(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

        @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}



