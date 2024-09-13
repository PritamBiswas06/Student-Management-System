package net.javaguides.sms.controller;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import net.javaguides.sms.entity.Student;
import net.javaguides.sms.service.StudentService;

public class StudentControllerTest {
	
	@Mock
	StudentService studentService;
	
	MockMvc mockMvc;
	
	StudentController studentController;
	

	 @Before
	    public void setUp() throws Exception {
	        MockitoAnnotations.openMocks(this);

	        studentController = new StudentController(studentService);
	        mockMvc = MockMvcBuilders.standaloneSetup(studentController)
	        		.build();
	    }
	 @Test
	 public void testGetListStudents() throws Exception{
		 
//		 Student student1=new Student("pp","bb","gm");
//		 Student student2=new Student("ppp","bbb","ggm");
		 List<Student> existingStudent=studentService.getAllStudents();
		 
		 when(studentService.getAllStudents()).thenReturn(existingStudent);
		 
		 
		 mockMvc.perform(get("/students/"))
		 .andExpect(status().isOk())
         .andExpect(view().name("students"))
         .andExpect(model().attributeExists("students"));
	 }
	 
	 @Test
	 public void testCreateStudentForm() throws Exception{
		 
		 Student student = new Student();
		 mockMvc.perform(get("/students/new/"))
		 .andExpect(status().isOk())
         .andExpect(view().name("create_student"))
         .andExpect(model().attributeExists("student"));
	 }
	 
	 @Test
	 public void testSavestudent() throws Exception{
//		 Student student1=new Student("pp","bb","gm");
//		 Student student2=new Student("ppp","bbb","ggm"); 
		 
		 Student student = new Student();
		 when(studentService.saveStudent(student)).thenReturn(student);
		 
		 mockMvc.perform(post("/students/save/"))
		 .andExpect(status().is3xxRedirection())
         .andExpect(view().name("redirect:/students"));
	 }
	 
	 @Test
	 public void testEditStudentForm() throws Exception{
		 Student student=new Student();
		 student.setId(1L);
		 
		 when(studentService.getStudentById(anyLong())).thenReturn(student);
		 
		 mockMvc.perform(get("/students/edit/1/"))
		 .andExpect(status().isOk())
         .andExpect(view().name("edit_student"))
         .andExpect(model().attributeExists("student"));
	 }
	 
	 @Test
	 public void testupdateStudent() throws Exception{
		 
		 Student student=new Student();
		 when(studentService.getStudentById(anyLong())).thenReturn(student);

		 Student existingstudent= studentService.getStudentById(anyLong());
		 when(studentService.updateStudent(existingstudent)).thenReturn(student);
		 
		 mockMvc.perform(post("/students/1/"))
		 .andExpect(status().is3xxRedirection())
         .andExpect(view().name("redirect:/students"));
		 
	 }
	 
	 @Test
	 public void testdeleteStudent() throws Exception{
//		 Student student=new Student();
//		 student.setId(1L);
		 
		 mockMvc.perform(get("/students/1/delete/"))
		 .andExpect(status().is3xxRedirection())
         .andExpect(view().name("redirect:/students"));
		 verify(studentService, times(1)).deleteStudentById(anyLong());

	 }
}
