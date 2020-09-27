package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.Users;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validUsers() {
        List<Object[]> list = new ArrayList<Object[]>();
        list.add (new Object[] {new UserData().withFirstName("Harry1").withLastName("Potter1").withHomeNumber(
                "3472731").withEmail("hp1@test.com").withAddress("UK, London, Privet Drive, 1").withGroup("test1")});
        list.add (new Object[] {new UserData().withFirstName("Harry2").withLastName("Potter2").withHomeNumber(
                "3472732").withEmail("hp2@test.com").withAddress("UK, London, Privet Drive, 2").withGroup("test2")});
        list.add (new Object[] {new UserData().withFirstName("Harry3").withLastName("Potter3").withHomeNumber(
                "3472733").withEmail("hp3@test.com").withAddress("UK, London, Privet Drive, 3").withGroup("test3")});
        return list.iterator();
    }

    @Test(dataProvider = "validUsers")
    public void testUserCreation(UserData user) throws Exception {
//        File photo = new File("src/test/resources/goose.jpg");
//        UserData user =
//                new UserData().withFirstName("Harry").withLastName("Potter").withNickname("meow1").withAddress("UK, London, Privet Drive, 4")
//                        .withHomeNumber("1234567").withMobNumber("89212343434").withWorkNumber("1420908")
//                        .withEmail("hp@test.com").withEmail2("hp2@test.com").withEmail3("hp3@test.com")
//                        .withPhoto(photo).withGroup("test1");
        app.goTo().homePage();
        Users before = app.user().all();
        app.goTo().addNew();
        app.user().create(user);
        assertThat(app.user().count(), equalTo(before.size() + 1));
        Users after = app.user().all();
        assertThat(after, equalTo(before.withAdded(user.withId(after.stream().mapToInt((u) -> u.getId()).max().getAsInt()))));
    }
}
