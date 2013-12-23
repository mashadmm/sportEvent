/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pa165.languageschoolservice;

import com.pa165.languageschoolpersistence.DAO.CourseDAO;
import com.pa165.languageschoolpersistence.DAOImpl.CourseDAOImpl;
import com.pa165.languageschoolpersistence.Exceptions.ServiceFailureException;
import com.pa165.languageschoolpersistence.entities.Course;
import com.pa165.languageschoolpersistence.entities.Difficulty;
import com.pa165.languageschoolpersistence.entities.LanguageSpoken;
import com.pa165.languageschoolservice.service.CourseService;
import com.pa165.languageschoolservice.serviceImpl.CourseServiceImpl;
import com.pa165.languageschoolservice.DTO.CourseDTO;
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
 * @author Aleksei Penzentcev
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-test.xml"})
public class CourseServiceTest {

    private CourseDAO courseMockDAO;
    private CourseServiceImpl courseService;
    @Autowired
    private Mapper mapper;
    public static String COURSE_NAME = "RU-BEG";

    @Before
    public void setUp() {
        courseService = new CourseServiceImpl();

        courseMockDAO = mock(CourseDAOImpl.class);
        courseService.setCourseDAO(courseMockDAO);

        courseService.setMapper(mapper);
    }

    @Test
    public void addPass() throws  ServiceFailureException {
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setName(COURSE_NAME);
        Course course = new Course();
        course.setName(COURSE_NAME);
        course.setLanguage(LanguageSpoken.RUSSIAN);
        course.setDifficulty(Difficulty.BEGINNER);

        when(courseMockDAO.add(any(Course.class))).thenReturn(course);

        CourseDTO returned = courseService.add(courseDTO);

        assertCourseDTO(returned, courseDTO);

        ArgumentCaptor<Course> courseArgument = ArgumentCaptor.forClass(Course.class);
        verify(courseMockDAO, times(1)).add(courseArgument.capture());

        assertCourse(courseArgument.getValue(), course);

    }

    @Test(expected = IllegalArgumentException.class)
    public void addNull() throws  ServiceFailureException {
        CourseDTO returned = courseService.add(null);
    }

    @Test
    public void removePass() throws  ServiceFailureException{
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setId(Long.MIN_VALUE);
        courseDTO.setName(COURSE_NAME);
        Course course = new Course();
        course.setId(Long.MIN_VALUE);
        course.setName(COURSE_NAME);

        courseService.remove(courseDTO);

        verify(courseMockDAO, times(1)).remove(course);
        verifyNoMoreInteractions(courseMockDAO);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeNull() throws  ServiceFailureException {
        courseService.remove(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeNullId() throws  ServiceFailureException {
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setId(null);
        courseDTO.setName(COURSE_NAME);
        courseService.remove(courseDTO);
    }

    @Test
    public void modifyPass()  throws  ServiceFailureException{
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setId(Long.MIN_VALUE);
        courseDTO.setName(COURSE_NAME);
        Course course = new Course();
        course.setId(Long.MIN_VALUE);
        course.setName(COURSE_NAME);

        when(courseMockDAO.modify(any(Course.class))).thenReturn(course);

        CourseDTO returned = courseService.modify(courseDTO);

        verify(courseMockDAO, times(1)).modify(course);
        assertCourseDTO(returned, courseDTO);
    }

    @Test(expected = IllegalArgumentException.class)
    public void modifyNull() throws  ServiceFailureException {
        courseService.modify(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void modifyNullId() throws  ServiceFailureException {
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setName(COURSE_NAME);
        courseDTO.setId(null);

        courseService.modify(courseDTO);
    }

    @Test
    public void getById() throws  ServiceFailureException{
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setName(COURSE_NAME);
        courseDTO.setId(Long.MIN_VALUE);
        Course course = new Course();
        course.setName(COURSE_NAME);
        course.setId(Long.MIN_VALUE);

        when(courseMockDAO.get(course.getId())).thenReturn(course);

        CourseDTO returned = courseService.get(courseDTO.getId());
        verify(courseMockDAO, times(1)).get(courseDTO.getId());
        verifyNoMoreInteractions(courseMockDAO);

        assertCourseDTO(returned, courseDTO);

    }

    @Test(expected = IllegalArgumentException.class)
    public void getNullId() throws  ServiceFailureException {
        Long ln = null;
        courseService.get(ln);
    }

    @Test
    public void getAll() {
        List<Course> courses = new ArrayList<Course>();
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setName(COURSE_NAME);
        courseDTO.setId(Long.MIN_VALUE);
        Course course = new Course();
        course.setName(COURSE_NAME);
        course.setId(Long.MIN_VALUE);
        courses.add(course);
        when(courseMockDAO.getAll()).thenReturn(courses);

        List<CourseDTO> returned = courseService.getAll();
        verify(courseMockDAO, times(1)).getAll();
        verifyNoMoreInteractions(courseMockDAO);

        List<CourseDTO> toComp = new ArrayList<CourseDTO>();
        toComp.add(courseDTO);

        assertEquals(returned.size(), toComp.size());
        assertCourseDTO(returned.get(0), toComp.get(0));
    }

    @Test
    public void findByNamePass() throws  ServiceFailureException{
        List<Course> courses = new ArrayList<Course>();
        String name = COURSE_NAME;
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setName(COURSE_NAME);
        courseDTO.setId(Long.MIN_VALUE);
        Course course = new Course();
        course.setName(COURSE_NAME);
        course.setId(Long.MIN_VALUE);
        courses.add(course);

        when(courseMockDAO.findByName(name)).thenReturn(courses);

        List<CourseDTO> returned = courseService.findByName(name);
        verify(courseMockDAO, times(1)).findByName(name);
        verifyNoMoreInteractions(courseMockDAO);

        List<CourseDTO> toComp = new ArrayList<CourseDTO>();
        toComp.add(courseDTO);

        assertEquals(returned.size(), toComp.size());
        assertCourseDTO(returned.get(0), toComp.get(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void findByNameNull() throws  ServiceFailureException {
        List<CourseDTO> returned = courseService.findByName(null);
    }

    @Test
    public void findByLanguage() throws  ServiceFailureException{
        List<Course> courses = new ArrayList<Course>();
        LanguageSpoken language = LanguageSpoken.RUSSIAN;
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setName(COURSE_NAME);
        courseDTO.setId(Long.MIN_VALUE);
        Course course = new Course();
        course.setName(COURSE_NAME);
        course.setId(Long.MIN_VALUE);
        courses.add(course);

        when(courseMockDAO.findByLanguage(language)).thenReturn(courses);

        List<CourseDTO> returned = courseService.findByLanguage(language);
        verify(courseMockDAO, times(1)).findByLanguage(language);
        verifyNoMoreInteractions(courseMockDAO);

        List<CourseDTO> toComp = new ArrayList<CourseDTO>();
        toComp.add(courseDTO);

        assertEquals(returned.size(), toComp.size());
        assertCourseDTO(returned.get(0), toComp.get(0));
    }

    private void assertCourse(Course course1, Course course2) {
        assertEquals(course1.getName(), course2.getName());
    }

    private void assertCourseDTO(CourseDTO courseDTO1, CourseDTO courseDTO2) {
        assertEquals(courseDTO1.getName(), courseDTO2.getName());
    }
}
