package com.muni.fi.pa165.gui;


import com.muni.fi.pa165.client.SportEventClient;
import com.pa165.sportevent.ws.SportsmanDTO;

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

//import net.sourceforge.stripes.integration.spring.SpringBean;

public class SportsmanTableModel extends AbstractTableModel {
    
    private static final Logger LOGGER = Logger.getLogger(SportsmanTableModel.class.getName());
    public static String FORMATSTRING = "yyyy-MM-dd";
    private SimpleDateFormat sdfSource = new SimpleDateFormat(FORMATSTRING);
    private GregorianCalendar gregory = new GregorianCalendar();
    private XMLGregorianCalendar calendar;

    

    private List<SportsmanDTO> sportsmans = new ArrayList<SportsmanDTO>();
    private static enum COLUMNS {
        ID, NAME, LASTNAME, DATEOFBIRTH
 
    }
    
        
    private SportEventClient client;
    public  SportsmanTableModel(){
        this.client = new SportEventClient();
    } 
    
    @Override
    public int getRowCount() {
        return sportsmans.size();
    }
 
    @Override
    public int getColumnCount() {
        return COLUMNS.values().length;
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
	switch (columnIndex) {
	    case 0:
		return Long.class;
	    case 1:
	    case 2:
           	return String.class;
	    case 3:
   		return String.class;
   
	    default:
		throw new IllegalArgumentException("columnIndex");
	}
 
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        SportsmanDTO sportsman = sportsmans.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return sportsman.getSportsmanId();
            case 1:
                return sportsman.getName();
            case 2:
                return sportsman.getLastname();
            case 3:
                return sdfSource.format((sportsman.getDateOfBirth()).toGregorianCalendar().getTime());
            
            default:
                throw new IllegalArgumentException("columnIndex");
        }
    }
    
    public void addSportsman(SportsmanDTO sportsman) {
	sportsmans.add(sportsman);
	fireTableDataChanged();
    }
    
    public void removeSportsman(SportsmanDTO sportsman) {
	sportsmans.remove(sportsman);
	fireTableDataChanged();
    }
    
    public void clear() {
	sportsmans.clear();
        fireTableDataChanged();
    }
    
     public List<SportsmanDTO> getAllSportsmans() {
	return sportsmans;
    }

    
    @Override
    public String getColumnName(int columnIndex) {
	switch (COLUMNS.values()[columnIndex]) {
	    case ID:
		return java.util.ResourceBundle.getBundle("Bundle").getString("sportsmans_table_id");
	    case NAME:
		return java.util.ResourceBundle.getBundle("Bundle").getString("sportsmans_table_name");
	    case LASTNAME:
		return java.util.ResourceBundle.getBundle("Bundle").getString("sportsmans_table_lastname");
	    case DATEOFBIRTH:
		return java.util.ResourceBundle.getBundle("Bundle").getString("sportsmans_table_dateofbirth");
            
	    default:
		throw new IllegalArgumentException("columnIndex");
	}
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
	SportsmanDTO sportsman = sportsmans.get(rowIndex);
	switch (COLUMNS.values()[columnIndex]) {
            case ID:
		sportsman.setSportsmanId((Long) aValue);
                break;
	    case NAME:
		sportsman.setName((String) aValue);
                break;
	   case LASTNAME:
		sportsman.setName((String) aValue);
                break;
	   case DATEOFBIRTH:
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
                sportsman.setDateOfBirth(calendar);
                
		break;
	    default:
		throw new IllegalArgumentException("columnIndex");
	}
        try {
            client.sportsmanManagerupdate(sportsman);
            fireTableDataChanged();
        } catch (Exception ex) {
            String msg = "User request failed";
            LOGGER.log(Level.INFO, msg);
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
	switch (columnIndex) {
	    case 1:
	    case 2:
            case 3:
    
		return true;
	    case 0:
		return false;
	    default:
		throw new IllegalArgumentException("columnIndex");
	}
    }

}
