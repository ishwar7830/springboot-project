package com.luv2code.demo.rest;

import com.luv2code.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api")
public class StudentRestController {
    //define endpoint for "/students" -return a list of students
    private List<Student> theStudents;
    @PostConstruct
    public void loadData(){
        theStudents= new ArrayList<>();
        theStudents.add(new Student("Ravi","Patel"));
        theStudents.add(new Student("mora","jiv"));
        theStudents.add(new Student("kavi","ranjab"));



    }
    @GetMapping("/students")
    public List<Student>getStudents(){


       return theStudents;
    }
    // define endpoint or "/students/(studentId)" -return student at index

    @GetMapping("/students/{studentId}")
    public Student getStarted (@PathVariable int studentId){
        if((studentId >= theStudents.size() ||(studentId<0))){
            throw new StudentNotFoundException("student id not found -"+studentId);


        }
        return theStudents.get(studentId);


    }
  //add an exception handling  using @ExceptionHandler

    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse>handlerException(StudentNotFoundException exc){
        StudentErrorResponse error=new StudentErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse>handleException(Exception exc){
        StudentErrorResponse error=new StudentErrorResponse();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exc.getMessage());
        error.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }


}
