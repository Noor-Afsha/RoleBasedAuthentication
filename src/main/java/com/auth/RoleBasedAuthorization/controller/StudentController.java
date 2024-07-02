package com.auth.RoleBasedAuthorization.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth.RoleBasedAuthorization.entity.Student;
import com.auth.RoleBasedAuthorization.repository.StudentRepository;

@RestController
@RequestMapping("/auth")
public class StudentController {

	@Autowired
	private StudentRepository repository;

	@GetMapping("/getAll")
	public List<Student> getAllStudent() {
		return repository.findAll();
	}

	@PostMapping("/create")
	public Student createStudent(@RequestBody Student student) {
		return repository.save(student);
	}

	@PutMapping("/update/{id}")
	public Student update(@PathVariable int id, @RequestBody Student student) {

		Student existingStudent = repository.findById(id).get();
		existingStudent.setName(student.getName());
		existingStudent.setCollege(student.getCollege());
		repository.save(existingStudent);
		return existingStudent;
	}

	@DeleteMapping("/delete/{id}")
	public Map<String, Boolean> delete(@PathVariable int id) {
		Student deleteById = repository.findById(id).get();
		repository.delete(deleteById);
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		map.put("Deleted records successfully", Boolean.TRUE);
		return map;

	}
}
