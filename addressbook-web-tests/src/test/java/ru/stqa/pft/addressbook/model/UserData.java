package ru.stqa.pft.addressbook.model;

public class UserData {
    public void setId(int id) {
        this.id = id;
    }

    private int id;
    private final String firstName;
    private final String lastName;
    private final String nickname;
    private final String homeNumber;
    private final String email;
    private String group;

    public UserData(String firstName, String lastName, String nickname, String homeNumber, String email,
                    String group) {
        this.id = 0;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickname = nickname;
        this.homeNumber = homeNumber;
        this.email = email;
        this.group = group;
    }

    public UserData(int id, String firstName, String lastName, String nickname, String homeNumber, String email,
                    String group) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickname = nickname;
        this.homeNumber = homeNumber;
        this.email = email;
        this.group = group;
    }

    public int getId() {
        return id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserData userData = (UserData) o;

        if (id != userData.id) return false;
        if (firstName != null ? !firstName.equals(userData.firstName) : userData.firstName != null) return false;
        return lastName != null ? lastName.equals(userData.lastName) : userData.lastName == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

}
