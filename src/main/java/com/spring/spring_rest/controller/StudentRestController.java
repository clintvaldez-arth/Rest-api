package com.spring.spring_rest.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.spring_rest.dto.StudentDto;
import com.spring.spring_rest.service.StudentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "*")
public class StudentRestController {
	
//	private List<Student> theStudents;
//	
//	@PostConstruct
//	public void initialize() {
//		theStudents = new ArrayList<>();
//		
////		theStudents.add(new Student("Eren", "Yeager"));
////		theStudents.add(new Student("Mikasa", "Ackerman"));
////		theStudents.add(new Student("Levi", "Ackerman"));
//		
//		theStudents.add(new Student(1L,"Eren", "Yeager","eren.yeager@example.com"));
//		theStudents.add(new Student(2L,"Mikasa", "Ackerman", "mikasa.ackerman@example.com"));
//		theStudents.add(new Student(3L,"Levi", "Ackerman","levi.ackerman@example.com"));
//		
//	}
//	
//	@GetMapping("/students")
//	public List<Student> getStudents(){
//		return theStudents;
//	}
//	
//	@GetMapping("/students/{studentId}")
//	public Student getStudent(@PathVariable int studentId){
//		
//		if (studentId >= theStudents.size() || (studentId < 0)) {
//			throw new StudentNotFoundException(
//					"Student id " + studentId + " not Found!!");
//		}
//		
//		return theStudents.get(studentId);
//	}
	
	private StudentService studentService;
	
	public StudentRestController(StudentService studentService) {
		this.studentService = studentService;
	}
	
	@PostMapping
	public ResponseEntity<StudentDto> createStudents(@Valid
													 @RequestBody StudentDto theStudentDto){
		StudentDto saved = studentService.createStudent(theStudentDto);
		return new ResponseEntity<>(saved, HttpStatus.CREATED);
	}
	
    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable Long id) {
        StudentDto studentDto = studentService.getStudentById(id);
        return ResponseEntity.ok(studentDto);
    }
    
    @GetMapping
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        List<StudentDto> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable Long id,
            @Valid @RequestBody StudentDto studentDto) {
        StudentDto updatedStudent = studentService.updateStudent(id,
                studentDto);
        return ResponseEntity.ok(updatedStudent);
    }
    
    @PatchMapping("/{id}")
    public ResponseEntity<StudentDto> patchStudent(@PathVariable Long id,
            @Valid @RequestBody StudentDto studentDto) {
        StudentDto patchedStudent = studentService.patchStudent(id, studentDto);
        return ResponseEntity.ok(patchedStudent);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudentById(@PathVariable Long id) {
        studentService.deleteStudentById(id);
        return ResponseEntity.ok("Student with id " + id + " deleted successfully.");
    }
}
