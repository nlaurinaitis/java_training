package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.Users;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions () {
        app.goTo().homePage();
        if (app.db().users().size() == 0) {
            app.goTo().addNew();
            app.user().create(new UserData().withFirstName("Kitty").withLastName("Cat").withAddress("UK, London, Privet Drive, 4")
                    .withNickname("meow").withHomeNumber("3472737").withMobNumber("89212343434").withWorkNumber("1420978")
                    .withEmail("kittycat@test").withGroup("test1"));
        }
    }

    @Test
    public void testUserModification() throws Exception {
        Users before = app.db().users();
        UserData modifiedUser = before.iterator().next();
        File photo = new File("src/test/resources/goose.jpg");
        UserData user =
                new UserData().withId(modifiedUser.getId()).withFirstName("Kitty3").withLastName("Cat3").withNickname("meow").withHomeNumber("3472737")
                        .withEmail("kittycat@test").withPhoto(photo);
        app.user().modify(user);
        assertThat(app.user().count(), equalTo(before.size()));
        Users after = app.db().users();
        assertThat(after, equalTo(before.without(modifiedUser).withAdded(user)));
    }
}
