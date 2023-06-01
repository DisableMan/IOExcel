package com.excel.service;

import com.excel.ExcelHelper;
import com.excel.model.Student;
import com.excel.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public void saveStudentsToDatabase(MultipartFile file){
        if(ExcelHelper.hasExcelFormat(file)){
            try{
                List<Student> students = ExcelHelper.getStudentsDataFromExcel(file.getInputStream());
                this.studentRepository.saveAll(students);
            }catch (IOException e){
                throw new RuntimeException(e);
            }
        }
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }


}
