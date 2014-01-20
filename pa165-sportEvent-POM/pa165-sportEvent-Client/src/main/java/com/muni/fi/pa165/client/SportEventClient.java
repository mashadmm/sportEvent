/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muni.fi.pa165.client;


import com.pa165.sportevent.ws.EventDTO;
import com.pa165.sportevent.ws.GradeDTO;
import com.pa165.sportevent.ws.SportDTO;
import com.pa165.sportevent.ws.SportEventWSImpl;
import com.pa165.sportevent.ws.SportEventWSImplService;
import com.pa165.sportevent.ws.SportsmanDTO;
import java.util.ArrayList;
import java.util.List;
import javax.xml.ws.BindingProvider;



/**
 *
 * @author Maria
 */
public class SportEventClient {
    
    public static final String  user="rest";
    public static final String  password="rest";

    public SportEventClient() {}
        
         // Event
        
        public List<EventDTO> eventManagerfindAll(){
                List<EventDTO> result = new ArrayList<EventDTO>();
                    try { // Call Web Service Operation
                        SportEventWSImplService service = new SportEventWSImplService();
                        SportEventWSImpl port = service.getSportEventWSImplPort();
                        // TODO process result here
                        BindingProvider bp = (BindingProvider) port;
                        bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, user);
                        bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);
                        result = port.eventManagerfindAll();
                        //System.out.println("Result = "+result);
                    } catch (Exception ex) {
                        System.out.println("ERRRRROR:" + ex);
                    }
                    return result;
        }
        
        public void eventManagersave(EventDTO event){
            
            try { // Call Web Service Operation
                SportEventWSImplService service = new SportEventWSImplService();
                SportEventWSImpl port = service.getSportEventWSImplPort();
                // TODO initialize WS operation arguments here
                EventDTO arg0 = new EventDTO();
                arg0 = event;
                BindingProvider bp = (BindingProvider) port;
                bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, user);
                bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);
                port.eventManagersave(arg0);
            } catch (Exception ex) {
                // TODO handle custom exceptions here
            }

        } 
        
        
        public void eventManagerupdate(EventDTO event){
            
            
            try { // Call Web Service Operation
                SportEventWSImplService service = new SportEventWSImplService();
                SportEventWSImpl port = service.getSportEventWSImplPort();
                // TODO initialize WS operation arguments here
                EventDTO arg0 = new EventDTO();
                arg0 = event;
                BindingProvider bp = (BindingProvider) port;
                bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, user);
                bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);
                port.eventManagerupdate(arg0);
            } catch (Exception ex) {
                // TODO handle custom exceptions here
            }

        }
         
       
        public void eventManagerdelete(EventDTO event){
            
            
            try { // Call Web Service Operation
                SportEventWSImplService service = new SportEventWSImplService();
                SportEventWSImpl port = service.getSportEventWSImplPort();
                // TODO initialize WS operation arguments here
                EventDTO arg0 = new EventDTO();
                arg0 = event;
                BindingProvider bp = (BindingProvider) port;
                bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, user);
                bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);
                port.eventManagerdelete(arg0);
            } catch (Exception ex) {
                // TODO handle custom exceptions here
            }
 
        }
        
        
        public EventDTO eventManagerfindById(Long eventid){
            
            EventDTO result = new EventDTO();
            try { // Call Web Service Operation
                SportEventWSImplService service = new SportEventWSImplService();
                SportEventWSImpl port = service.getSportEventWSImplPort();
                // TODO initialize WS operation arguments here
                java.lang.Long arg0 = eventid;
                // TODO process result here
                BindingProvider bp = (BindingProvider) port;
                bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, user);
                bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);
                result = port.eventManagerfindById(arg0);
                //System.out.println("Result = "+result);
            } catch (Exception ex) {
                // TODO handle custom exceptions here
            }

            return result;
        }
        
        // Sportsman
        
        
        public List<SportsmanDTO> sportsmanManagerfindAll(){
            List<SportsmanDTO> result = new ArrayList<SportsmanDTO>();
            
            try { // Call Web Service Operation
                SportEventWSImplService service = new SportEventWSImplService();
                SportEventWSImpl port = service.getSportEventWSImplPort();
                // TODO process result here
                BindingProvider bp = (BindingProvider) port;
                bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, user);
                bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);
                result = port.sportsmanManagerfindAll();
                System.out.println("Result = "+result);
            } catch (Exception ex) {
                System.out.println("ERRRRROR:" + ex);
            }

            return result;
        }
        
        public void sportsmanManagersave(SportsmanDTO sportsman){
            
            
            try { // Call Web Service Operation
                SportEventWSImplService service = new SportEventWSImplService();
                SportEventWSImpl port = service.getSportEventWSImplPort();
                // TODO initialize WS operation arguments here
                SportsmanDTO arg0 = new SportsmanDTO();
                arg0 = sportsman;
                BindingProvider bp = (BindingProvider) port;
                bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, user);
                bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);
                port.sportsmanManagersave(arg0);
            } catch (Exception ex) {
                // TODO handle custom exceptions here
            }

        } 
        
        
        public void sportsmanManagerupdate(SportsmanDTO sportsman){
            
            
            try { // Call Web Service Operation
                SportEventWSImplService service = new SportEventWSImplService();
                SportEventWSImpl port = service.getSportEventWSImplPort();
                // TODO initialize WS operation arguments here
                SportsmanDTO arg0 = new SportsmanDTO();
                arg0 = sportsman;
                BindingProvider bp = (BindingProvider) port;
                bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, user);
                bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);
                port.sportsmanManagerupdate(arg0);
            } catch (Exception ex) {
                // TODO handle custom exceptions here
            }

        }
         
       
        public void sportsmanManagerdelete(SportsmanDTO sportsman){
            
            
            try { // Call Web Service Operation
                SportEventWSImplService service = new SportEventWSImplService();
                SportEventWSImpl port = service.getSportEventWSImplPort();
                // TODO initialize WS operation arguments here
                SportsmanDTO arg0 = new SportsmanDTO();
                arg0 = sportsman;
                BindingProvider bp = (BindingProvider) port;
                bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, user);
                bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);
                port.sportsmanManagerdelete(arg0);
            } catch (Exception ex) {
                // TODO handle custom exceptions here
            }

        }
        
        
        public SportsmanDTO sportsmanManagerfindById(Long sportsmanid){
            SportsmanDTO result = new SportsmanDTO();
            try { // Call Web Service Operation
                SportEventWSImplService service = new SportEventWSImplService();
                SportEventWSImpl port = service.getSportEventWSImplPort();
                // TODO initialize WS operation arguments here
                java.lang.Long arg0 = sportsmanid;
                // TODO process result here
                BindingProvider bp = (BindingProvider) port;
                bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, user);
                bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);
                result = port.sportsmanManagerfindById(arg0);
                //System.out.println("Result = "+result);
            } catch (Exception ex) {
                // TODO handle custom exceptions here
            }

            return result;
        }
        
        // Grade
        
        
        public List<GradeDTO> gradeManagerfindAll(){
            List<GradeDTO> result = new ArrayList<GradeDTO>();
            
            try { // Call Web Service Operation
                SportEventWSImplService service = new SportEventWSImplService();
                SportEventWSImpl port = service.getSportEventWSImplPort();
                // TODO process result here
                BindingProvider bp = (BindingProvider) port;
                bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, user);
                bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);
                result = port.gradeManagerfindAll();
                System.out.println("Result = "+result);
            } catch (Exception ex) {
                System.out.println("ERRRRROR:" + ex);
            }

            return result;
        }
        
        public void gradeManagersave(GradeDTO gradeDto){
            
            
            try { // Call Web Service Operation
                SportEventWSImplService service = new SportEventWSImplService();
                SportEventWSImpl port = service.getSportEventWSImplPort();
                // TODO initialize WS operation arguments here
                GradeDTO arg0 = new GradeDTO();
                arg0 = gradeDto;
                BindingProvider bp = (BindingProvider) port;
                bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, user);
                bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);
                port.gradeManagersave(arg0);
            } catch (Exception ex) {
                System.out.println("ERRRRROR:" + ex);
            }

        } 
        
        
        public void gradeManagerupdate(GradeDTO gradeDto){
            
            
            try { // Call Web Service Operation
                SportEventWSImplService service = new SportEventWSImplService();
                SportEventWSImpl port = service.getSportEventWSImplPort();
                // TODO initialize WS operation arguments here
                GradeDTO arg0 = new GradeDTO();
                arg0 = gradeDto;
                BindingProvider bp = (BindingProvider) port;
                bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, user);
                bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);
                port.gradeManagerupdate(arg0);
            } catch (Exception ex) {
                // TODO handle custom exceptions here
            }

        }
         
        
        public void gradeManagerdelete(Long sportsmanid, Long eventid){
            
            
            try { // Call Web Service Operation
                SportEventWSImplService service = new SportEventWSImplService();
                SportEventWSImpl port = service.getSportEventWSImplPort();
                // TODO initialize WS operation arguments here
                GradeDTO arg0 = this.gradeManagerfindById(sportsmanid, eventid);
                BindingProvider bp = (BindingProvider) port;
                bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, user);
                bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);
                port.gradeManagerdelete(arg0);
            } catch (Exception ex) {
                // TODO handle custom exceptions here
            }

        }
        
        
        public GradeDTO gradeManagerfindById(Long eventid, Long sportsmanid){
            
            GradeDTO result = new GradeDTO();
            try { // Call Web Service Operation
                SportEventWSImplService service = new SportEventWSImplService();
                SportEventWSImpl port = service.getSportEventWSImplPort();
                // TODO initialize WS operation arguments here
                java.lang.Long arg0 = eventid;
                java.lang.Long arg1 = sportsmanid;
                // TODO process result here
                BindingProvider bp = (BindingProvider) port;
                bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, user);
                bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);
                result = port.gradeManagerfindById(arg0, arg1);
                System.out.println("Result = "+result);
            } catch (Exception ex) {
                // TODO handle custom exceptions here
            }

            return result;
        }
        
       //Sport
        
        
         public List<SportDTO> sportManagergetAll(){
            List<SportDTO> result = new ArrayList<SportDTO>();
            
            try { // Call Web Service Operation
                SportEventWSImplService service = new SportEventWSImplService();
                SportEventWSImpl port = service.getSportEventWSImplPort();
                BindingProvider bp = (BindingProvider) port;
                bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, user);
                bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);
                result = port.sportManagergetAll();
                System.out.println("Result = "+result);
            } catch (Exception ex) {
                // TODO handle custom exceptions here
            }

            return result;
        }
        
    }

