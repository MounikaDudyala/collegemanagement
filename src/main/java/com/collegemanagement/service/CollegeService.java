package com.collegemanagement.service;

import com.collegemanagement.dao.CollegeDao;
import com.collegemanagement.pojos.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollegeService {
    @Autowired
    CollegeDao collegeDao;
   public List<Student> fetchStudents()
   {
       List<Student> studentList=collegeDao.fetchStudents();
       return studentList;
   }
   public Student fetchStudent(int id){
       Student student=collegeDao.fetchStudent(id);
       return student;
   }
   public void createStudent(Student student){
      collegeDao.createStudent(student);
   }
   public void editStudent(Student student)
   {
       collegeDao.editStudent(student);
   }
   public boolean isStudentExist(Student student){
       return fetchStudent(student.getId())!=null;
   }
}
