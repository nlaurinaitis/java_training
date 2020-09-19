package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.Set;

public class UserCreationTests extends TestBase {

    @Test
    public void testUserCreation() throws Exception {
        app.goTo().homePage();
        Set<UserData> before = app.user().all();
        app.goTo().addNew();
        UserData user =
                new UserData().withFirstName("Harry").withLastName("Potter").withNickname("meow1").withHomeNumber(
                        "1234567").withEmail("hp@test.com").withGroup("test1");
        app.user().create(user);
        Set<UserData> after = app.user().all();
        Assert.assertEquals(after.size(), before.size() + 1);

        user.withId(after.stream().mapToInt((u) -> u.getId()).max().getAsInt());
        before.add(user);
        Assert.assertEquals(before, after);
    }
}
