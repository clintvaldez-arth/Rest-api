package com.spring.spring_rest.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.spring.spring_rest.dto.StudentDto;
import com.spring.spring_rest.entity.Student;
import com.spring.spring_rest.exception.StudentNotFoundException;
import com.spring.spring_rest.mapper.StudentMapper;
import com.spring.spring_rest.repository.StudentRepository;

@Service
public class StudentServiceImplement implements StudentService{
	
	private StudentRepository studentRepo;
	
	public StudentServiceImplement(StudentRepository studentRepo) {
		this.studentRepo = studentRepo;
	}
	
	@Override
	public StudentDto createStudent(StudentDto theStudentDto) {
		Student theStudent = StudentMapper.mapToStudent(theStudentDto);
		Student saved = studentRepo.save(theStudent);
		return StudentMapper.mapToStudentDto(saved);
	}
	
	@Override
    public StudentDto getStudentById(Long id) {
        Student theStudent = studentRepo.findById(id)
        		.orElseThrow(
                () -> new StudentNotFoundException("Student not found with id: " + id));
        return StudentMapper.mapToStudentDto(theStudent);
    }
	
	@Override
    public List<StudentDto> getAllStudents() {
        List<Student> students = studentRepo.findAll();
        return students.stream().map((student) -> StudentMapper.mapToStudentDto(student)).collect(Collectors.toList());
    }
	
    @Override
    public StudentDto updateStudent(Long id, StudentDto studentDto) {
        Student existingStudent = studentRepo.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(
                        "Student not found with id: " + id));

        existingStudent.setFirstName(studentDto.getFirstName());
        existingStudent.setLastName(studentDto.getLastName());
        existingStudent.setEmail(studentDto.getEmail());

        Student updatedStudent = studentRepo.save(existingStudent);
        return StudentMapper.mapToStudentDto(updatedStudent);
    }
    
    @Override
    public StudentDto patchStudent(Long id, StudentDto studentDto) {
        Student existingStudent = studentRepo.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(
                        "Student not found with id: " + id));

        if (studentDto.getFirstName() != null) {
            existingStudent.setFirstName(studentDto.getFirstName());
        }
        if (studentDto.getLastName() != null) {
            existingStudent.setLastName(studentDto.getLastName());
        }
        if (studentDto.getEmail() != null) {
            existingStudent.setEmail(studentDto.getEmail());
        }

        Student updatedStudent = studentRepo.save(existingStudent);
        return StudentMapper.mapToStudentDto(updatedStudent);
    }
    
    @Override
    public void deleteStudentById(Long id) {
        Student existingStudent = studentRepo.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(
                        "Student not found with id: " + id));
        studentRepo.delete(existingStudent);
    }
}
