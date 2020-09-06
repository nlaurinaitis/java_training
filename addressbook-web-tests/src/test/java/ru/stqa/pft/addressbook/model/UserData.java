package ru.stqa.pft.addressbook.model;

public class UserData {
    private final String firstName;
    private final String lastName;
    private final String nickname;
    private final String homeNumber;
    private final String email;
    private String group;

    public UserData(String firstName, String lastName, String nickname, String homeNumber, String email, String group) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickname = nickname;
        this.homeNumber = homeNumber;
        this.email = email;
        this.group = group;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNickname() {
        return nickname;
    }

    public String getHomeNumber() {
        return homeNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getGroup() {
        return group;
    }
}
