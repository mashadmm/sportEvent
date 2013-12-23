package com.pa165.languageschoolservice;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import com.pa165.languageschoolpersistence.DAO.LectorDAO;
import com.pa165.languageschoolpersistence.DAO.LevelOfLanguageDAO;
import com.pa165.languageschoolpersistence.DAOImpl.LectorDAOImpl;
import com.pa165.languageschoolpersistence.DAOImpl.LevelOfLanguageDAOImpl;
import com.pa165.languageschoolpersistence.Exceptions.IllegalArgumentPersistenceException;
import com.pa165.languageschoolpersistence.Exceptions.ServiceFailureException;
import com.pa165.languageschoolpersistence.entities.LanguageLevel;
import com.pa165.languageschoolpersistence.entities.LanguageSpoken;
import com.pa165.languageschoolpersistence.entities.Lector;
import com.pa165.languageschoolpersistence.entities.LevelofLanguage;
import com.pa165.languageschoolservice.DTO.LectorDTO;
import com.pa165.languageschoolservice.DTO.LevelofLanguageDTO;
import com.pa165.languageschoolservice.service.LevelOfLanguageService;
import com.pa165.languageschoolservice.serviceImpl.LevelOfLanguageServiceImpl;
import java.util.ArrayList;
import java.util.List;
import org.dozer.Mapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import static org.mockito.Mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import static junit.framework.Assert.assertEquals;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author ElenaMededeva
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-test.xml"})
public class LevelOfLanguageServiceTest {

    private LevelOfLanguageServiceImpl levelOfLanguageService;
    private LectorDAO lectorDAO;
    private LevelOfLanguageDAO levelOfLanguageMockDAO;
    @Autowired
    private Mapper mapper;
    public static String LECTOR_NAME = "Ira";

    @Before
    public void setUp() {
        // mapper = (Mapper)context.getBean("mapper");
        levelOfLanguageService = new LevelOfLanguageServiceImpl();

        levelOfLanguageMockDAO = mock(LevelOfLanguageDAOImpl.class);
        levelOfLanguageService.setLevelOfLanguageDAO(levelOfLanguageMockDAO);

        lectorDAO = mock(LectorDAOImpl.class);
        levelOfLanguageService.setMapper(mapper);
    }

    @Test
    public void addPass() throws  ServiceFailureException {
        LectorDTO lectorDTO = new LectorDTO();
        lectorDTO.setName(LECTOR_NAME);
        Lector lector = new Lector();
        lector.setName(LECTOR_NAME);

        LevelofLanguageDTO lvlDTO = new LevelofLanguageDTO();
        lvlDTO.setLanguageSpoken(LanguageSpoken.RUSSIAN);
        lvlDTO.setLevel(LanguageLevel.NATIVE);
        lvlDTO.setLector(lectorDTO);
        LevelofLanguage lvl = new LevelofLanguage();
        lvl.setLanguageSpoken(LanguageSpoken.RUSSIAN);
        lvl.setLevel(LanguageLevel.NATIVE);
        lvl.setLector(lector);

        when(levelOfLanguageMockDAO.add(any(LevelofLanguage.class))).thenReturn(lvl);

        LevelofLanguageDTO returned = levelOfLanguageService.add(lvlDTO);

        assertLevelOfLanguageDTO(returned, lvlDTO);

        ArgumentCaptor<LevelofLanguage> lvlArgument = ArgumentCaptor.forClass(LevelofLanguage.class);
        verify(levelOfLanguageMockDAO, times(1)).add(lvlArgument.capture());
        verifyNoMoreInteractions(levelOfLanguageMockDAO);

        assertLevelOfLanguage(lvlArgument.getValue(), lvl);

    }

    @Test(expected = IllegalArgumentException.class)
    public void addNull() throws  ServiceFailureException{
        LevelofLanguageDTO returned = levelOfLanguageService.add(null);
    }

    @Test
    public void removePass() throws  ServiceFailureException{
        LectorDTO lectorDTO = new LectorDTO();
        lectorDTO.setName(LECTOR_NAME);
        Lector lector = new Lector();
        lector.setName(LECTOR_NAME);

        LevelofLanguageDTO lvlDTO = new LevelofLanguageDTO();
        lvlDTO.setId(Long.MIN_VALUE);
        lvlDTO.setLanguageSpoken(LanguageSpoken.RUSSIAN);
        lvlDTO.setLevel(LanguageLevel.NATIVE);
        lvlDTO.setLector(lectorDTO);

        LevelofLanguage lvl = new LevelofLanguage();
        lvl.setId(Long.MIN_VALUE);
        lvl.setLanguageSpoken(LanguageSpoken.RUSSIAN);
        lvl.setLevel(LanguageLevel.NATIVE);
        lvl.setLector(lector);

        levelOfLanguageService.remove(lvlDTO);

        verify(levelOfLanguageMockDAO, times(1)).remove(lvl);
        verifyNoMoreInteractions(levelOfLanguageMockDAO);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeNull() throws  ServiceFailureException{
        levelOfLanguageService.remove(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeNullId() throws  ServiceFailureException{
        LectorDTO lectorDTO = new LectorDTO();
        lectorDTO.setName(LECTOR_NAME);
        LevelofLanguageDTO lvlDTO = new LevelofLanguageDTO();
        lvlDTO.setId(null);
        lvlDTO.setLanguageSpoken(LanguageSpoken.RUSSIAN);
        lvlDTO.setLevel(LanguageLevel.NATIVE);
        lvlDTO.setLector(lectorDTO);
        levelOfLanguageService.remove(lvlDTO);
    }

    @Test
    public void modifyPass() throws  ServiceFailureException {
        LectorDTO lectorDTO = new LectorDTO();
        lectorDTO.setName(LECTOR_NAME);
        Lector lector = new Lector();
        lector.setName(LECTOR_NAME);

        LevelofLanguageDTO lvlDTO = new LevelofLanguageDTO();
        lvlDTO.setId(Long.MIN_VALUE);
        lvlDTO.setLanguageSpoken(LanguageSpoken.RUSSIAN);
        lvlDTO.setLevel(LanguageLevel.NATIVE);
        lvlDTO.setLector(lectorDTO);

        LevelofLanguage lvl = new LevelofLanguage();
        lvl.setId(Long.MIN_VALUE);
        lvl.setLanguageSpoken(LanguageSpoken.RUSSIAN);
        lvl.setLevel(LanguageLevel.NATIVE);
        lvl.setLector(lector);

        when(levelOfLanguageMockDAO.modify(any(LevelofLanguage.class))).thenReturn(lvl);

        LevelofLanguageDTO returned = levelOfLanguageService.modify(lvlDTO);

        verify(levelOfLanguageMockDAO, times(1)).modify(lvl);
        verifyNoMoreInteractions(levelOfLanguageMockDAO);
        assertLevelOfLanguageDTO(returned, lvlDTO);
    }

    @Test(expected = IllegalArgumentException.class)
    public void modifyNull() throws  ServiceFailureException{
        levelOfLanguageService.modify(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void modifyNullId() throws  ServiceFailureException {
        LectorDTO lectorDTO = new LectorDTO();
        lectorDTO.setName(LECTOR_NAME);
        LevelofLanguageDTO lvlDTO = new LevelofLanguageDTO();
        lvlDTO.setId(null);
        lvlDTO.setLanguageSpoken(LanguageSpoken.RUSSIAN);
        lvlDTO.setLevel(LanguageLevel.NATIVE);
        lvlDTO.setLector(lectorDTO);
        levelOfLanguageService.modify(lvlDTO);
    }

    @Test
    public void getById() throws  ServiceFailureException {
        LectorDTO lectorDTO = new LectorDTO();
        lectorDTO.setName(LECTOR_NAME);
        Lector lector = new Lector();
        lector.setName(LECTOR_NAME);

        LevelofLanguageDTO lvlDTO = new LevelofLanguageDTO();
        lvlDTO.setId(Long.MIN_VALUE);
        lvlDTO.setLanguageSpoken(LanguageSpoken.RUSSIAN);
        lvlDTO.setLevel(LanguageLevel.NATIVE);
        lvlDTO.setLector(lectorDTO);
        LevelofLanguage lvl = new LevelofLanguage();
        lvl.setId(Long.MIN_VALUE);
        lvl.setLanguageSpoken(LanguageSpoken.RUSSIAN);
        lvl.setLevel(LanguageLevel.NATIVE);
        lvl.setLector(lector);

        when(levelOfLanguageMockDAO.get(lvl.getId())).thenReturn(lvl);

        LevelofLanguageDTO returned = levelOfLanguageService.get(lvlDTO.getId());
        verify(levelOfLanguageMockDAO, times(1)).get(lvlDTO.getId());
        verifyNoMoreInteractions(levelOfLanguageMockDAO);

        assertLevelOfLanguageDTO(returned, lvlDTO);

    }

    @Test(expected = IllegalArgumentException.class)
    public void getNullId() throws  ServiceFailureException {
        Long ln = null;
        levelOfLanguageService.get(ln);
    }

    @Test
    public void getByLector() throws  ServiceFailureException {
        List<LevelofLanguage> langs = new ArrayList<LevelofLanguage>();
        Lector lector = new Lector();
        lector.setName(LECTOR_NAME);
        lector.setId(Long.MIN_VALUE);
        LectorDTO lectorDTO = new LectorDTO();
        lectorDTO.setId(Long.MIN_VALUE);
        lectorDTO.setName(LECTOR_NAME);
        LevelofLanguage lvl = new LevelofLanguage();
        lvl.setId(Long.MIN_VALUE);
        lvl.setLanguageSpoken(LanguageSpoken.RUSSIAN);
        lvl.setLevel(LanguageLevel.NATIVE);
        lvl.setLector(lector);
        langs.add(lvl);


        when(levelOfLanguageMockDAO.get(lector)).thenReturn(langs);

        List<LevelofLanguageDTO> returned = levelOfLanguageService.getByLector(lectorDTO);
        verify(levelOfLanguageMockDAO, times(1)).get(lector);
        verifyNoMoreInteractions(levelOfLanguageMockDAO);

        List<LevelofLanguageDTO> toComp = new ArrayList<LevelofLanguageDTO>();
        LevelofLanguageDTO lvlDTO = new LevelofLanguageDTO();
        lvlDTO.setId(Long.MIN_VALUE);
        lvlDTO.setLanguageSpoken(LanguageSpoken.RUSSIAN);
        lvlDTO.setLevel(LanguageLevel.NATIVE);
        lvlDTO.setLector(lectorDTO);
        toComp.add(lvlDTO);

        assertEquals(returned.size(), toComp.size());
        assertLevelOfLanguageDTO(returned.get(0), toComp.get(0));

    }

    @Test(expected = IllegalArgumentException.class)
    public void getByLectorNull() throws  ServiceFailureException{
        LectorDTO lectorDTO = null;
        levelOfLanguageService.getByLector(lectorDTO);
    }

    private void assertLevelOfLanguage(LevelofLanguage lvl1, LevelofLanguage lvl2) {
        assertEquals(lvl1.getLanguageSpoken().name(), lvl2.getLanguageSpoken().name());
        assertEquals(lvl1.getLevel().name(), lvl2.getLevel().name());
        assertLector(lvl1.getLector(), lvl2.getLector());
    }

    private void assertLector(Lector lector1, Lector lector2) {
        assertEquals(lector1.getName(), lector2.getName());
    }

    private void assertLevelOfLanguageDTO(LevelofLanguageDTO lvlDTO1, LevelofLanguageDTO lvlDTO2) {
        assertEquals(lvlDTO1.getLanguageSpoken().name(), lvlDTO2.getLanguageSpoken().name());
        assertEquals(lvlDTO1.getLevel().name(), lvlDTO2.getLevel().name());
        assertLectorDTO(lvlDTO1.getLector(), lvlDTO2.getLector());
    }

    private void assertLectorDTO(LectorDTO lectorDTO1, LectorDTO lectorDTO2) {
        assertEquals(lectorDTO1.getName(), lectorDTO2.getName());
    }
}
