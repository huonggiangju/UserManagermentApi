package com.example.demo;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1")
public class Controller {

	@Autowired
	private StudentRepository repo;
	
	
	
	
	@PostMapping("/addStudent")
	public String save(@RequestBody Student s) {
		repo.save(s);
		return "Add Student with id " +s.getFirstName();
	}
	
	
	@GetMapping("/listStudent")
	public List<Student> getAllStudents(){
		return repo.findAll();
	}
	
	@GetMapping("/getStudent/{id}")
	public ResponseEntity<Student> getAccountById(@PathVariable("id") String id) {
		try {
			Student s = repo.findById(id).get();
			return new ResponseEntity<>(s, HttpStatus.OK);
			
		}catch(NoSuchElementException ex) {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	
	@DeleteMapping("/delete/{id}")
	public String deleteStudent (@PathVariable String id) {
		repo.deleteById(id);
		return "Delete Student with id  " + id;
	}
	
	@PutMapping("/update")
	public Student updateStudent(@RequestBody Student s) {

		return repo.save(s);
	}
}
