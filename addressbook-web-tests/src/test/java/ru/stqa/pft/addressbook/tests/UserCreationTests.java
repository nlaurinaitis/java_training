package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.Comparator;
import java.util.List;

public class UserCreationTests extends TestBase {

    @Test
    public void testUserCreation() throws Exception {
        app.goTo().homePage();
        List<UserData> before = app.user().list();
        app.goTo().addNew();
        UserData user =
                new UserData().withFirstName("Harry").withLastName("Potter").withNickname("meow1").withHomeNumber(
                        "1234567").withEmail("hp@test.com").withGroup("test1");
        app.user().create(user);
        List<UserData> after = app.user().list();
        Assert.assertEquals(after.size(), before.size() + 1);

        Comparator<? super UserData> byId = (u1, u2) -> Integer.compare(u1.getId(), u2.getId());
        before.sort(byId);
        after.sort(byId);
        before.add(user);
        Assert.assertEquals(before, after);
    }
}
