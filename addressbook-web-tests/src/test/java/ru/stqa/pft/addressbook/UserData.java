package ru.stqa.pft.addressbook;

public class UserData {
    private final String firstName;
    private final String lastName;
    private final String nickname;
    private final String homeNumber;
    private final String email;

    public UserData(String firstName, String lastName, String nickname, String homeNumber, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickname = nickname;
        this.homeNumber = homeNumber;
        this.email = email;
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
}
