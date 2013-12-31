package com.pa165.sportEventpresentation;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import com.pa165.sportEventpersistence.Exceptions.ServiceFailureException;
import com.pa165.sportEventservice.DTO.EventDTO;
import com.pa165.sportEventservice.DTO.GradeDTO;
import com.pa165.sportEventservice.DTO.SportDTO;
import com.pa165.sportEventservice.DTO.SportsmanDTO;
import com.pa165.sportEventservice.service.EventService;
import com.pa165.sportEventservice.service.GradeService;
import com.pa165.sportEventservice.service.SportService;
import com.pa165.sportEventservice.service.SportsmanService;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Maria
 */

@UrlBinding("/eeevents/{$event}/{eventDTO.id}")
public class EventActionBean implements ActionBean {

    final static Logger log = LoggerFactory.getLogger(EventActionBean.class);
    private ActionBeanContext context;
    @SpringBean
    protected EventService eventService;
    @SpringBean
    protected SportService sportService;
    @SpringBean
    protected GradeService gradeService;
    @SpringBean
    protected SportsmanService sportsmanService;
    
    @ValidateNestedProperties(value = {
        @Validate(on = {"saveAdd", "saveEdit"}, field = "name", required = true),
        @Validate(on = {"saveAdd", "saveEdit"}, field = "dateOfEvent", required = true)
    })
    private EventDTO eventDTO;
    private SportDTO sportDTO;
    private GradeDTO gradeDTO;
    private int grade;
    private SportsmanDTO sportsmanDTO;
    public static String FORMATSTRING = "yyyy-MM-dd";
    private SimpleDateFormat sdfSource = new SimpleDateFormat(FORMATSTRING);

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public SportService getSportService() {
        return sportService;
    }

    public void setSportService(SportService sportService) {
        this.sportService = sportService;
    }

    public GradeService getGradeService() {
        return gradeService;
    }

    public void setGradeService(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    public SportsmanService getSportsmanService() {
        return sportsmanService;
    }

    public void setSportsmanService(SportsmanService sportsmanService) {
        this.sportsmanService = sportsmanService;
    }

    public SportDTO getSportDTO() {
        return sportDTO;
    }

    public void setSportDTO(SportDTO sportDTO) {
        this.sportDTO = sportDTO;
    }

    public GradeDTO getGradeDTO() {
        return gradeDTO;
    }

    public void setGradeDTO(GradeDTO gradeDTO) {
        this.gradeDTO = gradeDTO;
    }

    public SportsmanDTO getSportsmanDTO() {
        return sportsmanDTO;
    }

    public void setSportsmanDTO(SportsmanDTO sportsmanDTO) {
        this.sportsmanDTO = sportsmanDTO;
    }

    public EventService getEventService() {
        return eventService;
    }

    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    public EventDTO getEventDTO() {
        return eventDTO;
    }

    public void setEventDTO(EventDTO eventDTO) {
        this.eventDTO = eventDTO;
    }

    public SimpleDateFormat getSdfSource() {
        return sdfSource;
    }

    public void setSdfSource(SimpleDateFormat sdfSource) {
        this.sdfSource = sdfSource;
    }

    public Resolution add() {
        log.debug("add() eventDTO={}", eventDTO);
        return new ForwardResolution("/event/eventAdd.jsp");
    }

    public Resolution saveAdd() throws ServiceFailureException{
        log.debug("add() eventDTO={}", eventDTO);
        eventService.add(eventDTO);
        return new RedirectResolution(this.getClass(), "all");
    }

    @DefaultHandler
    public Resolution all() {
        log.debug("all()");
        return new ForwardResolution("/event/event.jsp");
    }

    public Resolution delete() throws ServiceFailureException{
        log.debug("delete({})", eventDTO.getEventId());
        eventService.remove(eventDTO);
        return new RedirectResolution(this.getClass(), "all");
    }

    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "saveEdit", "addSportsman", "showSportsmans", "putGrade"})
    public void loadEventFromDatabase() throws ServiceFailureException{
        String ids = context.getRequest().getParameter("eventDTO.eventId");
        if (ids == null) {
            return;
        }
        eventDTO = eventService.findById(Long.parseLong(ids));
    }

    
    public Resolution showSportsmans() {
        log.debug("showSportsmans()");
        return new ForwardResolution("/event/eventSportsmans.jsp");
    }

    public List<EventDTO> getEvents() throws ServiceFailureException{
        log.debug("getEvents() eventDTO={}", eventDTO);
        return eventService.getAll();
    }
    
    public Map<SportsmanDTO, GradeDTO> getSportsmans() throws ServiceFailureException{
        log.debug("getSportsmans() eventDTO={}", eventDTO);
        return eventService.getSportsmansWithGrades(eventDTO);
    }
    
    public Resolution editGrade() throws ServiceFailureException{
        log.debug("editGrade() gradeDTO={}", gradeDTO,sportsmanDTO, eventDTO);
        gradeService.putGrade(sportsmanDTO.getSportsmanId(), eventDTO.getEventId(), gradeDTO.getGrade());
        return getContext().getSourcePageResolution();
    }

    public List<SportDTO> getAllSports() throws ServiceFailureException {
        return sportService.getAll();
    }

    public Resolution edit() {
        log.debug("edit() event={}", eventDTO);
        return new ForwardResolution("/event/eventEdit.jsp");
    }

    public Resolution saveEdit() throws ServiceFailureException{
        log.debug("saveEdit() eventDTO={}", eventDTO);
        eventService.edit(eventDTO);
        return new RedirectResolution(this.getClass(), "all");
    }

    

    @Override
    public void setContext(ActionBeanContext context) {
        this.context = context;
    }

    @Override
    public ActionBeanContext getContext() {
        return context;
    }
}
