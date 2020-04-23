package ua.nure.popova.SummaryTask4.db.entity;

import java.util.Objects;

public class Enrollee extends User implements Comparable<Enrollee> {

    private static final long serialVersionUID = 5692708766041889396L;

    private int entranceStatus;
    private String firstName;
    private String secName;
    private String lastName;
    private String email;
    private String city;
    private String region;
    private String school;
    private String password;
    private int roleId;
    private boolean accessAllowed;

    public int getEntranceStatus() {
        return entranceStatus;
    }

    public void setEntranceStatus(int entranceStatus) {
        this.entranceStatus = entranceStatus;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecName() {
        return secName;
    }

    public void setSecName(String secName) {
        this.secName = secName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    @Override
    public String toString() {
        return "Enrollee{" +
                "firstName='" + firstName + '\'' +
                ", secName='" + secName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", city='" + city + '\'' +
                ", region='" + region + '\'' +
                ", school='" + school + '\'' +
                '}';
    }

    public boolean isAccessAllowed() {
        return accessAllowed;
    }

    public void setAccessAllowed(boolean accessAllowed) {
        this.accessAllowed = accessAllowed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Enrollee enrollee = (Enrollee) o;
        return getFirstName().equals(enrollee.getFirstName()) &&
                getSecName().equals(enrollee.getSecName()) &&
                getLastName().equals(enrollee.getLastName()) &&
                getEmail().equals(enrollee.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getSecName(), getLastName(), getEmail());
    }

    @Override
    public int compareTo(Enrollee o) {
        return this.getLastName().compareTo(o.getLastName());
    }

}
