package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

@XStreamAlias("user")
@Entity
@Table(name = "addressbook")
public class UserData {

    @XStreamOmitField
    @Id
    @Column(name = "id")
    private int id = Integer.MAX_VALUE;

    @Expose
    @Column(name = "firstname")
    private String firstName;

    @Expose
    @Column(name = "lastname")
    private String lastName;

    @Expose
    @Column(name = "nickname")
    private String nickname;

    @Expose
    @Column(name = "address")
    @Type(type = "text")
    private String address;

    @Expose
    @Column(name = "home")
    @Type(type = "text")
    private String homeNumber;

    @Expose
    @Column(name = "mobile")
    @Type(type = "text")
    private String mobNumber;

    @Expose
    @Column(name = "work")
    @Type(type = "text")
    private String workNumber;

    @Transient
    private String allPhones;

    @Expose
    @Column(name = "email")
    @Type(type = "text")
    private String email;

    @Expose
    @Column(name = "email2")
    @Type(type = "text")
    private String email2;

    @Expose
    @Column(name = "email3")
    @Type(type = "text")
    private String email3;

    @Transient
    private String allEmails;

    @Expose
    @Column(name = "photo")
    @Type(type = "text")
    @Transient
    private String photo;

    @ManyToMany (fetch = FetchType.EAGER)
    @JoinTable(name = "address_in_groups",joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn (name = "group_id"))
    private Set<GroupData> groups = new HashSet<GroupData>();

    public UserData withId(int id) {
        this.id = id;
        return this;
    }

    public UserData withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserData withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserData withNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public UserData withAddress(String address) {
        this.address = address;
        return this;
    }

    public UserData withHomeNumber(String homeNumber) {
        this.homeNumber = homeNumber;
        return this;
    }

    public UserData withMobNumber(String mobNumber) {
        this.mobNumber = mobNumber;
        return this;
    }

    public UserData withWorkNumber(String workNumber) {
        this.workNumber = workNumber;
        return this;
    }

    public UserData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public UserData withEmail(String email) {
        this.email = email;
        return this;
    }

    public UserData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public UserData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public UserData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }

    public UserData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
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

    public String getAddress() {
        return address;
    }

    public String getHomeNumber() {
        return homeNumber;
    }

    public String getMobNumber() {
        return mobNumber;
    }

    public String getWorkNumber() {
        return workNumber;
    }

    public String getAllPhones() {
        return allPhones;
    }

    public String getEmail() {
        return email;
    }

    public String getEmail2() {
        return email2;
    }

    public String getEmail3() {
        return email3;
    }

    public String getAllEmails() {
        return allEmails;
    }

    public Groups getGroups() {
        return new Groups (groups);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserData userData = (UserData) o;

        if (id != userData.id) return false;
        if (firstName != null ? !firstName.equals(userData.firstName) : userData.firstName != null) return false;
        if (lastName != null ? !lastName.equals(userData.lastName) : userData.lastName != null) return false;
        if (nickname != null ? !nickname.equals(userData.nickname) : userData.nickname != null) return false;
        if (address != null ? !address.equals(userData.address) : userData.address != null) return false;
        if (homeNumber != null ? !homeNumber.equals(userData.homeNumber) : userData.homeNumber != null) return false;
        if (mobNumber != null ? !mobNumber.equals(userData.mobNumber) : userData.mobNumber != null) return false;
        if (workNumber != null ? !workNumber.equals(userData.workNumber) : userData.workNumber != null) return false;
        if (email != null ? !email.equals(userData.email) : userData.email != null) return false;
        if (email2 != null ? !email2.equals(userData.email2) : userData.email2 != null) return false;
        return email3 != null ? email3.equals(userData.email3) : userData.email3 == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (nickname != null ? nickname.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (homeNumber != null ? homeNumber.hashCode() : 0);
        result = 31 * result + (mobNumber != null ? mobNumber.hashCode() : 0);
        result = 31 * result + (workNumber != null ? workNumber.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (email2 != null ? email2.hashCode() : 0);
        result = 31 * result + (email3 != null ? email3.hashCode() : 0);
        return result;
    }

    public File getPhoto() {
        return new File(photo);
    }

    @Override
    public String toString() {
        return "UserData{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", nickname='" + nickname + '\'' +
                ", address='" + address + '\'' +
                ", homeNumber='" + homeNumber + '\'' +
                ", mobNumber='" + mobNumber + '\'' +
                ", workNumber='" + workNumber + '\'' +
                ", email='" + email + '\'' +
                ", email2='" + email2 + '\'' +
                ", email3='" + email3 + '\'' +
                '}';
    }

    public UserData inGroup(GroupData group) {
        groups.add(group);
        return this;
    }
}
