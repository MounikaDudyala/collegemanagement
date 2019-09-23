package com.collegemanagement.controller;

import com.collegemanagement.pojos.Student;
import com.collegemanagement.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping(value="/")
public class CollegeController {
    @Autowired
    CollegeService collegeService;
    @RequestMapping(value="/student",method= RequestMethod.GET)
    public ResponseEntity<List<Student>> fetchStudents(){
        List<Student> studentList=collegeService.fetchStudents();
        if(studentList.isEmpty())
        {
            return new ResponseEntity<List<Student>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Student>>(studentList, HttpStatus.OK);
    }
   @RequestMapping(value="/student{id}",method=RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> fetchEmployee(@PathVariable int id){
     Student student=collegeService.fetchStudent(id);
     if(student==null){
         System.out.println("Student with Id:"+id+"not existed");
         return new ResponseEntity<Student>(HttpStatus.NO_CONTENT);
     }
     return new ResponseEntity<Student>(student,HttpStatus.OK);
   }
   @RequestMapping(value="/student",method=RequestMethod.POST)
    public ResponseEntity<Void> createStudent(@RequestBody Student student,UriComponentsBuilder uriComponentsBuilder){
    if(collegeService.isStudentExist(student)){
        System.out.println("Student already exists");
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
    collegeService.createStudent(student);
    HttpHeaders httpHeaders=new HttpHeaders();
    httpHeaders.setLocation(uriComponentsBuilder.path("/student/{id}").buildAndExpand(student.getId()).toUri());
    return new ResponseEntity<>(HttpStatus.OK);
}
   @RequestMapping(value="/student{id}",method =RequestMethod.PUT)
    public ResponseEntity<Student> editStudent(@PathVariable int id,@RequestBody Student student){
        Student currentStudent=collegeService.fetchStudent(id);
        if(currentStudent==null)
        {
            System.out.println("Student not exists");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        currentStudent.setDeptId(student.getDeptId());
        currentStudent.setFirstname(student.getFirstname());
        currentStudent.setLastname(student.getLastname());
        collegeService.editStudent(student);
        return new ResponseEntity<>(student,HttpStatus.OK);
}
}
