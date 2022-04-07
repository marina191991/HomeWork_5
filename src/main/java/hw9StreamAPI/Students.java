package hw9StreamAPI;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


public class Students implements Student {
    private String name;
    private int age;
    private String sex;
    private List<Courses> course;


    public Students(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public List<Courses> getCourse() {
        return course;
    }

    public void setCourse(List<Courses> course) {
        this.course = course;
    }

    public Students(String name, int age, String sex, List<Courses> course) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.course = course;
    }

    /*---------Написать функцию, принимающую список Student и возвращающую список уникальных
    курсов, на которые подписаны студенты.*/
    public static List<Courses> uniqueCourses(List<Students> studentsList) {
//взяли список студентов, создали стрим со всеми курсами студентов, отсеяли повторы и добавили в коллекцию
        List<Courses> coursesList = studentsList.stream().flatMap(course -> course.getCourse().stream())
                .distinct().collect(Collectors.toList());
        return coursesList;
    }


    /* ----------2. Написать функцию, принимающую на вход список Student и возвращающую список из трех
     ----- самых любознательных (любознательность определяется количеством курсов).*/
    public static List<Students> curiousStudent(List<Students> studentsList) {
        List<Students> curSt = studentsList.stream().sorted(Comparator.comparingInt(o -> -o.getCourse().size())).limit(3).collect(Collectors.toList());
        return curSt;
    }


    /* -----------3. Написать функцию, принимающую на вход список Student и экземпляр Course, возвращающую
    ---------- список студентов, которые посещают этот курс.*/
    public static List<Students> takeCourse(List<Students> studentsList, Courses course) {

        List<Students> list = studentsList.stream().filter(s -> s.getCourse().contains(course)).collect(Collectors.toList());

        return list;
    }

    @Override

    public String getName() {

        return null;
    }

    @Override
    public List<Course> getAllCourses() {
        return null;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", courses" + course +
                '}' + "\n";

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Students students = (Students) o;
        return age == students.age && Objects.equals(name, students.name) && Objects.equals(sex, students.sex) && Objects.equals(course, students.course);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, sex, course);
    }
}
