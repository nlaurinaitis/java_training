package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserAddressTests extends TestBase {

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
    public void testUserAddress () {
        app.goTo().homePage();
        UserData user = app.user().all().iterator().next();
        UserData userInfoEditForm = app.user().infoFromEditForm(user);

        assertThat(user.getAddress(), equalTo(userInfoEditForm.getAddress()));
    }
}