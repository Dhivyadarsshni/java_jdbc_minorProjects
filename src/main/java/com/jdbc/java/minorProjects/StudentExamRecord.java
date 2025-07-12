package com.jdbc.java.minorProjects;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
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

            int rowsInserted = ps.executeUpdate();

            if(rowsInserted>0) System.out.println("Datas inserted successfully");
            else System.out.println("Error occured! Failed to insert data, please try again");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    //View Student function
    private static void viewStudent(Scanner sc) {
        try(Connection conn = Conn_StudentExamRecord.getConnection()){
            Statement smt = conn.createStatement();
            ResultSet rs = smt.executeQuery("Select* from student");

            System.out.println("\n--- Student Records ---");
            while (rs.next()){
                System.out.println("ID: "+rs.getString("id")
                        + "\nName: "+rs.getString("name")
                        +"\nDepartment: "+ rs.getString("dept")
                        +"\nCompany: "+rs.getString("company_name")
                        +"\nMarks scored: "+rs.getInt("scored_marks")
                        +"\nTotal Marks: "+rs.getInt("total_marks")
                );
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    //Update Student function
    private static void updateStudent(Scanner sc){
        try(Connection conn = Conn_StudentExamRecord.getConnection()){
            System.out.println("Enter the student ID to update details for: ");
            String id = sc.next();

            String checkQuery = "SELECT* FROM STUDENT WHERE id =?";

            PreparedStatement checkstmt = conn.prepareStatement(checkQuery);
            checkstmt.setString(1,id);
            ResultSet rs = checkstmt.executeQuery();

            if(!rs.next()){ System.out.println("Wrong id entered: "+id); return;}



            System.out.println("The fields given are\n1.Name\n2.Department\n3.Company\n4.Marks Scored\n5.Total Marks");
            System.out.println("In total how many fields do you want to update? ");
            int fieldCount = sc.nextInt();
            sc.nextLine();

            //first line of the update query
            StringBuilder sb = new StringBuilder("UPDATE STUDENT SET ");
            //we introduced <Object> so it can fit both integer and string values
            List<Object> values = new ArrayList<>();
            //introduce a loop to save the field names to update from the user
            for(int i =1; i<=fieldCount; i++){
                System.out.println("Enter the field name to update:");
                System.out.println("name,dept,company_name,scored_marks,total_marks ");
                System.out.println("(one field at a time)");
                String field = sc.nextLine();

                System.out.println("Enter the new value for "+field+":");
                String value = sc.nextLine();

                sb.append(field).append(" =?");
                if(i != fieldCount )sb.append(" ,");

                if(field.equalsIgnoreCase("scored_marks") || field.equalsIgnoreCase("total_marks"))
                    values.add(Integer.parseInt(value));
                else
                    values.add(value);

            }
            //append the query statement with WHERE clause using primary key
            sb.append(" WHERE id = ?");
            values.add(id);

            //convert sb into a string and prepare the sql query
            PreparedStatement ps = conn.prepareStatement(sb.toString());

            //set params dynamically into ps
            for (int i = 0; i< values.size();i++){
                Object val = values.get(i);

                if(val instanceof Integer)
                    ps.setInt(i+1,(Integer) val);
                else
                    ps.setString(i+1,(String) val);
            }

            int check = ps.executeUpdate();
            if(check>0)
                System.out.println("Student record updated");
            else
                System.out.println("uh-oh something went wrong");

        } catch (SQLException sqle) {
        // A SQL exception (syntax error, connectivity issue, etc.)
        System.out.println("Oops! Something went wrong with the database.");
        System.out.println("Error: " + sqle.getMessage());
    } catch (Exception e) {
        // Any other unexpected exception
        System.out.println("⚠️ Unexpected error: " + e.getMessage());
    }

    }
    //View Student function
    private static void deleteStudent(Scanner sc){

    }
}
