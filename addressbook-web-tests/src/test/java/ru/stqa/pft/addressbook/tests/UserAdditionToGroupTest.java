package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasItem;

public class UserAdditionToGroupTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions () {
        if (app.db().users().size() == 0) {
            app.goTo().addNew();
            app.user().create(new UserData().withFirstName("Kitty").withLastName("Cat").withNickname("meow").withHomeNumber("3472737")
                            .withEmail("kittycat@test")
            );
        }

        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1"));
        }
        //add one more precond to check if the user is in all possible groups, if yes then create another group
    }

    @Test
    public void testUserAdditionToGroups() throws Exception {
        UserData userToAdd = app.db().users().iterator().next();
        GroupData groupToAdd = app.db().groups().iterator().next();
        if (userToAdd.getGroups().contains(groupToAdd)) {
            app.goTo().homePage();
            app.user().removeFromGroup(userToAdd, groupToAdd);
            app.goTo().homePage();
            app.user().selectAllGroupsOnHomePage();
        }
        app.user().addToGroup(userToAdd, groupToAdd);
        int userId = userToAdd.getId();
        UserData addedUser = app.db().usersById(userId).iterator().next();
        assertThat(addedUser.getGroups(), hasItem(groupToAdd));
    }
}

