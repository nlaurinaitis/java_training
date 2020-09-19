package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.Users;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class UserCreationTests extends TestBase {

    @Test
    public void testUserCreation() throws Exception {
        app.goTo().homePage();
        Users before = app.user().all();
        app.goTo().addNew();
        UserData user =
                new UserData().withFirstName("Harry").withLastName("Potter").withNickname("meow1").withHomeNumber(
                        "1234567").withEmail("hp@test.com").withGroup("test1");
        app.user().create(user);
        Users after = app.user().all();

        assertEquals(after.size(), before.size() + 1);
        assertThat(after, equalTo(before.withAdded(user.withId(after.stream().mapToInt((u) -> u.getId()).max().getAsInt()))));
    }
}
