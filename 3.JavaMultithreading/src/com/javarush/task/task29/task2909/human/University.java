package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.List;

public class University {

    private String name;
    private int age;
    private List<Student> students = new ArrayList<>();


    public University(String name, int age) {
        //super(name, age, 0);
        this.name = name;
        this.age = age;
    }

    public Student getStudentWithAverageGrade(double averageValue) {
        Student student = null;
        for (Student st : students){
            if (st.getAverageGrade() == averageValue)
                student = st;
        }
        return student;
    }

    public Student getStudentWithMaxAverageGrade() {
        double maxGrade = 0;
        Student student = null;
        for (Student st : students){
            if (st.getAverageGrade() > maxGrade) {
                maxGrade = st.getAverageGrade();
                student = st;
            }
        }
        //TODO:
        return student;
    }

    public Student getStudentWithMinAverageGrade() {
        //TODO:
        Student student = students.get(0);
        double minGrade = student.getAverageGrade();
        for (Student st : students){
            if (st.getAverageGrade() < minGrade){
                minGrade = st.getAverageGrade();
                student = st;
            }
        }
        return student;

    }
    public void expel(Student student){
        //TODO:
        students.remove(student);
    }

    public String getName() {
        return name;
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

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}