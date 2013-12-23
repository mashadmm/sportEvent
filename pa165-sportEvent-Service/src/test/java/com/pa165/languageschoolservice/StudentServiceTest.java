package com.pa165.languageschoolservice;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import com.pa165.languageschoolpersistence.DAO.LectureDAO;
import com.pa165.languageschoolpersistence.DAO.StudentDAO;
import com.pa165.languageschoolpersistence.DAOImpl.LectureDAOImpl;
import com.pa165.languageschoolpersistence.DAOImpl.StudentDAOImpl;
import com.pa165.languageschoolpersistence.Exceptions.ServiceFailureException;
import com.pa165.languageschoolpersistence.entities.Lecture;
import com.pa165.languageschoolpersistence.entities.Student;
import com.pa165.languageschoolservice.DTO.LectureDTO;
import com.pa165.languageschoolservice.DTO.StudentDTO;
import com.pa165.languageschoolservice.service.StudentService;
import com.pa165.sportEventservice.serviceImpl.StudentServiceImpl;
import java.util.ArrayList;
import java.util.List;
import static junit.framework.Assert.assertEquals;
import org.dozer.Mapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import static org.mockito.Mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author Jiri Uhlir
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-test.xml"})
public class StudentServiceTest {

    private StudentServiceImpl studentService;
    private LectureDAO lectureMockDAO;
    private StudentDAO studentMockDAO;
    @Autowired
    private Mapper mapper;
    public static String STUDENT_NAME = "John";

    @Before
    public void setUp() {

        studentService = new StudentServiceImpl();

        studentMockDAO = mock(StudentDAOImpl.class);
        studentService.setStudentDao(studentMockDAO);

        lectureMockDAO = mock(LectureDAOImpl.class);

        studentService.setMapper(mapper);
    }

    @Test
    public void addPass() throws  ServiceFailureException{
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setName(STUDENT_NAME);
        Student student = new Student();
        student.setName(STUDENT_NAME);



        when(studentMockDAO.add(any(Student.class))).thenReturn(student);

        StudentDTO returned = studentService.add(studentDTO);

        assertStudentDTO(returned, studentDTO);

        ArgumentCaptor<Student> studentArgument = ArgumentCaptor.forClass(Student.class);
        verify(studentMockDAO, times(1)).add(studentArgument.capture());
        verifyNoMoreInteractions(studentMockDAO);

        assertStudent(student, studentArgument.getValue());
    }

    @Test(expected = IllegalArgumentException.class)
    public void addNull() throws  ServiceFailureException{
        StudentDTO returned = studentService.add(null);
    }

    @Test
    public void removePass() throws  ServiceFailureException{
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(Long.MIN_VALUE);
        studentDTO.setName(STUDENT_NAME);
        Student student = new Student();
        student.setId(Long.MIN_VALUE);
        student.setName(STUDENT_NAME);

        studentService.remove(studentDTO);

        verify(studentMockDAO, times(1)).remove(student);
        verifyNoMoreInteractions(studentMockDAO);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeNull() throws  ServiceFailureException{
        studentService.remove(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeNullId() throws  ServiceFailureException{
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(null);
        studentDTO.setName(STUDENT_NAME);
        studentService.remove(studentDTO);
    }

    @Test
    public void modifyPass() throws  ServiceFailureException{
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(Long.MIN_VALUE);
        studentDTO.setName(STUDENT_NAME);
        Student student = new Student();
        student.setId(Long.MIN_VALUE);
        student.setName(STUDENT_NAME);

        when(studentMockDAO.modify(any(Student.class))).thenReturn(student);

        StudentDTO returned = studentService.modify(studentDTO);

        verify(studentMockDAO, times(1)).modify(student);
        verifyNoMoreInteractions(studentMockDAO);
        assertStudentDTO(returned, studentDTO);
    }

    @Test(expected = IllegalArgumentException.class)
    public void modifyNull() throws  ServiceFailureException{
        studentService.modify(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void modifyNullId() throws  ServiceFailureException{
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setName(STUDENT_NAME);
        studentDTO.setId(null);

        studentService.modify(studentDTO);
    }

    @Test
    public void getById() throws  ServiceFailureException{
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setName(STUDENT_NAME);
        studentDTO.setId(Long.MIN_VALUE);
        Student student = new Student();
        student.setName(STUDENT_NAME);
        student.setId(Long.MIN_VALUE);

        when(studentMockDAO.get(student.getId())).thenReturn(student);

        StudentDTO returned = studentService.get(studentDTO.getId());
        verify(studentMockDAO, times(1)).get(studentDTO.getId());
        verifyNoMoreInteractions(studentMockDAO);

        assertStudentDTO(returned, studentDTO);

    }

    @Test(expected = IllegalArgumentException.class)
    public void getNullId() throws  ServiceFailureException{
        Long ln = null;
        studentService.get(ln);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findByNameNull() throws  ServiceFailureException{
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setName(STUDENT_NAME);
        studentService.findByName(null);
    }

    @Test
    public void getAll() throws  ServiceFailureException{
        List<Student> students = new ArrayList<Student>();
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setName(STUDENT_NAME);
        studentDTO.setId(Long.MIN_VALUE);
        Student student = new Student();
        student.setName(STUDENT_NAME);
        student.setId(Long.MIN_VALUE);
        students.add(student);
        when(studentMockDAO.getAll()).thenReturn(students);

        List<StudentDTO> returned = studentService.getAll();
        verify(studentMockDAO, times(1)).getAll();
        verifyNoMoreInteractions(studentMockDAO);

        List<StudentDTO> toComp = new ArrayList<StudentDTO>();
        toComp.add(studentDTO);

        assertEquals(returned.size(), toComp.size());
        assertStudentDTO(returned.get(0), toComp.get(0));
    }

    @Test
    public void findByNamePass() throws  ServiceFailureException{
        List<Student> students = new ArrayList<Student>();
        String name = STUDENT_NAME;
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setName(STUDENT_NAME);
        studentDTO.setId(Long.MIN_VALUE);
        Student student = new Student();
        student.setName(STUDENT_NAME);
        student.setId(Long.MIN_VALUE);
        students.add(student);

        when(studentMockDAO.findByName(name)).thenReturn(students);

        List<StudentDTO> returned = studentService.findByName(name);
        verify(studentMockDAO, times(1)).findByName(name);
        verifyNoMoreInteractions(studentMockDAO);

        List<StudentDTO> toComp = new ArrayList<StudentDTO>();
        toComp.add(studentDTO);

        assertEquals(returned.size(), toComp.size());
        assertStudentDTO(returned.get(0), toComp.get(0));
    }

    @Test
    public void findByLecture() throws  ServiceFailureException {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(Long.MIN_VALUE);
        studentDTO.setName(STUDENT_NAME);
        Student student = new Student();
        student.setId(Long.MIN_VALUE);
        student.setName(STUDENT_NAME);

        LectureDTO lectureDTO = new LectureDTO();
        lectureDTO.setRoomNumber("123");
        lectureDTO.setId(Long.MIN_VALUE);
        Lecture lecture = new Lecture();
        lecture.setRoomNumber("123");
        lecture.setId(Long.MIN_VALUE);

        when(studentMockDAO.enrollStudentToLecture(any(Lecture.class), any(Student.class))).thenReturn(lecture);

        when(studentMockDAO.get(studentDTO.getId())).thenReturn(student);
        when(lectureMockDAO.get(lectureDTO.getId())).thenReturn(lecture);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findByLectureNull() throws  ServiceFailureException {
        studentService.findByLecture(null);
    }

    @Test
    public void enrollLecture() throws  ServiceFailureException{
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(Long.MIN_VALUE);
        studentDTO.setName(STUDENT_NAME);
        Student student = new Student();
        student.setId(Long.MIN_VALUE);
        student.setName(STUDENT_NAME);

        LectureDTO lectureDTO = new LectureDTO();
        lectureDTO.setRoomNumber("123");
        lectureDTO.setId(Long.MIN_VALUE);
        Lecture lecture = new Lecture();
        lecture.setId(Long.MIN_VALUE);
        lecture.setRoomNumber("123");
        lecture.setId(Long.MIN_VALUE);

        when(studentMockDAO.enrollStudentToLecture(any(Lecture.class), any(Student.class))).thenReturn(lecture);

        when(studentMockDAO.get(studentDTO.getId())).thenReturn(student);
        when(lectureMockDAO.get(lectureDTO.getId())).thenReturn(lecture);

    }

    @Test(expected = IllegalArgumentException.class)
    public void enrollLectureNullId1() throws  ServiceFailureException{
        LectureDTO lectureDTO = new LectureDTO();
        lectureDTO.setRoomNumber("123");
        lectureDTO.setId(Long.MIN_VALUE);

        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setName(STUDENT_NAME);

        LectureDTO returned = studentService.enrollStudentToLecture(lectureDTO, studentDTO);
    }

    @Test(expected = IllegalArgumentException.class)
    public void enrollLectureNullId2() throws  ServiceFailureException{
        LectureDTO lectureDTO = new LectureDTO();
        lectureDTO.setRoomNumber("123");

        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(Long.MIN_VALUE);
        LectureDTO returned = studentService.enrollStudentToLecture(lectureDTO, studentDTO);

    }

    private void assertStudent(Student expected, Student actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
    }

    private void assertStudentDTO(StudentDTO expected, StudentDTO actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
    }

    private void assertLectureDTO(LectureDTO lectureDTO1, LectureDTO lectureDTO2) {
        assertEquals(lectureDTO1.getId(), lectureDTO2.getId());
        assertEquals(lectureDTO1.getRoomNumber(), lectureDTO2.getRoomNumber());
    }
}
