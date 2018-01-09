package com.getjavajob.common;

import com.getjavajob.common.enums.Role;
import com.getjavajob.common.enums.Sex;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Account {
    private int id;
    private String name;
    private String middleName;
    private String surName;
    private Sex sex;
    private Role role;
    private Date birthDate;
    private String homeAddress;
    private String workAddress;
    private String email;
    private String password;
    private int icq;
    private String skype;
    private String additionalInfo;
    private List<Group> groups = new ArrayList();
    private List<Phone> phones = new ArrayList();
    private List<Account> friends = new ArrayList();
    private byte[] image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getWorkAddress() {
        return workAddress;
    }

    public void setWorkAddress(String workAddress) {
        this.workAddress = workAddress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public void addPhone(Phone phone) {
        phones.add(phone);
    }

    public int getIcq() {
        return icq;
    }

    public void setIcq(int icq) {
        this.icq = icq;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Account> getFriends() {
        return friends;
    }

    public void setFriends(List<Account> friends) {
        this.friends = friends;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;

        Account account = (Account) o;

        if (id != account.id) return false;
        if (name != null ? !name.equals(account.name) : account.name != null) return false;
        if (surName != null ? !surName.equals(account.surName) : account.surName != null) return false;
        if (sex != null ? !sex.equals(account.sex) : account.sex != null) return false;
        return email != null ? email.equals(account.email) : account.email == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surName != null ? surName.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", middleName='" + middleName + '\'' +
                ", surName='" + surName + '\'' +
                ", sex='" + sex + '\'' +
                ", birthDate=" + birthDate +
                ", homeAddress='" + homeAddress + '\'' +
                ", workAddress='" + workAddress + '\'' +
                ", email='" + email + '\'' +
                ", phones=" + phones +
                ", icq=" + icq +
                ", skype='" + skype + '\'' +
                ", additionalInfo='" + additionalInfo + '\'' +
                ", groups=" + groups +
                '}';
    }
}