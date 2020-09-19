package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.Set;

public class UserDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions () {
        app.goTo().homePage();
        if (app.user().all().size() == 0) {
            app.goTo().addNew();
            app.user().create(new UserData().withFirstName("Kitty").withLastName("Cat").withNickname("meow").withHomeNumber("3472737")
                    .withEmail("kittycat@test").withGroup("test1"));
        }
    }

    @Test
    public void testUserDeletion() throws Exception {
        Set<UserData> before = app.user().all();
        UserData deletedUser = before.iterator().next();
        app.user().delete(deletedUser);
        app.goTo().homePage();
        Set<UserData> after = app.user().all();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(deletedUser);
        Assert.assertEquals(before, after);

    }
}