-- Create the Database and use
CREATE DATABASE Student_DB;
USE Student_DB;
-- Create the Table Student
CREATE TABLE STUDENT(
id VARCHAR(20) PRIMARY KEY,
name VARCHAR(50),
dept VARCHAR(50),
company_name VARCHAR(25),
scored_marks INT,
total_marks INT
);
-- Insert values using Java code
-- Create an JDBC Connection for that using Conn class , name it as Conn_JavaFileName


