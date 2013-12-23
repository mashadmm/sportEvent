/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pa165.languageschoolservice;

import com.pa165.languageschoolpersistence.DAO.LectorDAO;
import com.pa165.languageschoolpersistence.DAO.LectureDAO;
import com.pa165.languageschoolpersistence.DAOImpl.LectorDAOImpl;
import com.pa165.languageschoolpersistence.DAOImpl.LectureDAOImpl;
import com.pa165.languageschoolpersistence.Exceptions.ServiceFailureException;
import com.pa165.languageschoolpersistence.entities.Lector;
import com.pa165.languageschoolpersistence.entities.Lecture;
import com.pa165.languageschoolservice.DTO.LectorDTO;
import com.pa165.languageschoolservice.DTO.LectureDTO;
import com.pa165.languageschoolservice.service.LectorService;
import com.pa165.languageschoolservice.serviceImpl.LectorServiceImpl;
import com.pa165.languageschoolservice.serviceImpl.LectureServiceImpl;
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
 * @author Elena Medvedeva
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-test.xml"})
public class LectorServiceTest {

    private LectorDAO lectorMockDAO;
    private LectureDAO lectureMockDAO;
    private LectorServiceImpl lectorService;
    @Autowired
    private Mapper mapper;
    public static String LECTOR_NAME = "Ira";

    @Before
    public void setUp() {
        lectorService = new LectorServiceImpl();

        lectorMockDAO = mock(LectorDAOImpl.class);
        lectorService.setLectorDAO(lectorMockDAO);

        lectureMockDAO = mock(LectureDAOImpl.class);
        lectorService.setLectureDAO(lectureMockDAO);

        lectorService.setMapper(mapper);
    }

    @Test
    public void addPass() throws  ServiceFailureException{
        LectorDTO lectorDTO = new LectorDTO();
        lectorDTO.setName(LECTOR_NAME);
        Lector lector = new Lector();
        lector.setName(LECTOR_NAME);

        when(lectorMockDAO.add(any(Lector.class))).thenReturn(lector);

        LectorDTO returned = lectorService.add(lectorDTO);

        assertLectorDTO(returned, lectorDTO);

        ArgumentCaptor<Lector> lectorArgument = ArgumentCaptor.forClass(Lector.class);
        verify(lectorMockDAO, times(1)).add(lectorArgument.capture());
        verifyNoMoreInteractions(lectorMockDAO);

        assertLector(lectorArgument.getValue(), lector);

    }

    @Test(expected = IllegalArgumentException.class)
    public void add_null() throws  ServiceFailureException {
        LectorDTO returned = lectorService.add(null);
    }

    @Test
    public void removePass() throws  ServiceFailureException {
        LectorDTO lectorDTO = new LectorDTO();
        lectorDTO.setId(Long.MIN_VALUE);
        lectorDTO.setName(LECTOR_NAME);
        Lector lector = new Lector();
        lector.setId(Long.MIN_VALUE);
        lector.setName(LECTOR_NAME);

        lectorService.remove(lectorDTO);

        verify(lectorMockDAO, times(1)).remove(lector);
        verifyNoMoreInteractions(lectorMockDAO);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeNull() throws  ServiceFailureException{
        lectorService.remove(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeNullId() throws  ServiceFailureException {
        LectorDTO lectorDTO = new LectorDTO();
        lectorDTO.setId(null);
        lectorDTO.setName(LECTOR_NAME);
        lectorService.remove(lectorDTO);
    }

    @Test
    public void modifyPass() throws  ServiceFailureException{
        LectorDTO lectorDTO = new LectorDTO();
        lectorDTO.setId(Long.MIN_VALUE);
        lectorDTO.setName(LECTOR_NAME);
        Lector lector = new Lector();
        lector.setId(Long.MIN_VALUE);
        lector.setName(LECTOR_NAME);

        when(lectorMockDAO.modify(any(Lector.class))).thenReturn(lector);

        LectorDTO returned = lectorService.modify(lectorDTO);

        verify(lectorMockDAO, times(1)).modify(lector);
        verifyNoMoreInteractions(lectorMockDAO);
        assertLectorDTO(returned, lectorDTO);
    }

    @Test(expected = IllegalArgumentException.class)
    public void modifyNull() throws  ServiceFailureException {
        lectorService.modify(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void modifyNullId() throws  ServiceFailureException {
        LectorDTO lectorDTO = new LectorDTO();
        lectorDTO.setName(LECTOR_NAME);
        lectorDTO.setId(null);

        lectorService.modify(lectorDTO);
    }

    @Test
    public void getById() throws  ServiceFailureException{
        LectorDTO lectorDTO = new LectorDTO();
        lectorDTO.setName(LECTOR_NAME);
        lectorDTO.setId(Long.MIN_VALUE);
        Lector lector = new Lector();
        lector.setName(LECTOR_NAME);
        lector.setId(Long.MIN_VALUE);

        when(lectorMockDAO.get(lector.getId())).thenReturn(lector);

        LectorDTO returned = lectorService.get(lectorDTO.getId());
        verify(lectorMockDAO, times(1)).get(lectorDTO.getId());
        verifyNoMoreInteractions(lectorMockDAO);

        assertLectorDTO(returned, lectorDTO);

    }

    @Test(expected = IllegalArgumentException.class)
    public void getNullId() throws  ServiceFailureException{
        Long ln = null;
        lectorService.get(ln);
    }

    @Test
    public void getAll() {
        List<Lector> lectors = new ArrayList<Lector>();
        LectorDTO lectorDTO = new LectorDTO();
        lectorDTO.setName(LECTOR_NAME);
        lectorDTO.setId(Long.MIN_VALUE);
        Lector lector = new Lector();
        lector.setName(LECTOR_NAME);
        lector.setId(Long.MIN_VALUE);
        lectors.add(lector);
        when(lectorMockDAO.getAll()).thenReturn(lectors);

        List<LectorDTO> returned = lectorService.getAll();
        verify(lectorMockDAO, times(1)).getAll();
        verifyNoMoreInteractions(lectorMockDAO);

        List<LectorDTO> toComp = new ArrayList<LectorDTO>();
        toComp.add(lectorDTO);

        assertEquals(returned.size(), toComp.size());
        assertLectorDTO(returned.get(0), toComp.get(0));
    }

    @Test
    public void findByNamePass() throws  ServiceFailureException{
        List<Lector> lectors = new ArrayList<Lector>();
        String name = LECTOR_NAME;
        LectorDTO lectorDTO = new LectorDTO();
        lectorDTO.setName(LECTOR_NAME);
        lectorDTO.setId(Long.MIN_VALUE);
        Lector lector = new Lector();
        lector.setName(LECTOR_NAME);
        lector.setId(Long.MIN_VALUE);
        lectors.add(lector);

        when(lectorMockDAO.findByName(name)).thenReturn(lectors);

        List<LectorDTO> returned = lectorService.findByName(name);
        verify(lectorMockDAO, times(1)).findByName(name);
        verifyNoMoreInteractions(lectorMockDAO);

        List<LectorDTO> toComp = new ArrayList<LectorDTO>();
        toComp.add(lectorDTO);

        assertEquals(returned.size(), toComp.size());
        assertLectorDTO(returned.get(0), toComp.get(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void findByNameNull() throws  ServiceFailureException{
        List<LectorDTO> returned = lectorService.findByName(null);
    }

    @Test
    public void enrollLecture() throws  ServiceFailureException{
        LectorDTO lectorDTO = new LectorDTO();
        lectorDTO.setId(Long.MIN_VALUE);
        lectorDTO.setName(LECTOR_NAME);
        Lector lector = new Lector();
        lector.setId(Long.MIN_VALUE);
        lector.setName(LECTOR_NAME);

        LectureDTO lectureDTO = new LectureDTO();
        lectureDTO.setRoomNumber("123");
        lectureDTO.setId(Long.MIN_VALUE);
        Lecture lecture = new Lecture();
        lecture.setRoomNumber("123");
        lecture.setId(Long.MIN_VALUE);

        when(lectorMockDAO.enrollLecture(any(Lecture.class), any(Lector.class))).thenReturn(lecture);

        when(lectorMockDAO.get(lectorDTO.getId())).thenReturn(lector);
        when(lectureMockDAO.get(lectureDTO.getId())).thenReturn(lecture);

        LectureDTO returned = lectorService.enrollLecture(lectureDTO, lectorDTO);

        assertLectureDTO(returned, lectureDTO);


        verify(lectorMockDAO, times(1)).enrollLecture(lecture, lector);

    }

    @Test(expected = IllegalArgumentException.class)
    public void enrollLectureNull1() throws  ServiceFailureException{
        LectorDTO lectorDTO = new LectorDTO();
        lectorDTO.setId(Long.MIN_VALUE);
        lectorDTO.setName(LECTOR_NAME);
        LectureDTO returned = lectorService.enrollLecture(null, lectorDTO);
    }

    @Test(expected = IllegalArgumentException.class)
    public void enrollLectureNull2() throws  ServiceFailureException{
        LectureDTO lectureDTO = new LectureDTO();
        lectureDTO.setRoomNumber("123");
        lectureDTO.setId(Long.MIN_VALUE);
        LectureDTO returned = lectorService.enrollLecture(lectureDTO, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void enrollLectureNullId1() throws  ServiceFailureException{
        LectureDTO lectureDTO = new LectureDTO();
        lectureDTO.setRoomNumber("123");
        lectureDTO.setId(Long.MIN_VALUE);

        LectorDTO lectorDTO = new LectorDTO();
        lectorDTO.setName(LECTOR_NAME);

        LectureDTO returned = lectorService.enrollLecture(lectureDTO, lectorDTO);
    }

    @Test(expected = IllegalArgumentException.class)
    public void enrollLectureNullId2() throws  ServiceFailureException{
        LectureDTO lectureDTO = new LectureDTO();
        lectureDTO.setRoomNumber("123");

        LectorDTO lectorDTO = new LectorDTO();
        lectorDTO.setId(Long.MIN_VALUE);
        LectureDTO returned = lectorService.enrollLecture(lectureDTO, lectorDTO);

    }

    private void assertLector(Lector lector1, Lector lector2) {
        assertEquals(lector1.getName(), lector2.getName());
    }

    private void assertLectorDTO(LectorDTO lectorDTO1, LectorDTO lectorDTO2) {
        assertEquals(lectorDTO1.getName(), lectorDTO2.getName());
    }

    private void assertLectureDTO(LectureDTO lectureDTO1, LectureDTO lectureDTO2) {
        assertEquals(lectureDTO1.getId(), lectureDTO2.getId());
        assertEquals(lectureDTO1.getRoomNumber(), lectureDTO2.getRoomNumber());
    }
}
