package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.Arrays;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserEmailTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions () {
        app.goTo().homePage();
        if (app.user().all().size() == 0) {
            app.goTo().addNew();
            app.user().create(new UserData().withFirstName("Kitty").withLastName("Cat").withAddress("UK, London, Privet Drive, 4")
                    .withNickname("meow").withHomeNumber("3472737").withMobNumber("89212343434").withWorkNumber("1420978")
                    .withEmail("kittycat@test").withEmail2("kittycat2@test").withEmail3("kittycat3@test")
//                    .withGroup("test1")
            );
        }
    }

    @Test
    public void testUserEmail () {
        app.goTo().homePage();
        UserData user = app.user().all().iterator().next();
        UserData userInfoEditForm = app.user().infoFromEditForm(user);

        assertThat(user.getAllEmails(), equalTo(mergeEmails(userInfoEditForm)));
//        assertThat(user.getEmail(), equalTo(userInfoEditForm.getEmail()));
//        assertThat(user.getEmail2(), equalTo(userInfoEditForm.getEmail2()));
//        assertThat(user.getEmail3(), equalTo(userInfoEditForm.getEmail3()));
    }

    private String mergeEmails(UserData user) {
        return Arrays.asList(user.getEmail(), user.getEmail2(), user.getEmail3())
        .stream().filter((s) -> ! s.equals("")).collect(Collectors.joining("\n"));
    }
}