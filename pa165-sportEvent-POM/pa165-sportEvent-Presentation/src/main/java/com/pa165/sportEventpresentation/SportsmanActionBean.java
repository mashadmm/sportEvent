
package com.pa165.sportEventpresentation;

import com.pa165.sportEventpersistence.DAOImpl.SportsmanDAO;
import com.pa165.sportEventpersistence.Exceptions.ServiceFailureException;
import com.pa165.sportEventservice.DTO.EventDTO;
import com.pa165.sportEventservice.DTO.GradeDTO;
import com.pa165.sportEventservice.DTO.SportsmanDTO;
import com.pa165.sportEventservice.service.EventService;
import com.pa165.sportEventservice.service.GradeService;
import com.pa165.sportEventservice.service.SportsmanService;
import java.text.SimpleDateFormat;
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
import net.sourceforge.stripes.validation.LocalizableError;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import net.sourceforge.stripes.validation.ValidationErrors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Maria
 */

@UrlBinding("/spooortsmans/{$event}/{sportsmanDTO.sportsmanId}")
public class SportsmanActionBean implements ActionBean {

    final static Logger log = LoggerFactory.getLogger(SportsmanActionBean.class);
    private ActionBeanContext context;
    @SpringBean
    protected SportsmanService sportsmanService;
    @SpringBean
    protected SportsmanDAO sportsmanDAO;
    @SpringBean
    protected GradeService gradeService;
    @SpringBean
    protected EventService eventService;
    
    
    @ValidateNestedProperties(value = {
        @Validate(on = {"saveAdd", "saveEdit"}, field = "name", required = true),
        @Validate(on = {"saveAdd", "saveEdit"}, field = "lastname", required = true),
        @Validate(on = {"saveAdd", "saveEdit"}, field="dateOfBirth", required=true),
        @Validate(on = {"saveAdd", "saveEdit"}, field="userName", required=true, minlength=2, maxlength=20),
        @Validate(on = {"saveAdd", "saveEdit"}, field="pwd", required=true, minlength=2, maxlength=20)
        
    })
    protected SportsmanDTO sportsmanDTO;
    //private GradeDTO gradeDTO;
    private EventDTO eventDTO;
    public static String FORMATSTRING = "yyyy-MM-dd";
    private SimpleDateFormat sdfSource = new SimpleDateFormat(FORMATSTRING);

    public SportsmanService getSportsmanService() {
        return sportsmanService;
    }

    public GradeService getGradeService() {
        return gradeService;
    }

    public EventService getEventService() {
        return eventService;
    }

    public void setGradeService(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    public void setSportsmanService(SportsmanService sportsmanService) {
        this.sportsmanService = sportsmanService;
    }

    
    public SportsmanDTO getSportsmanDTO() {
        return sportsmanDTO;
    }

    public void setSportsmanDTO(SportsmanDTO sportsmanDTO) {
        this.sportsmanDTO = sportsmanDTO;
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
        log.debug("add() sportsmanDTO={}", sportsmanDTO);
        return new ForwardResolution("/sportsman/addSportsman.jsp");
    }
    
    public Resolution saveAdd() throws ServiceFailureException{
        log.debug("add() sportsmanDTO={}", sportsmanDTO);
        sportsmanService.add(sportsmanDTO);
        return new RedirectResolution(this.getClass(), "all");
    }

    @DefaultHandler
    public Resolution all() {
        log.debug("all()");
        return new ForwardResolution("/sportsman/sportsman.jsp");
    }

    public Resolution delete() throws ServiceFailureException{
        log.debug("delete({})", sportsmanDTO.getSportsmanId());
        sportsmanService.remove(sportsmanDTO);
        return new RedirectResolution(this.getClass(), "all");
    }

    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "saveEdit", "registerSportsman", "showEvents"})
    public void loadSportsmanFromDatabase() throws ServiceFailureException{
        String ids = context.getRequest().getParameter("sportsmanDTO.sportsmanId");
        if (ids == null) {
            return;
        }
        sportsmanDTO = sportsmanService.findById(Long.parseLong(ids));
    }
    
    public Resolution edit() {
        log.debug("edit() sportsman={}", sportsmanDTO);
        return new ForwardResolution("/sportsman/editSportsman.jsp");
    }

    public Resolution saveEdit() throws ServiceFailureException{
        log.debug("saveEdit() sportsmanDTO={}", sportsmanDTO);
        sportsmanService.edit(sportsmanDTO);
        return new RedirectResolution(this.getClass(), "all");
    }

    public List<SportsmanDTO> getSportsmans() throws ServiceFailureException{
        return sportsmanService.getAll();
    }

    public Resolution showEvents() {
        log.debug("showEvents()");
        
        return new ForwardResolution("/sportsman/sportsmanEvents.jsp");
    }


    
    public List<GradeDTO> getGrades() throws ServiceFailureException{
        log.debug("getEvents() sportsmanDTO={}", sportsmanDTO);
        return sportsmanDTO.getResults();
    }
    
     

    public List<EventDTO> getAllEvents() throws ServiceFailureException {
        log.debug("getAllEvents()");
        return eventService.getAllUpToDate();
    }

    public Map<EventDTO, GradeDTO> getEvents() throws ServiceFailureException {
        log.debug("getEvents()");
        return sportsmanService.getEventsWithGrades(sportsmanDTO);
    }
   

    
    public Resolution register() {
        log.debug("registrate()");
        return new ForwardResolution("/sportsman/registerToEvent.jsp");
    }

    public Resolution registerToEvent() {
        log.debug("registerSportsman()", sportsmanDTO, eventDTO);
        try {
            sportsmanService.registerToEvent(sportsmanDTO, eventDTO);
             return getContext().getSourcePageResolution();
        } catch ( ServiceFailureException e) {
            ValidationErrors errors = new ValidationErrors();
            errors.add("event", new LocalizableError("event.empty"));
            getContext().setValidationErrors(errors);
            return getContext().getSourcePageResolution();
        }
    }

    @Override
    public void setContext(ActionBeanContext context) {
        this.context = context;
    }

    @Override
    public ActionBeanContext getContext() {
        return context;
    }
   
    public Resolution showEventsByLogin() {
            log.debug("showEventsByLogin()");
            return new ForwardResolution("/sportsman/sportsmanEvents.jsp");
        }
        @Before(stages = LifecycleStage.BindingAndValidation, on = { "showEventsByLogin"})
        public void loadSportsmanByLogin() throws ServiceFailureException{
            String login = context.getRequest().getUserPrincipal().getName();
            if (login == null) {
                return;
            }
            sportsmanDTO = sportsmanService.getByLogin(login);
       } 

}