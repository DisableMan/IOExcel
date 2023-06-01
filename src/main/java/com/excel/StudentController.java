package com.excel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@CrossOrigin("http:localhost:8080")
@Controller
@RequestMapping("students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/upload-students-data")
    public ResponseEntity<?> uploadCustomersData(@RequestParam ("file") MultipartFile file){
        this.studentService.saveStudentsToDatabase(file);
        return ResponseEntity.ok(Map.of("Message", "Customers data uploaded and saved to database successfully"));

    }
    @GetMapping
    public ResponseEntity<List<Student>> getStudents(){
        return new ResponseEntity<>(studentService.getStudents(), HttpStatus.FOUND);
    }
}
