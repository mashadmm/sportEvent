package com.pa165.sportEventservice;


import com.pa165.sportEventpersistence.DAOImpl.GradeDAO;
import com.pa165.sportEventpersistence.DAOImpl.SportsmanDAO;
import com.pa165.sportEventpersistence.Exceptions.ServiceFailureException;
import com.pa165.sportEventpersistence.entities.Sportsman;
import com.pa165.sportEventservice.DTO.SportsmanDTO;
import com.pa165.sportEventservice.serviceImpl.SportsmanServiceImpl;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
 * @author Maria
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-test.xml"})
public class SportsmanServiceTest {

    private SportsmanServiceImpl sportsmanService;
    private SportsmanDAO sportsmanMockDAO;
    private GradeDAO gradeMockDAO;
    @Autowired
    private Mapper mapper;
    

    @Before
    public void setUp() {

        sportsmanService = new SportsmanServiceImpl();
        sportsmanMockDAO = mock(SportsmanDAO.class);
        sportsmanService.setSportsmanDAO(sportsmanMockDAO);
        gradeMockDAO = mock(GradeDAO.class);
        sportsmanService.setMapper(mapper);
    }

    @Test
    public void add() throws  ServiceFailureException, ParseException{
        SportsmanDTO sportsmanDTO = new SportsmanDTO();
        sportsmanDTO.setName("Jack");
        sportsmanDTO.setLastname("Snow");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
	String dateInString = "31-08-1982";
	java.util.Date date = sdf.parse(dateInString);
        sportsmanDTO.setDateOfBirth(date);
        Sportsman sportsman = new Sportsman();
        sportsman.setName("Jack");
        sportsman.setLastname("Snow");
        dateInString = "31-08-1982";
	date = sdf.parse(dateInString);
        sportsman.setDateOfBirth(date);

        when(sportsmanMockDAO.persist(any(Sportsman.class))).thenReturn(sportsman);
        SportsmanDTO returned = sportsmanService.add(sportsmanDTO);

        assertSportsmanDTO(returned, sportsmanDTO);

        ArgumentCaptor<Sportsman> sportsmanArgument = ArgumentCaptor.forClass(Sportsman.class);
        verify(sportsmanMockDAO, times(1)).persist(sportsmanArgument.capture());
        verifyNoMoreInteractions(sportsmanMockDAO);

        assertSportsman(sportsman, sportsmanArgument.getValue());
    }

    @Test(expected = IllegalArgumentException.class)
    public void addNull() throws  ServiceFailureException{
        SportsmanDTO returned = sportsmanService.add(null);
    }

    @Test
    public void remove() throws  ServiceFailureException, ParseException{
        SportsmanDTO sportsmanDTO = new SportsmanDTO();
        sportsmanDTO.setSportsmanId(5L);
        sportsmanDTO.setName("Jack");
        sportsmanDTO.setLastname("Snow");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
	String dateInString = "31-08-1982";
	java.util.Date date = sdf.parse(dateInString);
        sportsmanDTO.setDateOfBirth(date);
        Sportsman sportsman = new Sportsman();
        sportsman.setSportsmanId(5L);
        sportsman.setName("Jack");
        sportsman.setLastname("Snow");
        dateInString = "31-08-1982";
	date = sdf.parse(dateInString);
        sportsman.setDateOfBirth(date);
        when(sportsmanMockDAO.findById(any(Long.class))).thenReturn(sportsman);
        sportsmanService.remove(sportsmanDTO);

        verify(sportsmanMockDAO, times(1)).remove(sportsman);
        
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeNull() throws  ServiceFailureException{
        sportsmanService.remove(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeNullId() throws  ServiceFailureException, ParseException{
        SportsmanDTO sportsmanDTO = new SportsmanDTO();
        sportsmanDTO.setSportsmanId(null);
        sportsmanDTO.setName("Jack");
        sportsmanDTO.setLastname("Snow");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
	String dateInString = "31-08-1982";
	java.util.Date date = sdf.parse(dateInString);
        sportsmanDTO.setDateOfBirth(date);
        sportsmanService.remove(sportsmanDTO);
    }

    @Test
    public void edit() throws  ServiceFailureException, ParseException{
        SportsmanDTO sportsmanDTO = new SportsmanDTO();
        sportsmanDTO.setSportsmanId(5L);
        sportsmanDTO.setName("Jack");
        sportsmanDTO.setLastname("Snow");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
	String dateInString = "31-08-1982";
	java.util.Date date = sdf.parse(dateInString);
        sportsmanDTO.setDateOfBirth(date);
        Sportsman sportsman = new Sportsman();
        sportsman.setSportsmanId(5L);
        sportsman.setName("Jon");
        sportsman.setLastname("Snow");
        dateInString = "31-08-1982";
	date = sdf.parse(dateInString);
        sportsman.setDateOfBirth(date);

        when(sportsmanMockDAO.edit(any(Sportsman.class))).thenReturn(sportsman);

        SportsmanDTO returned = sportsmanService.edit(sportsmanDTO);

        verify(sportsmanMockDAO, times(1)).edit(sportsman);
        verifyNoMoreInteractions(sportsmanMockDAO);
        assertSportsmanDTO(returned, sportsmanDTO);
    }

    @Test(expected = IllegalArgumentException.class)
    public void editNull() throws  ServiceFailureException{
        sportsmanService.edit(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void editNullId() throws  ServiceFailureException, ParseException{
        SportsmanDTO sportsmanDTO = new SportsmanDTO();
        sportsmanDTO.setSportsmanId(null);
        sportsmanDTO.setName("Jack");
        sportsmanDTO.setLastname("Snow");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
	String dateInString = "31-08-1982";
	java.util.Date date = sdf.parse(dateInString);
        sportsmanDTO.setDateOfBirth(date);
        
        sportsmanService.edit(sportsmanDTO);
    }

    @Test
    public void getById() throws  ServiceFailureException, ParseException{
        SportsmanDTO sportsmanDTO = new SportsmanDTO();
        sportsmanDTO.setSportsmanId(5L);
        sportsmanDTO.setName("Jack");
        sportsmanDTO.setLastname("Snow");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
	String dateInString = "31-08-1982";
	java.util.Date date = sdf.parse(dateInString);
        sportsmanDTO.setDateOfBirth(date);
        Sportsman sportsman = new Sportsman();
        sportsman.setSportsmanId(5L);
        sportsman.setName("Jack");
        sportsman.setLastname("Snow");
        dateInString = "31-08-1982";
	date = sdf.parse(dateInString);
        sportsman.setDateOfBirth(date);

        when(sportsmanMockDAO.findById(sportsman.getSportsmanId())).thenReturn(sportsman);

        SportsmanDTO returned = sportsmanService.findById(sportsmanDTO.getSportsmanId());
        verify(sportsmanMockDAO, times(1)).findById(sportsmanDTO.getSportsmanId());
        verifyNoMoreInteractions(sportsmanMockDAO);

        assertSportsmanDTO(returned, sportsmanDTO);

    }

    @Test(expected = IllegalArgumentException.class)
    public void getNullId() throws  ServiceFailureException{
        Long id = null;
        sportsmanService.findById(id);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findByLastnameNull() throws  ServiceFailureException{
        SportsmanDTO sportsmanDTO = new SportsmanDTO();
        sportsmanDTO.setLastname("Smith");
        sportsmanService.findByLastname(null);
    }

    @Test
    public void getAll() throws  ServiceFailureException, ParseException{
        List<Sportsman> sportsmans = new ArrayList<Sportsman>();
        SportsmanDTO sportsmanDTO = new SportsmanDTO();
        sportsmanDTO.setSportsmanId(5L);
        sportsmanDTO.setName("Jack");
        sportsmanDTO.setLastname("Snow");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
	String dateInString = "31-08-1982";
	java.util.Date date = sdf.parse(dateInString);
        sportsmanDTO.setDateOfBirth(date);
        Sportsman sportsman = new Sportsman();
        sportsman.setSportsmanId(5L);
        sportsman.setName("Jack");
        sportsman.setLastname("Snow");
        dateInString = "31-08-1982";
	date = sdf.parse(dateInString);
        sportsman.setDateOfBirth(date);
        sportsmans.add(sportsman);
        when(sportsmanMockDAO.findAll()).thenReturn(sportsmans);

        List<SportsmanDTO> returned = sportsmanService.getAll();
        verify(sportsmanMockDAO, times(1)).findAll();
        verifyNoMoreInteractions(sportsmanMockDAO);

        List<SportsmanDTO> toComp = new ArrayList<SportsmanDTO>();
        toComp.add(sportsmanDTO);

        assertEquals(returned.size(), toComp.size());
        assertSportsmanDTO(returned.get(0), toComp.get(0));
    }

    @Test
    public void findByLastname() throws  ServiceFailureException, ParseException{
        List<Sportsman> sportsmans = new ArrayList<Sportsman>();
        SportsmanDTO sportsmanDTO = new SportsmanDTO();
        sportsmanDTO.setSportsmanId(5L);
        sportsmanDTO.setName("Jack");
        String lastname = "Snow";
        sportsmanDTO.setLastname(lastname);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
	String dateInString = "31-08-1982";
	java.util.Date date = sdf.parse(dateInString);
        sportsmanDTO.setDateOfBirth(date);
        Sportsman sportsman = new Sportsman();
        sportsman.setSportsmanId(5L);
        sportsman.setName("Jack");
        sportsman.setLastname(lastname);
        dateInString = "31-08-1982";
	date = sdf.parse(dateInString);
        sportsman.setDateOfBirth(date);
        sportsmans.add(sportsman);

        when(sportsmanMockDAO.findByLastname(lastname)).thenReturn(sportsmans);

        List<SportsmanDTO> returned = sportsmanService.findByLastname(lastname);
        verify(sportsmanMockDAO, times(1)).findByLastname(lastname);
        verifyNoMoreInteractions(sportsmanMockDAO);

        List<SportsmanDTO> toComp = new ArrayList<SportsmanDTO>();
        toComp.add(sportsmanDTO);

        assertEquals(returned.size(), toComp.size());
        assertSportsmanDTO(returned.get(0), toComp.get(0));
    }

   
    private void assertSportsman(Sportsman expected, Sportsman actual) {
        assertEquals(expected.getSportsmanId(), actual.getSportsmanId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getLastname(), actual.getLastname());
        assertEquals(expected.getDateOfBirth(), actual.getDateOfBirth());
    }

    private void assertSportsmanDTO(SportsmanDTO expected, SportsmanDTO actual) {
        assertEquals(expected.getSportsmanId(), actual.getSportsmanId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getLastname(), actual.getLastname());
        assertEquals(expected.getDateOfBirth(), actual.getDateOfBirth());
    }

    
}
