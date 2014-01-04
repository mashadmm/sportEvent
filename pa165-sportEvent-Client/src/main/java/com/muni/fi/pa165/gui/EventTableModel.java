package com.muni.fi.pa165.gui;


import com.muni.fi.pa165.client.SportEventClient;
import com.pa165.sportevent.ws.EventDTO;
import com.pa165.sportevent.ws.SportDTO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;



public class EventTableModel extends AbstractTableModel {
    
    private static final Logger LOGGER = Logger.getLogger(EventTableModel.class.getName());
    public static String FORMATSTRING = "yyyy-MM-dd";
    private SimpleDateFormat sdfSource = new SimpleDateFormat(FORMATSTRING);
    private GregorianCalendar gregory = new GregorianCalendar();
    private XMLGregorianCalendar calendar;

    private List<EventDTO> events = new ArrayList<EventDTO>();
    private static enum COLUMNS {
        ID, NAME, DATE, SPORT
    }
 
    private SportEventClient client;
    public  EventTableModel(){
        this.client = new SportEventClient();
        
    } 
    
    @Override
    public int getRowCount() {
        return events.size();
    }
 
    @Override
    public int getColumnCount() {
        return COLUMNS.values().length;
    }
    public SimpleDateFormat getSdfSource() {
        return sdfSource;
    }

        
    @Override
    public Class<?> getColumnClass(int columnIndex) {
	switch (COLUMNS.values()[columnIndex]) {
	    case ID:
		return Long.class;
	    case NAME:
	    case SPORT:
 		return String.class;
            case DATE:
		return String.class;
	    default:
		throw new IllegalArgumentException("columnIndex");
	}
    }
    
    @Override
    public String getColumnName(int columnIndex) {
	switch (COLUMNS.values()[columnIndex]) {
	    case ID:
		return java.util.ResourceBundle.getBundle("Bundle").getString("events_table_id");
	    case NAME:
		return java.util.ResourceBundle.getBundle("Bundle").getString("events_table_name");
	    case DATE:
		return java.util.ResourceBundle.getBundle("Bundle").getString("events_table_date");
	    case SPORT:
		return java.util.ResourceBundle.getBundle("Bundle").getString("events_table_sport");
            default:
		throw new IllegalArgumentException("columnIndex");
	}
    }
 
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        EventDTO event = events.get(rowIndex);
        switch (COLUMNS.values()[columnIndex]) {
            case ID:
		return event.getEventId();
	    case NAME:
		return event.getName();
	    case DATE:
		return sdfSource.format((event.getDateOfEvent()).toGregorianCalendar().getTime());
	    case SPORT:
		return event.getSport().getName();
            
            default:
                throw new IllegalArgumentException("columnIndex");
        }
    }
    
    public void addEvent(EventDTO event) {
	events.add(event);
	fireTableDataChanged();
    }
        
    public void removeEvent(EventDTO event) {
	events.remove(event);
	fireTableDataChanged();
    }
    
    public void clear() {
	events.clear();
        fireTableDataChanged();
    }
    
     public List<EventDTO> getAllEvents() {
	return events;
    }
    
    @Override 
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
	EventDTO event = events.get(rowIndex);
	switch (COLUMNS.values()[columnIndex]) {
	    case ID:
		event.setEventId((Long) aValue);
		break;
	    case NAME:
		event.setName((String) aValue);
		break;
	    case DATE:
                try { 
                     gregory.setTime(sdfSource.parse((String) aValue));
                 } catch (ParseException ex) {
                    Logger.getLogger(EventTableModel.class.getName()).log(Level.SEVERE, null, ex);
                }   
                try {
                    calendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregory);
                } catch (DatatypeConfigurationException ex) {
                    Logger.getLogger(SportsmanTableModel.class.getName()).log(Level.SEVERE, null, ex);
                }
                event.setDateOfEvent(calendar);
                break;
            case SPORT:
		event.setSport((SportDTO) aValue);
		break;
            
	    default:
		throw new IllegalArgumentException("columnIndex");
	}
        try {
            client.eventManagerupdate(event);
            fireTableDataChanged();
        } catch (Exception ex) {
            String msg = "User request failed";
            LOGGER.log(Level.INFO, msg);
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
	switch (COLUMNS.values()[columnIndex]) {
	    case ID:
		return false;
            case NAME:
	    case DATE:
	    case SPORT:
    		return true;
	    default:
		throw new IllegalArgumentException("columnIndex");
	}
    }

}
