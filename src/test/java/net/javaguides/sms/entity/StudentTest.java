package net.javaguides.sms.entity;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class StudentTest {
	
	Student student;

    @Before
    public void setUp(){
       student=new Student();
    }

    @Test
    public void getId() throws Exception {
        Long idValue = 4L;
        student.setId(idValue);
        assertEquals(idValue, student.getId());
    }

    @Test
    public void getFirstName() throws Exception {
    	String name="pritam";
    	student.setFirstName(name);
    	assertEquals(name, student.getFirstName());
    }

    @Test
    public void getRecipes() throws Exception {
    }
}
