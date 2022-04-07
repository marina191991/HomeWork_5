package hw9StreamAPI;

import java.util.*;
/*------------1. Написать функцию, принимающую список Student и возвращающую список уникальных
         курсов, на которые подписаны студенты.
         2. Написать функцию, принимающую на вход список Student и возвращающую список из трех
         самых любознательных (любознательность определяется количеством курсов).
         3. Написать функцию, принимающую на вход список Student и экземпляр Course, возвращающую
         список студентов, которые посещают этот курс.------------*/

public class Main {
    public static void main(String[] args) {
        List<Courses> courses = Arrays.asList(
                new Courses("History of the 19th century"),
                new Courses("Psychology of child education"),
                new Courses("Artistic fine art of the 18th century"),
                new Courses("Geography"));


        List<Students> students = Arrays.asList(
                new Students("Kate", 21, "woman", Arrays.asList(courses.get(1), courses.get(2))),
                new Students("Bob", 23, "man", Arrays.asList(courses.get(2), courses.get(0), courses.get(1))),
                new Students("Anna", 32, "woman", Arrays.asList(courses.get(1))),
                new Students("Dan", 20, "man", Arrays.asList(courses.get(3))),
                new Students("Dan", 20, "man", Arrays.asList(courses.get(3)))
        );
        System.out.println("ex.1+++++++++++++++++++++");
        System.out.println(Students.uniqueCourses(students));
        System.out.println("ex.2+++++++++++++++++++++");
        System.out.println(Students.curiousStudent(students));
        System.out.println("ex.3+++++++++++++++++++++");
        System.out.println(Students.takeCourse(students, courses.get(1)));
    }

}
