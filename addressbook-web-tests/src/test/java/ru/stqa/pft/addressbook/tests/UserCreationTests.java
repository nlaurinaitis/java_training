package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.Users;

import java.io.File;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserCreationTests extends TestBase {

    @Test
    public void testUserCreation() throws Exception {
        app.goTo().homePage();
        Users before = app.user().all();
        File photo = new File("src/test/resources/goose.jpg");
        app.goTo().addNew();
        UserData user =
                new UserData().withFirstName("Harry").withLastName("Potter").withNickname("meow1").withAddress("UK, London, Privet Drive, 4")
                        .withHomeNumber("1234567").withMobNumber("89212343434").withWorkNumber("1420908")
                        .withEmail("hp@test.com").withEmail2("hp2@test.com").withEmail3("hp3@test.com")
                        .withPhoto(photo).withGroup("test1");
        app.user().create(user);
        assertThat(app.user().count(), equalTo(before.size() + 1));
        Users after = app.user().all();
        assertThat(after, equalTo(before.withAdded(user.withId(after.stream().mapToInt((u) -> u.getId()).max().getAsInt()))));
    }
}
