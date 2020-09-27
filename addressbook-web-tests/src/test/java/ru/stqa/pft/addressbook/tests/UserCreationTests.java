package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.Users;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validUsers() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/users.csv")));
        String line = reader.readLine();
        while (line != null) {
            String[] split = line.split(";");
            list.add(new Object[] {new UserData().withFirstName(split[0])
                    .withLastName(split[1])
                    .withHomeNumber(split[2])
                    .withEmail(split[3])
                    .withAddress(split[4])
                    .withGroup(split[5])});
            line = reader.readLine();
        }
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
