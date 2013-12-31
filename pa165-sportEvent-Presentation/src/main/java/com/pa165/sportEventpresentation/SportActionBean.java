/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pa165.sportEventpresentation;


import com.pa165.sportEventpersistence.Exceptions.ServiceFailureException;
import static com.pa165.sportEventpresentation.EventActionBean.FORMATSTRING;
import com.pa165.sportEventservice.DTO.EventDTO;
import com.pa165.sportEventservice.DTO.SportDTO;
import com.pa165.sportEventservice.service.EventService;
import com.pa165.sportEventservice.service.SportService;
import java.text.SimpleDateFormat;
import java.util.List;
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

@UrlBinding("/spooort/{$event}/{sportDTO.sportId}")
public class SportActionBean implements ActionBean {

    final static Logger log = (Logger) LoggerFactory.getLogger(SportActionBean.class);
    private ActionBeanContext context;
    @SpringBean
    protected SportService sportService;
    @SpringBean
    protected EventService eventService;
     
    
    @ValidateNestedProperties(value = {
        @Validate(on = {"saveAdd", "saveEdit"}, field = "name", required = true)})
    protected SportDTO sportDTO;
    
    public static String FORMATSTRING = "yyyy-MM-dd";
    private SimpleDateFormat sdfSource = new SimpleDateFormat(FORMATSTRING);

    public SimpleDateFormat getSdfSource() {
        return sdfSource;
    }

    public void setSdfSource(SimpleDateFormat sdfSource) {
        this.sdfSource = sdfSource;
    }
    
    public SportService getSportService() {
        return sportService;
    }

    public void setSportService(SportService sportService) {
        this.sportService = sportService;
    }

    public EventService getEventService() {
        return eventService;
    }

    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    

    public SportDTO getSportDTO() {
        return sportDTO;
    }

    public void setSportDTO(SportDTO sportDTO) {
        this.sportDTO = sportDTO;
    }

    public Resolution add() {
        log.debug("add() sportDTO={}", sportDTO);
        return new ForwardResolution("/sport/addSport.jsp");
    }

    public Resolution saveAdd() {
        log.debug("add() sportDTO={}", sportDTO);
        try {
            sportService.add(sportDTO);
            return new RedirectResolution(this.getClass(), "all");
        } catch (ServiceFailureException e) {
            ValidationErrors errors = new ValidationErrors();
            errors.add("sport Name", new LocalizableError("sportName.duplicate"));
            getContext().setValidationErrors(errors);
            return getContext().getSourcePageResolution();
        }catch (IllegalArgumentException e) {
            ValidationErrors errors = new ValidationErrors();
            errors.add("sport Name", new LocalizableError("sportName.duplicate"));
            getContext().setValidationErrors(errors);
            return getContext().getSourcePageResolution();
        }

    }

    @DefaultHandler
    public Resolution all() {
        log.debug("all()");
        return new ForwardResolution("/sport/sport.jsp");
    }

    public Resolution delete() throws ServiceFailureException{
        log.debug("delete({})", sportDTO.getSportId());
        sportService.remove(sportDTO);
        return new RedirectResolution(this.getClass(), "all");
    }

    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "saveEdit", "showEvents"})
    public void loadFromDatabase() throws ServiceFailureException {
        String ids = context.getRequest().getParameter("sportDTO.sportId");
        if (ids == null) {
            return;
        }
        sportDTO = sportService.findById(Long.parseLong(ids));
    }

    public Resolution edit() {
        log.debug("edit() sport={}", sportDTO);
        return new ForwardResolution("/sport/editSport.jsp");
    }

    public Resolution saveEdit() {
        log.debug("saveEdit() sportDTO={}", sportDTO);
        try {
            sportService.edit(sportDTO);
            return new RedirectResolution(this.getClass(), "all");
        } catch (ServiceFailureException e) {
            ValidationErrors errors = new ValidationErrors();
            errors.add("sport Name", new LocalizableError("sportName.duplicate"));
            getContext().setValidationErrors(errors);
            return getContext().getSourcePageResolution();
        }
    }

    public List<SportDTO> getSports() throws ServiceFailureException {
        return sportService.getAll();
    }

    public Resolution showEvents() {
        log.debug("showEvents()");
        return new ForwardResolution("/sport/sportEvents.jsp");
    }

    public List<EventDTO> getEvents() throws ServiceFailureException{
        log.debug("getLectures() sportDTO={}", sportDTO);
        return sportService.getEvents(sportDTO);
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