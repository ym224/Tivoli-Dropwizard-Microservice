package model;

import java.io.Serializable;
import java.util.Objects;

public class Cal_User implements Serializable {

    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
    private String gender;
    private String email;

    public Cal_User(){}

    public Cal_User(String firstName, String lastName, String gender, Integer age, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cal_User cal_user = (Cal_User) o;
        return Objects.equals(id, cal_user.id) &&
                Objects.equals(firstName, cal_user.firstName) &&
                Objects.equals(lastName, cal_user.lastName) &&
                Objects.equals(age, cal_user.age) &&
                Objects.equals(gender, cal_user.gender) &&
                Objects.equals(email, cal_user.email);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, firstName, lastName, age, gender, email);
    }
}
