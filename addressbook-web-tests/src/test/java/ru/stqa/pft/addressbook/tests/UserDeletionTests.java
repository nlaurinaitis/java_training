package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.List;

public class UserDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions () {
        app.goTo().homePage();
        if (app.user().list().size() == 0) {
            app.goTo().addNew();
            app.user().create(new UserData().withFirstName("Kitty").withLastName("Cat").withNickname("meow").withHomeNumber("3472737")
                    .withEmail("kittycat@test").withGroup("test1"));
        }
    }

    @Test
    public void testUserDeletion() throws Exception {
        List<UserData> before = app.user().list();
        int index = before.size() - 1;
        app.user().delete(index);
        app.goTo().homePage();
        List<UserData> after = app.user().list();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(index);
        Assert.assertEquals(before, after);

    }
}