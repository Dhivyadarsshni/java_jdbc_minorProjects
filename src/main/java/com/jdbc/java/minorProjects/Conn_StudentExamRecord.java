package com.jdbc.java.minorProjects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Conn_StudentExamRecord {
        private static final String url = "jdbc:mysql://localhost:3306/student_db";
        private static final String username = "root";
        private static final String pass = "pass@";

        public static Connection getConnection()throws SQLException{
            return DriverManager.getConnection(url,username,pass);
    }
}
