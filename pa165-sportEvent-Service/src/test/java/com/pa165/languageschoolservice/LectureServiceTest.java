/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pa165.languageschoolservice;

import com.pa165.languageschoolpersistence.DAO.CourseDAO;
import com.pa165.languageschoolpersistence.DAO.LectorDAO;
import com.pa165.languageschoolpersistence.DAO.LectureDAO;
import com.pa165.languageschoolpersistence.DAOImpl.CourseDAOImpl;
import com.pa165.languageschoolpersistence.DAOImpl.LectorDAOImpl;
import com.pa165.languageschoolpersistence.DAOImpl.LectureDAOImpl;
import com.pa165.languageschoolpersistence.Exceptions.ServiceFailureException;
import com.pa165.languageschoolpersistence.entities.Course;
import com.pa165.languageschoolpersistence.entities.Lector;
import com.pa165.languageschoolpersistence.entities.Lecture;
import com.pa165.languageschoolservice.DTO.CourseDTO;
import com.pa165.languageschoolservice.DTO.LectorDTO;
import com.pa165.languageschoolservice.DTO.LectureDTO;
import com.pa165.languageschoolservice.serviceImpl.LectureServiceImpl;
import java.sql.Date;
import java.sql.Timestamp;
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
 * @author Tomas Hruby
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-test.xml"})
public class LectureServiceTest {

    private LectureServiceImpl lectureService;
    private LectorDAO mockedLectorDao;
    private CourseDAO mockedCourseDao;
    private LectureDAO mockedLectureDao;
    private Date STARTTIME = Date.valueOf("2013-11-1");
    private Date ENDTIME = Date.valueOf("2014-11-1");
    private String ROOM = "aula";
    @Autowired
    private Mapper mapper;

    @Before
    public void setUp() {
        lectureService = new LectureServiceImpl();

        mockedCourseDao = mock(CourseDAOImpl.class);
        lectureService.setCourseDao(mockedCourseDao);

        mockedLectorDao = mock(LectorDAOImpl.class);
        lectureService.setLectorDao(mockedLectorDao);

        mockedLectureDao = mock(LectureDAOImpl.class);
        lectureService.setLectureDao(mockedLectureDao);

        lectureService.setMapper(mapper);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addNull() throws  ServiceFailureException {
        LectureDTO returned = lectureService.add(null);
    }

    @Test
    public void addPass() throws  ServiceFailureException{
        LectureDTO lectureDTO = new LectureDTO();
        lectureDTO.setStartTime(STARTTIME);
        lectureDTO.setEndTime(ENDTIME);
        lectureDTO.setRoomNumber(ROOM);

        Lecture lecture = new Lecture();
        lecture.setStartTime(new Timestamp(STARTTIME.getTime()));
        lecture.setEndTime(new Timestamp(ENDTIME.getTime()));
        lecture.setRoomNumber(ROOM);

        when(mockedLectureDao.add(any(Lecture.class))).thenReturn(lecture);

        LectureDTO returned = lectureService.add(lectureDTO);

        assertEqualsDTO(returned, lectureDTO);

        ArgumentCaptor<Lecture> argument = ArgumentCaptor.forClass(Lecture.class);
        verify(mockedLectureDao, times(1)).add(argument.capture());
        verifyNoMoreInteractions(mockedLectureDao);

    }

    private void assertEqualsDTO(LectureDTO first, LectureDTO second) {
        assertEquals(second.getStartTime(), first.getStartTime());
        assertEquals(second.getEndTime(), first.getEndTime());
        assertEquals(second.getRoomNumber(), first.getRoomNumber());
    }

    @Test
    public void removePass() throws  ServiceFailureException{
        LectureDTO lectureDTO = new LectureDTO();
        lectureDTO.setStartTime(STARTTIME);
        lectureDTO.setEndTime(ENDTIME);
        lectureDTO.setRoomNumber(ROOM);

        Lecture lecture = new Lecture();
        lecture.setStartTime(new Timestamp(STARTTIME.getTime()));
        lecture.setEndTime(new Timestamp(ENDTIME.getTime()));
        lecture.setRoomNumber(ROOM);

        lectureService.remove(lectureDTO);

        verify(mockedLectureDao, times(1)).remove(lecture);
        verifyNoMoreInteractions(mockedLectureDao);
    }

    @Test
    public void modifyPass() throws  ServiceFailureException{
        LectureDTO lectureDTO = new LectureDTO();
        lectureDTO.setStartTime(STARTTIME);
        lectureDTO.setEndTime(ENDTIME);
        lectureDTO.setRoomNumber(ROOM);

        Lecture lecture = new Lecture();
        lecture.setStartTime(new Timestamp(STARTTIME.getTime()));
        lecture.setEndTime(new Timestamp(ENDTIME.getTime()));
        lecture.setRoomNumber("room1");

        lectureService.add(lectureDTO);

        lectureDTO.setRoomNumber("room1");

        LectureDTO returned = lectureService.modify(lectureDTO);

        verify(mockedLectureDao, times(1)).modify(lecture);
        assertEqualsDTO(returned, lectureDTO);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getNullId() throws  ServiceFailureException{
        Long ln = null;
        lectureService.get(ln);
    }

    @Test
    public void getPass() throws  ServiceFailureException{
        LectureDTO lectureDTO = new LectureDTO();
        lectureDTO.setId(Long.MIN_VALUE);
        lectureDTO.setStartTime(STARTTIME);
        lectureDTO.setEndTime(ENDTIME);
        lectureDTO.setRoomNumber(ROOM);

        Lecture lecture = new Lecture();
        lecture.setId(Long.MIN_VALUE);
        lecture.setStartTime(new Timestamp(STARTTIME.getTime()));
        lecture.setEndTime(new Timestamp(ENDTIME.getTime()));
        lecture.setRoomNumber(ROOM);

        lectureDTO = lectureService.add(lectureDTO);
        when(mockedLectureDao.get(lecture.getId())).thenReturn(lecture);
        LectureDTO returned = lectureService.get(lectureDTO.getId());
        verify(mockedLectureDao, times(1)).get(lecture.getId());

        assertEqualsDTO(returned, lectureDTO);
    }

    @Test
    public void getByLectorPass() throws  ServiceFailureException{
        LectureDTO lectureDTO = new LectureDTO();
        lectureDTO.setStartTime(STARTTIME);
        lectureDTO.setEndTime(ENDTIME);
        lectureDTO.setRoomNumber(ROOM);

        LectorDTO lectorDTO = new LectorDTO();
        lectorDTO.setName("john");

        Lecture lecture = new Lecture();
        lecture.setStartTime(new Timestamp(STARTTIME.getTime()));
        lecture.setEndTime(new Timestamp(ENDTIME.getTime()));
        lecture.setRoomNumber(ROOM);

        Lector lector = new Lector();
        lector.setName("john");

        List<Lecture> lectures = new ArrayList<Lecture>();
        when(mockedLectureDao.getLectures(any(Lector.class))).thenReturn(lectures);

        List<LectureDTO> returned = lectureService.getLectures(lectorDTO);
        verify(mockedLectureDao, times(1)).getLectures(lector);
        verifyNoMoreInteractions(mockedLectureDao);

        assertEquals(returned, new ArrayList<Lecture>());
    }

    @Test
    public void getByCoursePass() throws  ServiceFailureException{
        LectureDTO lectureDTO = new LectureDTO();
        lectureDTO.setStartTime(STARTTIME);
        lectureDTO.setEndTime(ENDTIME);
        lectureDTO.setRoomNumber(ROOM);

        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setName("english");

        Lecture lecture = new Lecture();
        lecture.setStartTime(new Timestamp(STARTTIME.getTime()));
        lecture.setEndTime(new Timestamp(ENDTIME.getTime()));
        lecture.setRoomNumber(ROOM);

        Course course = new Course();
        course.setName("english");

        List<Lecture> lectures = new ArrayList<Lecture>();
        when(mockedLectureDao.getLectures(any(Course.class))).thenReturn(lectures);

        List<LectureDTO> returned = lectureService.getLectures(courseDTO);
        verify(mockedLectureDao, times(1)).getLectures(course);
        verifyNoMoreInteractions(mockedLectureDao);

        assertEquals(returned, new ArrayList<Lecture>());
    }

    @Test
    public void getAllPass() throws  ServiceFailureException{
        LectureDTO lectureDTO = new LectureDTO();
        lectureDTO.setStartTime(STARTTIME);
        lectureDTO.setEndTime(ENDTIME);
        lectureDTO.setRoomNumber(ROOM);

        Lecture lecture = new Lecture();
        lecture.setStartTime(new Timestamp(STARTTIME.getTime()));
        lecture.setEndTime(new Timestamp(ENDTIME.getTime()));
        lecture.setRoomNumber(ROOM);

        lectureService.add(lectureDTO);

        List<Lecture> lectures = new ArrayList<Lecture>();
        lectures.add(lecture);

        when(mockedLectureDao.getAll()).thenReturn(lectures);

        List<LectureDTO> returned = lectureService.getAll();
        verify(mockedLectureDao, times(1)).getAll();

        assertEquals(returned.size(), lectures.size());
    }
}
