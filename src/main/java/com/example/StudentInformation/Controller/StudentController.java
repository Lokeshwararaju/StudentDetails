package com.example.StudentInformation.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.StudentInformation.Repository.StudentRepo;
import com.example.StudentInformation.entity.Student;

@RestController
public class StudentController {
 
	@Autowired
	StudentRepo studentRepo;
	
	@PostMapping("/api/studentss")
	public ResponseEntity<Student> studentDetails(@RequestBody Student student) {
		return new ResponseEntity<>(studentRepo.save(student),HttpStatus.CREATED);
	}
	@GetMapping("/api/studentss")
	public ResponseEntity<List<Student>> getDetails(){
		return new ResponseEntity<List<Student>>(studentRepo.findAll(), HttpStatus.OK);
	}
	@GetMapping("api/studentss/{id}")
	public ResponseEntity<Student> getStudentDetails(@PathVariable int id){
		Optional<Student> student =  studentRepo.findById(id);
		
		if(student.isPresent()) {
			return new ResponseEntity<>(student.get(),HttpStatus.OK );
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	@PutMapping("api/studentss/{id}")
	public ResponseEntity<Student> updateStudentDetails(@PathVariable int id , @RequestBody Student stud){
		Optional<Student> student =  studentRepo.findById(id);
		
		if(student.isPresent()) {
			student.get().setName(stud.getName());
			student.get().setGender(stud.getGender());
			student.get().setCity(stud.getCity());
			return new ResponseEntity<>(studentRepo.save(student.get()),HttpStatus.OK );
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	@DeleteMapping("api/studentss/{id}")
	public ResponseEntity<Void> deleteStudentDetails(@PathVariable int id , @RequestBody Student stud){
		Optional<Student> student =  studentRepo.findById(id);
		
		if(student.isPresent()) {
			studentRepo.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT );
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
}
