package com.academy.student;

import java.util.Scanner;

public class StudentManager {

    private static final int MAX_STUDENTS = 20;

    private final Student[] students = new Student[MAX_STUDENTS];
    private int studentCount = 0;
    private final Scanner scanner;

    public StudentManager(Scanner scanner) {
        this.scanner = scanner;
    }

    public void displayMenu() {
        System.out.println("====================================");
        System.out.println("Student Management System");
        System.out.println("====================================");
        System.out.println("1. Add Student");
        System.out.println("2. Display Students");
        System.out.println("3. Search Student");
        System.out.println("4. Average Marks");
        System.out.println("5. Exit");
        System.out.print("Enter Choice : ");
    }
    // Methods addStudent, displayStudents, searchStudent, calculateAverage
    // will be filled in later steps.
    public int findStudentIndex(int id){
        for (int i = 0; i < studentCount; i++){
            if(students[i].getStudentId() == id){
                return i;
            }
        }
        return -1;
    }
    public void addStudent(){
        if(studentCount >= MAX_STUDENTS){
            System.out.println("full");
            return;
        }
        System.out.print("Student ID : ");
        int id;
        try {
            id = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException ex) {
            System.out.println("Invalid Input");
            return;
        }
        if(findStudentIndex(id) != -1){
            System.out.println("ID already exists");
            return;
        }
        System.out.print("Name : ");
        String name = scanner.nextLine();
        if(name.isEmpty()){
            System.out.println("Invalid Name!");
            return;
        }
        System.out.print("Course : ");
        String course = scanner.nextLine();
        if(course.isEmpty()){
            System.out.println("Invalid Course!");
            return;
        }
        System.out.print("Marks : ");
        double mark;
        try {
            mark = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException ex) {
            System.out.println("Invalid Input");
            return;
        }
        if (mark <0 || mark > 100){
            System.out.println("Mark is not in range which is between 0~100");
            return;
        }
        students[studentCount] = new Student(id, name, course, mark);
        studentCount++;
        System.out.println("Student Added Successfully.");
    }
    public void displayStudents() {
        if (studentCount == 0) {
            System.out.println("No students to display.");
            return;
        }
        System.out.println("----------------------------------------------------------");
        System.out.printf("%-8s %-20s %-15s %-8s%n", "ID", "Name", "Course", "Marks");
        System.out.println("----------------------------------------------------------");

        for (int i = 0; i < studentCount; i++) {
            Student student = students[i];
            System.out.printf("%-8d %-20s %-15s %-8.2f%n",
                    student.getStudentId(),
                    student.getName(),
                    student.getCourse(),
                    student.getMarks());
        }
        System.out.println("----------------------------------------------------------");
    }
    public void searchStudent(){
        if(studentCount == 0){
            System.out.println("No Student to search");
            return;
        }
        System.out.print("Student ID : ");
        int id;
        try {
            id = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException ex) {
            System.out.println("Invalid Input");
            return;
        }
        int checkId = findStudentIndex(id);
        if (checkId == -1){
            System.out.println("Student Not Found.");
            return;
        }else{
            students[checkId].display();
        }
    }
    public void calculateAverage(){
        double total = 0;
        if (studentCount == 0) {
            System.out.println("No students available.");
            return;
        }
        for (int i = 0; i <= studentCount - 1; i++){
            total += students[i].getMarks();
        }
        double avg = total / studentCount;
        System.out.printf("Average Marks : %.2f%n", avg);

    }
}