package com.collegemanagement.dao;

import com.collegemanagement.pojos.Department;
import com.collegemanagement.pojos.Student;

import java.util.List;

public interface CollegeDao {
    void createStudent(Student student);
    List<Student> fetchStudents();
    Student fetchStudent(int id);
    void editStudent(Student student);
    Department fetchDepartment(int id);
}
