package org.kaleval.spring_MVC.models;

import javax.validation.constraints.*;

public class Person {

    private int id;
    /**Пользователь не мог ввести пустое имя и размер, использование валидации*/
    @NotEmpty(message = "Name should not be empty")
    @Size(min=2, max = 100, message = "Имя должно быть от 2 до 100 символов длиной")
    private String fullName;

    @Size(min=2, max = 100, message = "Год рождения должен быть больше, чем 1900")
    private String yearOfBirth;

    public Person() {
    }

    public Person(String fullName, String yearOfBirth) {
        this.fullName = fullName;
        this.yearOfBirth = yearOfBirth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(String yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }
}
