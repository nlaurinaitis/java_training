package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.model.UserData;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

public class UserRemovalFromGroupTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions () {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1"));
        }

        if (app.db().users().size() == 0) {
            Groups groups = app.db().groups();
            app.goTo().addNew();
            app.user().create(new UserData().withFirstName("Kitty").withLastName("Cat").withNickname("meow").withHomeNumber("3472737")
                    .withEmail("kittycat@test").inGroup(groups.iterator().next())
            );
        }
    }

    @Test
    public void testUserRemovalFromGroup() {
        Groups groups = app.db().groups();
        UserData userToRemove = app.db().users().iterator().next();
        if (userToRemove.getGroups().size() == 0) {
            userToRemove.inGroup(groups.iterator().next());
        }
        GroupData groupToRemove = userToRemove.getGroups().iterator().next();
        int groupId = groupToRemove.getId();
        GroupData groupToRemoveWithId = app.db().groupsById(groupId).iterator().next();
        app.goTo().homePage();
        app.user().removeFromGroup(userToRemove);
        int userId = userToRemove.getId();
        UserData removedUser = app.db().usersById(userId).iterator().next();
        assertThat(removedUser.getGroups(), contains(not(groupToRemoveWithId)));
    }
}
