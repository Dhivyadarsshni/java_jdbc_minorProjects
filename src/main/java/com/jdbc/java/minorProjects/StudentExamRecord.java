package com.jdbc.java.minorProjects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class StudentExamRecord {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome to DD's University Student Record ");
            System.out.println("Choose one of the following options: ");
            System.out.println("1. Add New Student");
            System.out.println("2. View a Student");
            System.out.println("3. Update a Student Details");
            System.out.println("4. Delete a Student");
            System.out.println("5. Exit");

            int ch = sc.nextInt();

            switch (ch) {
                case 1 -> addStudent(sc);
                case 2 -> viewStudent(sc);
                case 3 -> updateStudent(sc);
                case 4 -> deleteStudent(sc);
                case 5 -> {
                    System.out.println("Good Day, Signing Off");
                    System.exit(0);
                }
                default -> System.out.println("Enter a valid number");
            }
        }
    }
    //Add Student function
    private static void addStudent(Scanner sc){
        try (Connection conn = Conn_StudentExamRecord.getConnection()){
            System.out.print("Enter your Register ID: ");
            String id = sc.next();
            System.out.print("Enter your name: ");
            String name = sc.next();
            System.out.print("Enter your Department: ");
            String dept = sc.next();
            System.out.print("Enter your Company name: ");
            String cmpny_name = sc.next();
            System.out.print("Enter Gained Marks: ");
            int scored_marks = sc.nextInt();
            System.out.print("Enter Total Marks: ");
            int total_marks = sc.nextInt();

            String query = "INSERT INTO STUDENT (id,name,dept,company_name,scored_marks,total_marks)VALUES (?, ?, ?, ? ,? ,?)";

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1,id);
            ps.setString(2,name);
            ps.setString(3,dept);
            ps.setString(4,cmpny_name);
            ps.setInt(5,scored_marks);
            ps.setInt(6,total_marks);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    //View Student function
    private static void viewStudent(Scanner sc) {

    }
    //Update Student function
    private static void updateStudent(Scanner sc){

    }
    //View Student function
    private static void deleteStudent(Scanner sc){

    }
}
