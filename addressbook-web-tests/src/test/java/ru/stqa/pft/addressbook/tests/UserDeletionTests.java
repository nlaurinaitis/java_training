package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.Users;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions () {
        if (app.db().users().size() == 0) {
            app.goTo().addNew();
            app.user().create(new UserData().withFirstName("Kitty").withLastName("Cat").withNickname("meow").withHomeNumber("3472737")
                    .withEmail("kittycat@test")
//                    .withGroup("test1")
            );
        }
    }

    @Test
    public void testUserDeletion() throws Exception {
        Users before = app.db().users();
        UserData deletedUser = before.iterator().next();
        app.user().delete(deletedUser);
        app.goTo().homePage();
        assertThat(app.user().count(), equalTo(before.size() - 1));
        Users after = app.db().users();
        assertThat(after, equalTo(before.without(deletedUser)));
        verifyUserListInUI();
    }
}