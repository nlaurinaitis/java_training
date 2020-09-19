package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.Set;

public class UserModificationTests extends TestBase {

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
    public void testUserModification() throws Exception {
        Set<UserData> before = app.user().all();
        UserData modifiedUser = before.iterator().next();
        UserData user =
                new UserData().withId(modifiedUser.getId()).withFirstName("Kitty").withLastName("Cat").withNickname("meow").withHomeNumber("3472737")
                        .withEmail("kittycat@test");
        app.user().modify(user);
        Set<UserData> after = app.user().all();
        Assert.assertEquals(after.size(), before.size());

        before.remove(modifiedUser);
        before.add(user);
        Assert.assertEquals(before, after);
    }
}
