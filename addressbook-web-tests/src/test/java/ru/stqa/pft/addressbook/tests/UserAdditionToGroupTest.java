package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.Users;

import java.io.File;
import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;

public class UserAdditionToGroupTest extends TestBase {

    public UserData userToAdd;
    public GroupData groupToAdd;


    @BeforeMethod
    public void ensurePreconditions() {
        File photo = new File("src/test/resources/goose.jpg");
        if (app.db().users().size() == 0) {
            app.goTo().addNew();
            app.user().create(new UserData().withFirstName("Kitty").withLastName("Cat").withNickname("meow").withHomeNumber("3472737")
                    .withEmail("kittycat@test").withPhoto(photo)
            );
        }

        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1"));
        }


        Users allUsers = app.db().users();
        Groups allGroups = app.db().groups();

        userToAdd = null;
        Iterator<UserData> i = allUsers.iterator();
        while (i.hasNext()) {
            UserData u = i.next();
            if (u.getGroups().size() < allGroups.size()) {
                userToAdd = u;
                GroupData selectedGroup = app.user().findUniqueGroup(userToAdd, allGroups);
                groupToAdd = selectedGroup;
                break;
            }
        }
        if (userToAdd == null) {
            app.goTo().groupPage();
            GroupData newGroup = new GroupData().withName("NewGroup").withHeader("NewHeader").withFooter("NewFooter");
            app.group().create(newGroup);
            Groups allGroupsAfter = app.db().groups();
            groupToAdd = newGroup.withId(allGroupsAfter.stream().mapToInt((g) -> g.getId()).max().getAsInt());
            userToAdd = allUsers.iterator().next();

        }
    }


    @Test
    public void testUserAdditionToGroups() throws Exception {
        app.goTo().homePage();
        app.user().addToGroup(userToAdd, groupToAdd);
        int userId = userToAdd.getId();
        UserData addedUser = app.db().usersById(userId).iterator().next();
        assertThat(addedUser.getGroups(), hasItem(groupToAdd));
    }


}


