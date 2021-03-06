package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserPhoneTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions () {
        app.goTo().homePage();
        if (app.user().all().size() == 0) {
            app.goTo().addNew();
            app.user().create(new UserData().withFirstName("Kitty").withLastName("Cat").withAddress("UK, London, Privet Drive, 4")
                    .withNickname("meow").withHomeNumber("3472737").withMobNumber("89212343434").withWorkNumber("1420978")
                    .withEmail("kittycat@test")
//                    .withGroup("test1")
            );
        }
    }

    @Test
    public void testUserPhone () {
        app.goTo().homePage();
        UserData user = app.user().all().iterator().next();
        UserData userInfoEditForm = app.user().infoFromEditForm(user);

        assertThat(user.getAllPhones(), equalTo(mergePhones(userInfoEditForm)));
//        assertThat(user.getHomeNumber(), equalTo(cleaned(userInfoEditForm.getHomeNumber())));
//        assertThat(user.getMobNumber(), equalTo(cleaned(userInfoEditForm.getMobNumber())));
//        assertThat(user.getWorkNumber(), equalTo(cleaned(userInfoEditForm.getWorkNumber())));
    }

    private String mergePhones(UserData user) {
        return Arrays.asList(user.getHomeNumber(), user.getMobNumber(), user.getWorkNumber())
                .stream().filter((s) -> ! s.equals("")).map(UserPhoneTests::cleaned).collect(Collectors.joining("\n"));
    }

    public static String cleaned(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }
}
