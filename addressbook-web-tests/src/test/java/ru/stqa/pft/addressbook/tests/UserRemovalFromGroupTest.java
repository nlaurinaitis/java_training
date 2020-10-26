package ru.stqa.pft.addressbook.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.Users;

import java.io.File;
import java.util.Iterator;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserRemovalFromGroupTest extends TestBase {

    public UserData userToRemove;
    public GroupData groupToRemove;

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1"));
        }

        File photo = new File("src/test/resources/goose.jpg");
        if (app.db().users().size() == 0) {
            app.goTo().addNew();
            app.user().create(new UserData().withFirstName("Kitty").withLastName("Cat").withNickname("meow").withHomeNumber("3472737")
                    .withEmail("kittycat@test").withPhoto(photo).inGroup(app.db().groups().iterator().next())
            );
        }

        Users allUsers = app.db().users();

        userToRemove = null;
        Iterator<UserData> i = allUsers.iterator();
        while (i.hasNext()) {
            UserData u = i.next();
            if (u.getGroups().size() > 0) {
                userToRemove = u;
                groupToRemove = userToRemove.getGroups().iterator().next();
                break;
            }
        }
        if (userToRemove == null) {
            userToRemove = allUsers.iterator().next();
            groupToRemove = app.db().groups().iterator().next();
            app.user().addToGroup(userToRemove, groupToRemove);
        }
    }


    @Test
    public void testUserRemovalFromGroup() {
        app.goTo().homePage();
        app.user().removeFromGroup(userToRemove, groupToRemove);
        int userId = userToRemove.getId();
        UserData removedUser = app.db().usersById(userId).iterator().next();
        assertThat(removedUser.getGroups(), Matchers.not(hasItem(groupToRemove)));
    }
}
