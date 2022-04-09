package hw1ObjectClass;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Person {
    private String namePerson;
    private Integer age;
    private String sex;
    private Integer power;
    private boolean passedDistance;

    public Person(String namePerson, Integer age, String sex, Integer power) {
        this.namePerson = namePerson;
        this.age = age;
        this.sex = sex;
        this.power = power;
    }

    public boolean isPassedDistance() {
        return passedDistance;
    }

    public void setPassedDistance(boolean passedDistance) {
        this.passedDistance = passedDistance;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public String getNamePerson() {
        return namePerson;
    }

    public void setNamePerson(String namePerson) {
        this.namePerson = namePerson;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    //сздать список из людей для формирования команды. Макс 5 чел
    public static List<Person> takeList(Person person0, Person person1, Person person2, Person person3, Person person4) {
        List<Person> listPerson = Arrays.asList(person0, person1, person2, person3, person4);
        return listPerson;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + namePerson + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", power='" + power + '\'' +
                '}' + "\n";
    }
}
